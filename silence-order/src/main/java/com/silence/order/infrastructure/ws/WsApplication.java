package com.silence.order.infrastructure.ws;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@Component
public class WsApplication implements ApplicationContextAware {

    @Value("${ws.port}")
    private int port;
    private static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(
            1,
            2,
            60,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(3)
            , Executors.defaultThreadFactory()
            , new ThreadPoolExecutor.AbortPolicy()
    );

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        POOL_EXECUTOR.execute(new WebSocketServer(port));
    }
}
