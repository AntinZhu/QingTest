package com.qingqing.test.learn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhujianxing on 2020/11/6.
 */
public class ThreeTest {

    public static void main(String[] args) {
        List<List<Integer>> resultList = new ThreeTest().threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        resultList.stream().forEach(e -> System.out.println(e));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++){
            if(nums[i] > 0){
                break;
            }

            if(i > 0 && nums[i - 1] == nums[i]){
                continue;
            }

            int matchValue = -1 * nums[i];
            int j = i + 1;
            int k = nums.length - 1;
            while(j < k){
                int plusResult = nums[j] + nums[k];
                if(plusResult == matchValue){
                    resultList.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j <  k && nums[j + 1] == nums[j]){ j++;}
                    while (j <  k && nums[k - 1] == nums[k]){ k--;}
                    j++;
                    k--;
                }else if(plusResult < matchValue){
                    j++;
                }else{
                    k--;
                }
            }
        }

        return resultList;
    }

}
