package com.silence.pay.infrastructure.mq.impl;

import com.silence.pay.infrastructure.mq.KafkaService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * @author Administrator
 */
@Service
public class KafkaServiceImpl implements KafkaService {
    Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);

    KafkaProducer<String, String> producer;


    /**
     * 主题
     */
    @PostConstruct
    public void init() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "120.24.205.36:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "producer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //连接的字符串
        //通过工厂
        //new
        producer = new KafkaProducer<>(properties);
    }

    @Override
    public void send(String topic, String body) {
        producer.send(new ProducerRecord<>(topic, body), (metadata, exception) -> {
            logger.info("偏移量{},分片{},主题{},请求参数{}", metadata.offset(), metadata.partition(), metadata.topic(), body);
        });
    }

}
