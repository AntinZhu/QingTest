package com.qingqing.test.learn.tree;

import com.qingqing.common.util.CollectionsUtil;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by zhujianxing on 2020/11/5.
 */
public class BackTraceArrTest {

    public static void main(String[] args) {
//        System.out.println(new BackTraceArrTest().verifyPostorder(new int[]{1,6,3,2,5}));
    }

    public boolean verifyPostorder(int[] postorder) {
        return isA(postorder, 0, postorder.length - 1, 0, Integer.MAX_VALUE);
    }

    private boolean isA(int[] arr, int start, int end, int min, int max){
        if(start > end){
            return true;
        }

        if (start == end){
            return arr[start] > min && arr[start] < max;
        }


        int root = arr[end];
        int i = end - 1;
        while (i >= start && arr[i] > root){
            i--;
        }

        return isA(arr, start, i, min, root) & isA(arr, i + 1, end - 1, root, max);
    }

    private boolean isB(int[] arr, int start, int end){
        if(start > end){
            return true;
        }

        int mid = start;
        while(mid < end && arr[mid] < arr[end]){
            mid++;
        }
        int j = mid;
        while (j < end && arr[j] > arr[end]){
            j++;
        }

        return j == end && isB(arr, start, mid - 1) && isB(arr, mid, end - 1);
    }
}
