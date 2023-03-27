package com.silence.order.ui.mq;

import com.silence.order.domain.Handler;
import com.silence.order.infrastructure.struct.DelayData;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Properties;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;

import static com.silence.order.domain.handler.config.KafkaConfig.HANDLERS;
import static com.silence.order.domain.handler.config.KafkaConfig.TOPICS;

/**
 * KafkaConsumer
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/22
 */
//@Component
public class KafkaListener implements ApplicationContextAware {
    Logger logger = LoggerFactory.getLogger(KafkaListener.class);
    private final DelayQueue<DelayData> DELAY_QUEUE= new DelayQueue<DelayData>();

    private static final Executor MESSAGE_EXECUTOR = new ThreadPoolExecutor(
            2,
            5,
            3,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    private static final Executor SINGLE = new ThreadPoolExecutor(
            1,
            1,
            3,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    KafkaConsumer<String, String> consumer;

    public void init() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "120.24.205.36:9092");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, RandomStringUtils.random(5, true, true));
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //一个新的group的消费者去消费一个topic
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(TOPICS);
    }

    public void run() {
        init();
        for (; ; ) {
            try {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
                consumerRecords.forEach(record -> {
                        String topic = record.topic();
                        logger.info("message:topic{},body{}", record.topic(), record.value());
                        MESSAGE_EXECUTOR.execute(() -> {
                            try {
                                Handler handler = HANDLERS.get(topic);
                                handler.handler(record.value());
                            } catch (Exception e) {
                                DelayData delayData = new DelayData();
                                delayData.setTopic(topic);
                                delayData.setBody(record.value());
                                DELAY_QUEUE.add(delayData);
                            }
                        });
                });
            } catch (Exception e) {
                break;
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SINGLE.execute(this::run);
    }
}
