package com.silence.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.silence.ElasticsearchApplication;
import com.silence.elasticsearch.infrastructure.repository.DO.Scheme;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.histogram.HistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest(classes = ElasticsearchApplication.class)
public class EsTest {

    @Autowired
    private RestHighLevelClient client;

    public EsTest() throws IOException {
    }

    /**
     * 创建索引（简单模式）
     *
     * @throws IOException
     */
    @Test
    public void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("cs_go_index");
        Settings.Builder put = Settings.builder().put("number_of_shards", "2").put("number_of_replicas", "2");
        request.settings(put);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.isAcknowledged());
    }


    @Test
    public void saveIndex() throws IOException {
        //1.新建索引
        IndexRequest indexRequest = new IndexRequest("cs_gos_index");

        //2.数据的id
        indexRequest.id("4");

        //第二种, 保存json字符串对象的形式

        String jsonString = JSON.toJSONString(buildScheme3());

        //3.构建数据 XContentType ====> 必须要指定内容类型
        indexRequest.source(jsonString, XContentType.JSON);

        //4.执行操作
        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index);
        //提取有用的响应数据
    }

    @Test
    public void saveDateIndex() throws IOException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //1.新建索引
        IndexRequest indexRequest = new IndexRequest("date_index");

        Map<String, Object> user = new HashMap<>();
        user.put("name", "chris");
        user.put("age", 18);
        user.put("birthday", d.format(new Date()));

        //2.数据的id
        indexRequest.id("1");

        //第二种, 保存json字符串对象的形式

        String jsonString = JSON.toJSONString(user);

        //3.构建数据 XContentType ====> 必须要指定内容类型
        indexRequest.source(jsonString, XContentType.JSON);

        //4.执行操作
        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index);
        //提取有用的响应数据
    }


    @Test
    public void saveBulkIndex() throws IOException {

        BulkRequest request = new BulkRequest("cs_index");


        //1.新建索引
        request.add(new UpdateRequest("cs_index", "1").doc(XContentType.JSON, "saleCount", "16"));
        request.add(new IndexRequest("cs_index").id("4")
                .opType("create").source(XContentType.JSON, JSON.toJSONString(buildScheme3())));        //3.构建数据 XContentType ====> 必须要指定内容类型
        //4.执行操作
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulkResponse);
        //提取有用的响应数据
    }

    @Test
    public void searchIndex() throws IOException {

        BulkRequest request = new BulkRequest("cs_index");


        //1.新建索引
        request.add(new UpdateRequest("cs_index", "1").doc(XContentType.JSON, "saleCount", "16"));
        request.add(new IndexRequest("cs_index").id("2").source(XContentType.JSON, JSON.toJSONString(buildScheme2())));
        //3.构建数据 XContentType ====> 必须要指定内容类型
        //4.执行操作
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulkResponse);
        //提取有用的响应数据
    }

    @Test
    public void boolSearchIndex() throws IOException {

        // 指定创建时间
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.matchAllQuery());
        queryBuilder.filter(QueryBuilders.rangeQuery("saleCount").gte(0));


        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(1);
        sourceBuilder.size(2);
        sourceBuilder.sort("saleCount", SortOrder.DESC);
        sourceBuilder.query(queryBuilder);

        SearchRequest searchRequest = new SearchRequest("cs_index");
        searchRequest.source(sourceBuilder);
        final SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        final List<Scheme> collect = Arrays.stream(search.getHits().getHits()).map(SearchHit::getSourceAsString).map(a -> JSON.parseObject(a, Scheme.class)).collect(Collectors.toList());


        System.out.println(JSON.toJSONString(collect));


        CountRequest countRequest = new CountRequest("cs_index");
        countRequest.query(queryBuilder);
        final CountResponse count = client.count(countRequest, RequestOptions.DEFAULT);
        System.out.println(count);
        //提取有用的响应数据
    }

    @Test
    public void multiSearchIndex() throws IOException, NoSuchFieldException, IllegalAccessException {

        // 指定创建时间
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.should(QueryBuilders.multiMatchQuery("面膜").field("schemeName").field("adapter"));
        queryBuilder.filter(QueryBuilders.rangeQuery("saleCount").gte(0));

        MultiMatchQueryBuilder matchQueryBuilder = QueryBuilders.multiMatchQuery("1", "categoryId");
        queryBuilder.must(matchQueryBuilder);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(0);
        sourceBuilder.size(2);
        sourceBuilder.sort("saleCount", SortOrder.DESC);
        sourceBuilder.query(queryBuilder);


        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("schemeName").field("adapter");
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        //下面这两项,如果你要高亮如文字内容等有很多字的字段,必须配置,不然会导致高亮不全,文章内容缺失等
        highlightBuilder.fragmentSize(800000); //最大高亮分片数
        highlightBuilder.numOfFragments(0); //从第一个分片获取高亮片段

        sourceBuilder.highlighter(highlightBuilder);

        SearchRequest searchRequest = new SearchRequest("cs_index");
        searchRequest.source(sourceBuilder);


        final SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        final List<Scheme> collect = Arrays.stream(search.getHits().getHits()).map(SearchHit::getSourceAsString).map(a -> JSON.parseObject(a, Scheme.class)).collect(Collectors.toList());

        List<Map<String, HighlightField>> collect1 = Arrays.stream(search.getHits().getHits()).map(SearchHit::getHighlightFields).collect(Collectors.toList());


        for (int i = 0; i < collect.size(); i++) {
            final Scheme scheme = collect.get(i);
            Map<String, HighlightField> stringHighlightFieldMap = collect1.get(i);
            if (!CollectionUtils.isEmpty(stringHighlightFieldMap)) {
                for (String s : stringHighlightFieldMap.keySet()) {
                    Field s1 = scheme.getClass().getDeclaredField(s);
                    s1.setAccessible(true);
                    s1.set(scheme, Arrays.toString(stringHighlightFieldMap.get(s).fragments()));
                }
            }
        }

        System.out.println(JSON.toJSONString(collect));

        final Text[] schemeNames = collect1.get(0).get("schemeName").getFragments();


        System.out.println(Arrays.toString(schemeNames));


        CountRequest countRequest = new CountRequest("cs_index");
        countRequest.query(queryBuilder);
        final CountResponse count = client.count(countRequest, RequestOptions.DEFAULT);
        System.out.println(count);
        //提取有用的响应数据
    }

    @Test
    public void agesSearchIndex() throws IOException {
        // 指定创建时间
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);
        sourceBuilder.size(0);
