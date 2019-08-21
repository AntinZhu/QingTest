package com.qingqing.test.learn;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

/**
 * Created by zhujianxing on 2019/8/21.
 */
public class ExchangerTest {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<Integer> exchanger = new Exchanger<>();
        CountDownLatch latch = new CountDownLatch(2);
        Producer p1 = new Producer(latch, exchanger, "老大", 10);
        Producer p2 = new Producer(latch, exchanger, "老二", 20);

        new Thread(p1).start();
        new Thread(p2).start();
        latch.await();
    }

    static class Producer implements Runnable{

        private CountDownLatch latch;
        private Exchanger<Integer> exchanger;
        private String name;
        private int start;

        public Producer(CountDownLatch latch, Exchanger<Integer> exchanger, String name, int start) {
            this.latch = latch;
            this.exchanger = exchanger;
            this.name = name;
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++){
                try {
                    int data = start +i;
                    data = exchanger.exchange(data);
                    System.out.println(name + ":" + (start +i) + "->" + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            latch.countDown();;
        }
    }
}
