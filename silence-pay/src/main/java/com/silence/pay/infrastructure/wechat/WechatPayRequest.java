package com.silence.pay.infrastructure.wechat;


import com.alibaba.fastjson.JSON;
import com.silence.api.PayDto;
import com.silence.pay.infrastructure.util.wechatUtil.WxPayConstants;
import com.silence.pay.infrastructure.util.wechatUtil.WxPayUtil;
import com.silence.pay.infrastructure.wechat.DO.PayDO;
import com.silence.pay.infrastructure.wechat.config.WechatPayConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * WechatPayRequest
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/21
 */
public class WechatPayRequest {

    Logger logger = LoggerFactory.getLogger(WechatPayRequest.class);


    private final WechatPayConfig wechatPayConfig;

    public WechatPayRequest(WechatPayConfig wechatPayConfig) {
        this.wechatPayConfig = wechatPayConfig;
    }

    public HttpClient getHttpClient() {
        BasicHttpClientConnectionManager connManager;
        connManager = new BasicHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", SSLConnectionSocketFactory.getSocketFactory())
                        .build(),
                null,
                null,
                null
        );
        return HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();
    }


    public String doPost(String url, Map<String, String> requestDate, Map<String, String> headers) {
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(6 * 1000).setConnectTimeout(6 * 1000).build();
            httpPost.setConfig(requestConfig);
            requestDate.put("sign", WxPayUtil.generateSignature(requestDate, wechatPayConfig.getAppKey(), WxPayConstants.SignType.MD5));
            logger.info("调用url:{},调用参数：{}", url, requestDate);
            StringEntity postEntity = new StringEntity(WxPayUtil.mapToXml(requestDate), "UTF-8");

            headers.forEach(httpPost::addHeader);
            httpPost.setEntity(postEntity);

            HttpResponse httpResponse = null;
            httpResponse = getHttpClient().execute(httpPost);

            assert httpResponse != null;
            HttpEntity httpEntity = httpResponse.getEntity();
            String string = EntityUtils.toString(httpEntity, "UTF-8");
            logger.info("调用参数：{}", requestDate);
            logger.info("调用url:{},调用结果：{}", url, string);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Map<String, String> pay(PayDO pay) {
        pay.setBody("leo");
        Map<String, String> requestMap = buildRequestMap();
        requestMap.put("out_trade_no", pay.getOrderNo());
        requestMap.put("body", pay.getBody());
        requestMap.put("total_fee", pay.getAmount());
        requestMap.put("attach", JSON.toJSONString(pay.getAttach()));
        Map<String, String> headers = buildHeadersMap();
        try {
            Map<String, String> map = WxPayUtil.xmlToMap(doPost(wechatPayConfig.getDomain() + "/pay/unifiedorder", requestMap, headers));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, String> query(String orderNo) {
        Map<String, String> requestMap = buildRequestMap();
        requestMap.put("out_trade_no", orderNo);
        Map<String, String> headers = buildHeadersMap();
        try {
            Map<String, String> map = WxPayUtil.xmlToMap(doPost(wechatPayConfig.getDomain() + "/pay/orderquery", requestMap, headers));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, String> buildRequestMap() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("mch_id", wechatPayConfig.getMrchId());
        requestMap.put("appid", wechatPayConfig.getAppId());
        requestMap.put("trade_type", "NATIVE");
        requestMap.put("sign_type", wechatPayConfig.getSignType());
        requestMap.put("nonce_str", "5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
        requestMap.put("notify_url", wechatPayConfig.getNotifyUrl());
        return requestMap;
    }

    ;


    public Map<String, String> buildHeadersMap() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "com.silence.elasticsearch.application/json");
        headers.put("Accept", "com.silence.elasticsearch.application/json");
        return headers;
    }

    ;


    /**
     * 构建返回参数
     *
     * @param response {@link IJPayHttpResponse}
     * @return {@link Map}
     */
    public static Map<String, Object> buildResMap(IJPayHttpResponse response) {
        if (response == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>(6);
//        String timestamp = response.getHeader("Wechatpay-Timestamp");
//        String nonceStr = response.getHeader("Wechatpay-Nonce");
//        String serialNo = response.getHeader("Wechatpay-Serial");
//        String signature = response.getHeader("Wechatpay-Signature");
        String body = response.getBody();
        int status = response.getStatus();
//        map.put("timestamp", timestamp);
//        map.put("nonceStr", nonceStr);
//        map.put("serialNumber", serialNo);
//        map.put("signature", signature);
        map.put("body", body);
        map.put("status", status);
        return map;
    }

    public Map<String, String> callBack(String sb) {
        try {
            Map<String, String> map = WxPayUtil.xmlToMap(sb);
            String sign = map.remove("sign");
            String signature = WxPayUtil.generateSignature(map, wechatPayConfig.getAppKey(), WxPayConstants.SignType.MD5);
            if (signature.equals(sign)) {
                System.out.println(JSON.toJSONString(map));
                return map;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
