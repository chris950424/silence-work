package com.silence.order.domain.handler.config;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.silence.order.domain.Handler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KafkaConfig
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/22
 */
@Configuration
public class KafkaConfig implements ApplicationContextAware {

    public final static Map<String, Handler> HANDLERS = new ConcurrentHashMap<>();
    public final static Set<String> TOPICS = new ConcurrentHashSet<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Handler> beansOfType = applicationContext.getBeansOfType(Handler.class);
        beansOfType.forEach((s, handler) -> {
            HANDLERS.put(handler.getType(), handler);
            TOPICS.add(handler.getType());
        });
    }
}
