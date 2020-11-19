package com.qingqing.test.learn.array;

/**
 * Created by zhujianxing on 2020/11/12.
 */
public class ProfitTest {

    public static void main(String[] args) {
        System.out.println(new ProfitTest().maxProfit(new int[]{7,1,5,3,4, 6}));
    }

    public int maxProfit(int[] prices) {
        int result = 0;

        int buyIdx = -1;
        int sellIdx = -1;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > prices[i - 1]){
                result += prices[i] - prices[i - 1];
                if(buyIdx == -1){
                    buyIdx = i - 1;
                }

                if(i == prices.length - 1){
                    sellIdx = i;
                    System.out.println("[" + buyIdx + "," + sellIdx + "]");
                }
            }else{
                sellIdx = i -1;
                if(buyIdx != -1){
                    System.out.println("[" + buyIdx + "," + sellIdx + "]");
                    buyIdx = -1;
                }
            }
        }

        return result;
    }

}
