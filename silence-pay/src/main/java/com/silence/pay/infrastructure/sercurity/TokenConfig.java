package com.silence.pay.infrastructure.sercurity;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


/**
 * @author Administrator
 * @version 1.0
 **/
@Configuration
public class TokenConfig {


    @Bean
    public TokenStore tokenStore() {
        //JWT令牌存储方案
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //对称秘钥，资源服务器使用该秘钥来验证
        converter.setVerifierKey(rsaPublicKey());
        return converter;
    }

    /**
     * 本地获取JWT验签公钥
     */
    @SneakyThrows
    @Bean
    public String rsaPublicKey() {
        String s = null;
        try {
            Resource resource = new ClassPathResource("public.key");
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            s = br.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {

        }
        return s;
    }

}
