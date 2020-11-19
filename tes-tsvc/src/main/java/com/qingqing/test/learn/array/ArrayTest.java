package com.qingqing.test.learn.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhujianxing on 2020/11/12.
 */
public class ArrayTest {

    public static void main(String[] args) {
        System.out.println(new ArrayTest().maxSubArray(new int[]{-1}));
    }

    public int maxEnvelopes2(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int n = envelopes.length;
        int[] result = new int[n];

        int maxResult = 0;
        for(int i = 0; i < n; i++){
            int max = 0;
            for(int j = 0; j < i; j++){
                if(envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]){
                    max = Math.max(max, result[j]);
                }
            }
            result[i] = 1 + max;
            maxResult = Math.max(maxResult, result[i]);
        }

        return maxResult;
    }

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        return max(envelopes, 0, 0, 0);
    }

    private int max(int[][] envelopes, int start, int maxWidth, int maxHeight){
        if(start == envelopes.length){
            return 0;
        }

        if(envelopes[start][0] > maxWidth && envelopes[start][1] > maxHeight){
            return Math.max(max(envelopes, start + 1, maxWidth, maxHeight), 1 + max(envelopes, start + 1, envelopes[start][0], envelopes[start][1]));
        }else {
            return max(envelopes, start + 1, maxWidth, maxHeight);
        }
    }


    /*
    [
         [2],
        [3,4],
       [6,5,7],
      [4,1,8,3]
    ]

    [
     [2],
     [3,4],
     [6,5,7],
     [4,1,8,3]
    ]
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int tSize = triangle.size();
        int n = triangle.get(tSize - 1).size();
        int[] result = new int[n + 1];

        int lineIdx = tSize - 1;
        while (lineIdx >= 0){
            List<Integer> lineList = triangle.get(lineIdx);
            for(int i = 0; i <= lineIdx; i++){
                result[i] = lineList.get(i) + Math.min(result[i], result[i + 1]);
            }

            lineIdx--;
        }

        return result[0];
    }

    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int n = nums.length;
        int[] result = new int[n + 1];
        result[0] = 0;

        int maxValue = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            result[i + 1] = Math.max(result[i] + nums[i], nums[i]);
            maxValue = Math.max(maxValue, result[i + 1]);
        }

        return maxValue;
    }
}
