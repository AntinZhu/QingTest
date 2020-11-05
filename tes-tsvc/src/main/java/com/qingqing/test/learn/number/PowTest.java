package com.qingqing.test.learn.number;

/**
 * Created by zhujianxing on 2020/11/5.
 */
public class PowTest {

    public static void main(String[] args) {
        System.out.print(new PowTest().pow(2.00, -2147483648));
    }

    double pow(double x, int n){
        long b = n;
        if(b < 0){
            x = 1/x;
            b = -1 * b;
        }

        double result = 1.0;
        double halfResult = x;
        while(b > 0){
            if((b & 1) == 1){
                result *= halfResult;
            }

            halfResult *= halfResult;
            b >>= 1;
        }

        return result;
    }
}
