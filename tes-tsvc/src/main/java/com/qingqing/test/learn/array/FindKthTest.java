package com.qingqing.test.learn.array;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhujianxing on 2020/11/9.
 */
public class FindKthTest {

    public static void main(String[] args) {
//        System.out.println(new FindKthTest().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 10));
        System.out.println(new FindKthTest().longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int maxLen = 0;
        for(int num : nums){
            if(numSet.contains(num)){
                numSet.remove(num);

                int decCount = decCountAndRemove(num, numSet);
                int incCount = incCountAndRemove(num, numSet);
                maxLen = Math.max(decCount + 1 + incCount, maxLen);
            }

            if(maxLen > nums.length / 2){
                break;
            }
        }

        return maxLen;
    }

    private int decCountAndRemove(int num, Set<Integer> numSet){
        int count = 0;
        while (numSet.contains(--num)){
            numSet.remove(num);
            count++;
        }

        return count;
    }

    private int incCountAndRemove(int num, Set<Integer> numSet){
        int count = 0;
        while (numSet.contains(++num)){
            numSet.remove(num);
            count++;
        }

        return count;
    }

    public int findKthLargest(int[] nums, int k) {
        if(nums == null || k < 1 || k > nums.length){
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        int sortIdx =  - 1;
        while (sortIdx != k - 1){
            if(sortIdx > k - 1){
                end = sortIdx - 1;
            }else{
                start = sortIdx + 1;
            }
            sortIdx = sortArrange(nums, start, end);
        }

        return nums[sortIdx];
    }

    private int sortArrange(int[] nums, int start, int end){
        int num = nums[start];
        int left = start;
        int right = end;
        while(left < right){
            while(left < right && nums[right] < num){ right--;}
            if(left < right){
                nums[left] = nums[right];
                left++;
            }

            while(left < right && nums[left] > num){ left++;}
            if(left < right){
                nums[right] = nums[left];
                right--;
            }
        }
        nums[left] = num;

        return  left;
    }
}
