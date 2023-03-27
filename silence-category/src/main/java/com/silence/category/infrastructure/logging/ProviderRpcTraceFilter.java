//package com.silence.category.com.silence.elasticsearch.infrastructure.logging;
//
//import com.alibaba.dubbo.common.extension.Activate;
//import com.alibaba.dubbo.rpc.*;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.MDC;
//
//import java.util.UUID;
//
///**
// * @author wzd
// */
//@Activate(group = {"provider"}, order = 1)
//public class ProviderRpcTraceFilter implements Filter {
//    Log log = LogFactory.getLog(ProviderRpcTraceFilter.class);
//
//
//    @Override
//    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//        // 获得 RPC 方法名
//        String methodName = invoker.getUrl().getPath();
//        // 获得开始时间
//        long startTime = System.currentTimeMillis();
//        String traceId = RpcContext.getContext().getAttachment("traceId");
//        if (StringUtils.isBlank(traceId)) {
//            traceId = this.getUUID();
//        }
//        Object[] arguments = invocation.getArguments();
//        log.info("提供者拦截请求, traceId:" + traceId + " methodName: " + methodName + "startTime: " + startTime + " arguments: " + JSONObject.toJSONString(arguments));
//        //设置日志traceId变量
//        MDC.put("traceId", traceId);
//        RpcContext.getContext().setAttachment("traceId", traceId);
//        try {
//            Result result = invoker.invoke(invocation);
//            // 抛出的异常
//            Throwable exception = result.getException();
//            // 返回结果
//            Object value = result.getValue();
//            // 打印结束日志
//            if (exception != null) {
//                log.info("提供者拦截请求结束,traceId: " + traceId + "耗时:" + (System.currentTimeMillis() - startTime) + "毫秒,响应结果异常:" + exception.getMessage());
//            } else {
//                log.info("提供者拦截请求结束, traceId: " + traceId + "耗时:" + (System.currentTimeMillis() - startTime) + "毫秒,响应结果:" + JSONObject.toJSONString(value));
//            }
//            return result;
//        } finally {
//            MDC.remove("traceId");
//        }
//    }
//
//    /**
//     * 获取UUID
//     *
//     * @return String UUID
//     */
//    public String getUUID() {
//        String uuid = UUID.randomUUID().toString();
//        //替换-字符
//        return uuid.replaceAll("-", "");
//    }
//
//}
//
