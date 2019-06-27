package com.sunbeam;

import java.util.concurrent.CountDownLatch;

public class MultiSum implements Runnable {

    private A sum;
    private int fromInt;
    private int toInt;
    private int threadNo;
    private CountDownLatch countDownLatch;

    public MultiSum(A sum, int fromInt, int toInt, int threadNo, CountDownLatch countDownLatch) {
        this.sum = sum;
        this.fromInt = fromInt;
        this.toInt = toInt;
        this.threadNo = threadNo;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        long current = System.currentTimeMillis();
        for (int i = fromInt; i <= toInt; i++) {
            this.sum.value += i;
        }
        current = System.currentTimeMillis() - current;
        System.out.println("Thread." + threadNo + " executes sum from " + fromInt + " to " + toInt + " in " + current
                + " milseconds. Sum is " + sum.value);
        countDownLatch.countDown();;
    }

    public static void main(String[] args) throws InterruptedException {
        Integer toMax = 100000000; // 计算1到100000000的和
        long sum = 0;
        int threads = 6; // 计算线程数
        // 每个线程计算一段连续的加和，并将加和结果保存在数组中。
        A[] subSum = new A[threads];
        for (int i = 0; i < threads; i++) {
            subSum[i] = new A(0);
        }
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        synchronized (subSum) {
            for (int i = 0; i < threads; i++) {
                int fromInt = toMax * i / threads + 1; // 边界条件
                int toInt = toMax * (i + 1) / threads; // 边界条件
                new Thread(new MultiSum(subSum[i], fromInt, toInt, i, countDownLatch)).start();
            }
        }
        countDownLatch.await();
        for (int i = 0; i < threads; i++) {
            sum += subSum[i].value;
        }
        System.out.println("The sum is :" + sum);
    }

}
