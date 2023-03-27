package com.algorithm.io.feture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Feture1 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 1");
            try {
                Thread.sleep(1000);
                System.out.println("执行step 1...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "step1 result";
        }, executor);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 2");
            return "step2 result";
        },executor);
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 3");
            return "step3 result";
        },executor);

        CompletableFuture<Void> cf6 = CompletableFuture.allOf(cf1, cf2, cf3);
        CompletableFuture<String> result = cf6.thenApply(v -> {
            //这里的join并不会阻塞，因为传给thenApply的函数是在CF3、CF4、CF5全部完成时，才会执行 。
            String result1 = cf1.join();
            String  result2 = cf2.join();
            String result3 = cf3.join();
            System.out.println(result1 + " , " + result2+','+result3);
            countDownLatch.countDown();
            //根据result3、result4、result5组装最终result;
            return "result";
        });
        countDownLatch.await();
        System.out.println("a");
    }
}
