package com.silence.order.infrastructure.struct;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *  DelayData
 * 
 * @author leo
 * @version 1.1.0
 * @date 2022/1/22
 */
public class DelayData implements Delayed {

    private String topic;
    private String body;
    //数据到期时间
    private long endTime;
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public DelayData(String topic,String body, long endTime) {
        this.topic = topic;
        this.body = body;
        //需要把传入的时间endTime 加上当前系统时间，作为数据的到期时间
        this.endTime = endTime + System.currentTimeMillis();
    }

    public DelayData() {
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.endTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        return o.getDelay(this.timeUnit) - this.getDelay(this.timeUnit) < 0 ? 1: -1;
    }

}