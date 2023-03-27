package com.silence.order.domain;


/**
 *  KafkaHandler
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/22
 */
public interface Handler {

    /**
     * @return
     */
    String getType();

    /**
     * @param msg
     */
    void handler(String msg);
}
