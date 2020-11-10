package com.qingqing.test.learn.array;

/**
 * Created by zhujianxing on 2020/11/9.
 */
public class SearchTest {

    public static void main(String[] args) {
        System.out.println(new SearchTest().search(new int[]{5,1,2,3,4}, 5));
    }

    // nums = [4,5,6,7,0,1,2], target = 0
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }

        if(nums.length == 1){
            return nums[0] == target? 0 : -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                return mid;
            }else{
                if(nums[mid] >= nums[left]){
                    if(target < nums[mid] && target >= nums[left]){
                        right = mid - 1;
                    }else{
                        left = mid + 1;
                    }
                }else{
                    if(target > nums[mid] && target <= nums[right]){
                        left = mid + 1;
                    }else{
                        right = mid - 1;
                    }
                }
            }
        }

        return -1;
    }

}
