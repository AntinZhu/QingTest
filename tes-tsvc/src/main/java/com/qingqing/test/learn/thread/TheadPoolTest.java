package com.qingqing.test.learn.thread;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhujianxing on 2020/11/23.
 */
public class TheadPoolTest {

    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10) {
            @Override
            public boolean offer(Runnable runnable) {
                return false;
            }
        };

        ExecutorService pool = new ThreadPoolExecutor(3, 5, 20, TimeUnit.SECONDS, queue, new ThreadFactory() {
            private AtomicInteger number = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                String threadName = "test-pool-" + number.incrementAndGet();
                System.out.println("create new thread pool:" + threadName);
                return new Thread(r, threadName);
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                try {
                    boolean result = queue.offer(r, 2, TimeUnit.SECONDS);
                    System.out.println("reject to  queue, result:" + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        for (int i = 0; i < 20; i++){
            final int iValue = i;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("task-" + (iValue + 1) +" finish");
                }
            });
        }

        pool.shutdown();
    }
}
