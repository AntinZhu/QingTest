package com.qingqing.test.learn.array;

import java.util.Arrays;

/**
 * Created by zhujianxing on 2020/11/2.
 */
public class ArrayUtils {

    public static void print(int[][] arr){
        for(int i =0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println("--------------------------------");
    }

    public static void print(char[][] arr){
        for(int i =0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println("--------------------------------");
    }
}
