package com.qingqing.test.learn.integer;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by zhujianxing on 2020/11/12.
 */
public class CoinTest {

    public static void main(String[] args) {
        System.out.println(new CoinTest().coinChange(new int[]{1, 2, 5}, 11));
    }



    public int coinChange2(int[] coins, int amount) {
        if(amount == 0){
            return 0;
        }

        int[] resultArr = new int[amount + 1];
        Arrays.fill(resultArr, amount + 1);
        resultArr[0] = 0;
        for(int i = 1; i <= amount; i++){
            for(int coin : coins){
                if(coin <= i){
                    resultArr[i] = Math.min(resultArr[i], resultArr[i - coin] + 1);
                }
            }
        }

        return resultArr[amount] == amount + 1? -1 : resultArr[amount];
    }

    public int coinChange(int[] coins, int amount) {
        return innerCoinChange(coins, amount, new int[amount]);
    }

    private int innerCoinChange(int[] coins, int amount, int[] tmpResult){
        if(amount == 0){
            return 0;
        }

        if(tmpResult[amount - 1] != 0){
            return tmpResult[amount - 1];
        }

        int minCount = Integer.MAX_VALUE;
        for(int i = coins.length - 1; i >=0; i--){
            if(amount >= coins[i]){
                int count = 1 + innerCoinChange(coins, amount - coins[i], tmpResult);
                if(count >= 0 && minCount > count){
                    minCount = count;
                }
            }
        }

        tmpResult[amount - 1] = minCount == Integer.MAX_VALUE? -1 : minCount;

        return tmpResult[amount - 1];
    }
}
