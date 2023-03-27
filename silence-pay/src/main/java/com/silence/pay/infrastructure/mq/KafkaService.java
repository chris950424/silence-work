package com.silence.pay.infrastructure.mq;

/**
 *  KafkaService
 * 
 * @author leo
 * @version 1.1.0
 * @date 2022/1/22
 */
public interface KafkaService {
    
    void send(String s, String s1);
}
