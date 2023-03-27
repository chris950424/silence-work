//package com.silence.order.infrastructure.feign;
//
//import com.alibaba.fastjson.JSON;
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import io.seata.common.util.StringUtils;
//import io.seata.core.context.RootContext;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FeignRequestInterceptor implements RequestInterceptor {
//    Logger log = LoggerFactory.getLogger(FeignRequestInterceptor.class);
//
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        String seataXid= RootContext.getXID();
//        if (StringUtils.isNotBlank(seataXid)) {
//            requestTemplate.header(RootContext.KEY_XID,seataXid);
//        }
//        log.info("Feign拦截请求路径添加头部信息：{}", JSON.toJSONString(requestTemplate.headers()));
//    }
//}
