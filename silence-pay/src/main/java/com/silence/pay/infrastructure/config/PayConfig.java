package com.silence.pay.infrastructure.config;

import com.silence.pay.domain.PayService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * PayConfig
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/20
 */
@Configuration
public class PayConfig implements ApplicationContextAware {

    public final static Map<String, PayService> PAYS = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, PayService> beansOfType = applicationContext.getBeansOfType(PayService.class);
        beansOfType.forEach((s, payService) -> PAYS.put(payService.getPayType(), payService));
    }
}
