package com.silence.admin.infrastructure.feign;

import cn.hutool.json.JSONObject;
import com.silence.api.UserDto;
import com.silence.auth.util.EncryptUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 */
@Component
public class AuthFeignInterceptor implements RequestInterceptor {
    Log log = LogFactory.getLog(AuthFeignInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("Feign拦截器，拦截到Feign的Http请求发送，从当前Request的Header中获取AUTHORIZATION值为：{}" + accessToken, null);
        requestTemplate.header(HttpHeaders.AUTHORIZATION, accessToken);

        String token = request.getHeader("json-token");
        if (token != null) {
            requestTemplate.header("json-token", token);
        }
    }

}