//        AvgAggregationBuilder aggregation = AggregationBuilders.avg("ags_price").field("price");
//        SumAggregationBuilder aggregation = AggregationBuilders.sum("ags_price").field("price");
        TermsAggregationBuilder aggregation = AggregationBuilders.terms("terms_price").field("price");

        sourceBuilder.aggregation(aggregation);
        SearchRequest searchRequest = new SearchRequest("cs_index");
        searchRequest.source(sourceBuilder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        final Aggregations aggregations = search.getAggregations();

        Aggregation aggregation1 = aggregations.get("terms_price");
        List<? extends Terms.Bucket> buckets = ((Terms) aggregation1).getBuckets();
        Map<BigDecimal, Long> map = new HashMap<>();
        for (Terms.Bucket bucket : buckets) {
            map.put(new BigDecimal(bucket.getKeyAsString()).setScale(2, RoundingMode.FLOOR), bucket.getDocCount());
        }
        System.out.println(JSON.toJSONString(map));
    }


    @Test
    public void agesHistogramSearchIndex() throws IOException {
        // 指定创建时间
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);
        sourceBuilder.size(0);
        HistogramAggregationBuilder aggregation = AggregationBuilders.histogram("histogram_price").field("price").interval(4);

        sourceBuilder.aggregation(aggregation);
        SearchRequest searchRequest = new SearchRequest("cs_index");
        searchRequest.source(sourceBuilder);
        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        final Aggregations aggregations = search.getAggregations();

        Aggregation aggregation1 = aggregations.get("histogram_price");
        List<? extends Histogram.Bucket> buckets = ((Histogram) aggregation1).getBuckets();
        Map<BigDecimal, Long> map = new HashMap<>();
        for (Histogram.Bucket bucket : buckets) {
            map.put(new BigDecimal(bucket.getKeyAsString()).setScale(2, RoundingMode.FLOOR), bucket.getDocCount());
        }
        System.out.println(JSON.toJSONString(map));
    }


    /**
     * 索引是否存在
     *
     * @param indexName
     * @return
     */
    public boolean exists(String indexName) {
        GetIndexRequest request = new GetIndexRequest(indexName);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        try {
            return client.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除索引库
     */
    @Test
    public  void deleteIndexStores() {
        String indexName = "date_index";
        boolean exists = exists(indexName);
        if(!exists) {
            //不存在就结束
            return ;
        }
        //索引存在，就执行删除
        long s = System.currentTimeMillis();
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);

        request.timeout(TimeValue.timeValueMinutes(2));
        request.timeout("2m");
        try {
            AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long t = System.currentTimeMillis();
        //计算删除耗时
        System.out.println(t-s);
    }

    @Test
    public void updateIndex() throws IOException {
        UpdateRequest request = new UpdateRequest("cs_index", "1").doc(JSON.toJSONString(buildScheme()), XContentType.JSON);
        //3.构建数据 XContentType ====> 必须要指定内容类型
        //4.执行操作
        final UpdateResponse update = client.update(request, RequestOptions.DEFAULT);
        System.out.println(update);
        //提取有用的响应数据
    }

    @Test
    public void deleteIndex() throws IOException {
        DeleteRequest request = new DeleteRequest("cs_index", "4");
        //3.构建数据 XContentType ====> 必须要指定内容类型
        //4.执行操作
        final DeleteResponse update = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(update);
        //提取有用的响应数据
    }


    private Scheme buildScheme() {
        Scheme scheme = new Scheme();
        scheme.setId(21);
        scheme.setFormId(72);
        scheme.setBaseId(79);
        scheme.setCateId(541);
        scheme.setSchemeNo("AJ4010001");
        scheme.setSchemeName("铁观音_茶叶_铁观音_250g-四边封袋_多边形铁罐（内卷边）_精装盒-天地盖天包地盒_2盒装_250g_2罐装_防摔,密封,防水,避光,精装_四边封袋_国潮");
        scheme.setPackAttr("防摔,环保");
        scheme.setAdapter("uploads/file/package/202204/625668c727dc4/茶叶_铁观音_250g-四边封袋）_多边形铁罐（内卷边）_精装盒-抽屉盒单抽_2罐装.pdf");
        scheme.setCreateTime(new Date());
        scheme.setUpdateTime(new Date());
        scheme.setStyleId(5);
        scheme.setPackId(80);
        scheme.setSaleCount(14);
        scheme.setPrice(new BigDecimal("3.18"));

        List<Integer> categoryId = new ArrayList<>();
        categoryId.add(1);
        categoryId.add(2);
        categoryId.add(3);
        scheme.setCategoryId(categoryId);
        return scheme;
    }

    private Scheme buildScheme1() {
        Scheme scheme = new Scheme();
        scheme.setId(3);
        scheme.setFormId(54);
        scheme.setBaseId(42);
        scheme.setCateId(3);
        scheme.setSchemeNo("AO1030002");
        scheme.setSchemeName("面膜_果冻贴片式面膜_25g_7片装_防摔,环保_卷膜_设计师自选");
        scheme.setPackAttr("防摔,环保");
        scheme.setAdapter("uploads/file/package/202204/6253ef0d83105/面膜_果冻_25g-7片装_抽屉盒.pdf");
        scheme.setCreateTime(new Date());
        scheme.setUpdateTime(new Date());
        scheme.setStyleId(1);
        scheme.setPackId(43);
        scheme.setSaleCount(54);
        scheme.setPrice(new BigDecimal("6.18"));

        List<Integer> categoryId = new ArrayList<>();
        categoryId.add(1);
        categoryId.add(2);
        categoryId.add(3);
        scheme.setCategoryId(categoryId);
        return scheme;
    }

    private Scheme buildScheme2() {
        Scheme scheme = new Scheme();
        scheme.setId(69);
        scheme.setFormId(78);
        scheme.setBaseId(218);
        scheme.setCateId(146);
        scheme.setSchemeNo("AJ4010001");
        scheme.setSchemeName("破壁机_精装盒-忠才_100g_200盒装_真空,保冷,保温_书型盒_田园");
        scheme.setPackAttr("真空,保冷,保温");
        scheme.setAdapter("uploads/file/package/202204/626a7712626c5/手提袋.jpg");
        scheme.setCreateTime(new Date());
        scheme.setUpdateTime(new Date());
        scheme.setStyleId(4);
        scheme.setSaleCount(25);
        scheme.setPackId(219);
        scheme.setPrice(new BigDecimal("0.86"));

        List<Integer> categoryId = new ArrayList<>();
        categoryId.add(3);
        scheme.setCategoryId(categoryId);
        return scheme;
    }

    private Scheme buildScheme3() {
        Scheme scheme = new Scheme();
        scheme.setId(21);
        scheme.setFormId(72);
        scheme.setBaseId(79);
        scheme.setCateId(541);
        scheme.setSchemeNo("AJ4010001");
        scheme.setSchemeName("铁观音_茶叶_铁观音_250g-四边封袋_多边形铁罐（内卷边）_精装盒-天地盖天包地盒_2盒装_250g_2罐装_防摔,密封,防水,避光,精装_四边封袋_国潮");
        scheme.setPackAttr("防摔,环保");
        scheme.setAdapter("uploads/file/package/202204/625668c727dc4/茶叶_铁观音_250g-四边封袋）_多边形铁罐（内卷边）_精装盒-抽屉盒单抽_2罐装.pdf");
        scheme.setCreateTime(new Date());
        scheme.setUpdateTime(new Date());
        scheme.setStyleId(5);
        scheme.setPackId(80);
        scheme.setPrice(new BigDecimal("3.18"));

        List<Integer> categoryId = new ArrayList<>();
        categoryId.add(1);
        categoryId.add(2);
        categoryId.add(3);
        scheme.setCategoryId(categoryId);
        return scheme;
    }

}
