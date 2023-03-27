package com.silence.elasticsearch.infrastructure.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ElasticsearchConfiguration {
    @Value("${spring.elasticSearch.rest.uris}")
    String hosts;

    @Value("${spring.elasticSearch.rest..userName}")
    private String userName;

    @Value("${spring.elasticSearch.rest..password}")
    private String password;

    @Bean(name = "highLevelClient")
    public RestHighLevelClient highLevelClient(@Autowired RestClientBuilder restClientBuilder) {
        String[] hosts = this.hosts.split(",");
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        for(int i=0;i<hosts.length;i++) {
            String host = hosts[i].split(":")[0];
            int port = Integer.parseInt(hosts[i].split(":")[1]);
            httpHosts[i] = new HttpHost(host, port, "http");
        }

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));

        RestClientBuilder builder = RestClient.builder(httpHosts).setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                requestConfigBuilder.setConnectTimeout(-1);
                requestConfigBuilder.setSocketTimeout(-1);
                requestConfigBuilder.setConnectionRequestTimeout(-1);
                return requestConfigBuilder;
            }
        }).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                httpClientBuilder.disableAuthCaching();
                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
        });
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }


    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        int ulong = 10;
        List<Integer> prime = new ArrayList<Integer>();
        boolean[] isnp = new boolean[ulong];
        for (int i = 2; i < ulong; i++)
        {
            if (!isnp[i])
            {
                prime.add(i);
            }
            for (int j = 0; j < prime.size() && i * prime.get(j) < ulong; j++)
            {
                isnp[i * prime.get(j)] = true;
                if (i % prime.get(j) == 0)
                {
                    break;
                }
            }
        }
        System.out.println("last " + prime.get(prime.size()-1));
        long endTime = System.currentTimeMillis();
        long d = endTime - startTime;
        System.out.println("costTime " + d);

    }

}
