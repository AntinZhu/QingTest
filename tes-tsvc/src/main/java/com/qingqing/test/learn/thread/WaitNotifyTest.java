package com.qingqing.test.learn.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 两个线程交替运行，被唤醒时，检查是否轮到自己执行。一开始指定开始执行的一方
 * Created by zhujianxing on 2020/10/28.
 */
public class WaitNotifyTest {

    public static void main(String[] args) {
        PrintA printA = new PrintA();
        PrintB printB = new PrintB();

        Object lock = new Object();

        AtomicReference<String> printType = new AtomicReference<>("A");

        Thread t1 = new Thread(() -> {
            int count = 26;
            while(count-- > 0){
                synchronized (lock){
                    while(!"A".equals(printType.get())){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }

                    printA.print();
                    printA.print();

                    printType.set("B");
                    lock.notifyAll();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            int count = 26;
            while(count-- > 0){
                synchronized (lock){
                    while (!"B".equals(printType.get())){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }

                    printB.print();

                    printType.set("A");
                    lock.notifyAll();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
        }

        try {
            t2.join();
        } catch (InterruptedException e) {
        }
    }

    static class PrintA{
        private int start;

        public PrintA(){
            this(1);
        }

        public PrintA(int start) {
            this.start = start;
        }

        public void print(){
            System.out.print(start);
            start++;
        }
    }

    static class PrintB{
        private char start;

        public PrintB(){
            this('A');
        }

        public PrintB(char start) {
            this.start = start;
        }

        public void print(){
            System.out.print(start);
            start++;
        }
    }
}
