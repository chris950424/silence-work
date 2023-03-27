package com.silence.api;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class MyThreadPoolExecutor extends ThreadPoolExecutor {
    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {

        System.out.println("before");
        super.beforeExecute(t, r);
    }

  static    ThreadPoolExecutor threadPoolExecutor = new MyThreadPoolExecutor(2, 4,
            10L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),
            new ThreadFactoryBuilder().setNameFormat("c_t_%d").build(), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("rejected");
        }
    });


    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("after");
        super.afterExecute(r, t);
    }

    public static void main(String[] args) {
        threadPoolExecutor.setCorePoolSize(3);
        threadPoolExecutor.execute(()->{
            System.out.println("execute");
        });
        int corePoolSize = threadPoolExecutor.getCorePoolSize();
        System.out.println(corePoolSize);
        Map<String,String> map = new HashMap<>();
    }

}
