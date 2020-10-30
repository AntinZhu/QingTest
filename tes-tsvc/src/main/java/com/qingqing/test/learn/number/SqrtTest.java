package com.qingqing.test.learn.number;

/**
 * Created by zhujianxing on 2020/10/30.
 */
public class SqrtTest {

    /**
     *
     * @param x int整型
     * @return int整型
     */
    public int sqrt (int x) {
        // write code here
        if (x <= 0) {
            return 0;
        }

        long r = x;
        while (r > x / r) {
            r = (r + x / r) / 2;
        }
        return (int) r;
    }

    public static void main(String[] args) {
        System.out.print(new SqrtTest().sqrt(35));
    }
}
