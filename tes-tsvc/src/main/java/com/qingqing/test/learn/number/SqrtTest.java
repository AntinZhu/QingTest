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
        System.out.print(new SqrtTest().sqrt2(4, 2, 7));
    }


    private double sqrt2(int x, double existResult, int scaleCount){
        double result = existResult;
        while (scaleCount-- > 0){
            int nextNum = matchNextGt(x, result, 0, 9);
            result = Double.valueOf(String.valueOf(result) + (nextNum - 1));
        }

        return result;
    }

    /**
     * 找第一个相乘大于x的位数
     */
    private Integer matchNextGt(int x, double existResult, int start, int end){
        if(start == end){
            return start;
        }

        int mid = (start + end) / 2;
        double midResult = Double.valueOf(String.valueOf(existResult) + mid);
        if(midResult * midResult > x){
            return matchNextGt(x, existResult, start, mid);
        }else{
            return matchNextGt(x, existResult, mid + 1, end);
        }
    }
}
