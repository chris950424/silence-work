package com.algorithm.io.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Thread1 {

    private static final ThreadFactory NAMED_FACTORY = new ThreadFactoryBuilder().setNameFormat("线程-demo-%d").build();

    private static final ThreadPoolExecutor POOL = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new
            ArrayBlockingQueue<>(10), NAMED_FACTORY, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        POOL.execute(()->{
            System.out.println("aaaaa");
        });
    }

}