package com.qingqing.test.learn.array;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by zhujianxing on 2020/11/2.
 */
public class ArrayUtils {

    public static <T> void print(Stream<T> stream){
        stream.forEach(e -> {
            int len = Array.getLength(e);
            System.out.print("[");
            for(int i = 0; i < len; i++){
                System.out.print(Array.get(e, i) + ",");
            }
            System.out.println("]");
        });
        System.out.println("--------------------------------");
    }

    public static void print(int[][] arr){
        for(int i =0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println("--------------------------------");
    }

    public static void print(boolean[][] arr){
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

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"4","13","5","/","+"}));
    }

    public static int coinChange(int[] coins, int amount){
        if(coins == null || coins.length == 0){
            return -1;
        }

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount +1);
        dp[0] = 0;

        for(int i = 1; i <= amount; i++){
            for(int coin : coins){
                if(i >= coin){
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == amount + 1? -1 : dp[amount];
    }

    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();

        Set<String> CALC_SET = new HashSet<>();
        CALC_SET.addAll(Arrays.asList("+", "-","*", "/"));
        for (String token : tokens) {
            if(CALC_SET.contains(token)){
                int second = stack.pop();
                int first = stack.pop();
                switch (token){
                    case "+":
                        stack.push(first + second);
                        break;
                    case "-":
                        stack.push(first - second);
                        break;
                    case "*":
                        stack.push(first * second);
                        break;
                    case "/":
                        stack.push(first / second);
                        break;
                    default:
                        throw new RuntimeException("unknown token for value:" + token);
                }
            }else{
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}
