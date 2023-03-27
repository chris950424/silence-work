package com.silence.elasticsearch.infrastructure.logging;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LogInterceptor loginInterceptor = new LogInterceptor();
        String[] path = {"/**"};
        registry.addInterceptor(loginInterceptor).addPathPatterns(path);
    }
}
