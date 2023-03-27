package com.silence.pay.domain.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.silence.api.PayDto;
import com.silence.pay.domain.wechat.entity.Pay;
import com.silence.pay.domain.wechat.entity.WechatEnum;
import com.silence.pay.domain.wechat.repository.WechatRepository;
import com.silence.pay.domain.wechat.service.WechatPayService;
import com.silence.pay.infrastructure.proxy.anno.AllinPayAnno;
import com.silence.pay.infrastructure.proxy.anno.ExceptionHandleAnno;
import com.silence.pay.infrastructure.proxy.anno.Test2Anno;
import com.silence.pay.infrastructure.proxy.anno.TestAnno;
import com.silence.pay.infrastructure.util.wechatUtil.WxPayConstants;
import com.silence.pay.infrastructure.util.wechatUtil.WxPayUtil;
import com.silence.pay.infrastructure.wechat.WechatPayRequest;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import org.springframework.stereotype.Service;
import org.apache.http.client.HttpClient;


import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * WechatPayServiceImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/20
 */
@Service
public class WechatPayServiceImpl implements WechatPayService {

    @Resource
    WechatRepository repository;

    @Override
    public String getPayType() {
        return WechatEnum.WECHAT_PAY.getCode();
    }

    @Override
    @TestAnno(description = "1")
    @Test2Anno(description = "2")
    public String pay(Pay pay) {
        System.out.println("1111111111");
        return "1111";
//        return repository.pay(pay);
    }


    @Override
    @AllinPayAnno(description = "1")
    @ExceptionHandleAnno
    public Pay query(String orderNo) {
        return repository.query(orderNo);
    }


    @Override
    public Pay callBack(String sb) {
        return repository.callBack(sb);
    }
}
