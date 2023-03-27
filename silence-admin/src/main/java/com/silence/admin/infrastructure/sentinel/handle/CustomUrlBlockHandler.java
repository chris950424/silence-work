package com.silence.admin.infrastructure.sentinel.handle;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@Service
public class CustomUrlBlockHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        httpServletResponse.setHeader("Content-Type","com.silence.elasticsearch.application/json;charset=UTF-8");
        String message = "{\"code\":999,\"msg\":\"请求人数过多\"}";
        httpServletResponse.getWriter().write(message);
    }
}
