package com.qingqing.test.learn.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujianxing on 2020/11/10.
 */
public class NQueueTest {

    public static void main(String[] args) {
//        List<List<String>> resultList = new NQueueTest().solveNQueens(4);
//        for (List<String> strings : resultList) {
//            for (String string : strings) {
//                System.out.println(string);
//            }
//            System.out.println("------------------");
//        }

        System.out.println(new NQueueTest().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(new NQueueTest().trap(new int[]{4,2,0,3,2,5}));
    }

    public List<List<String>> solveNQueens(int n) {
        boolean[][] place = new boolean[n][n];
        List<List<String>> resultList = new ArrayList<>();
        dfs(n, 0, place, resultList);

        return resultList;
    }


    private void dfs(int n, int line, boolean[][] place, List<List<String>> resultList){
        if(line == n){
            resultList.add(toResult(place));
            return;
        }

        for(int i = 0; i < n; i++){
            if(canPlace(line, i, n, place)){
                place[line][i] = true;
                dfs(n, line + 1, place, resultList);
                place[line][i] = false;
            }
        }
    }

    private boolean canPlace(int line, int idx, int n, boolean[][] place){
        for(int i = 0; i < n; i++){
            if(place[i][idx] || place[line][i]){
                return false;
            }


            if(idx + i < n && line + i < n && place[line + i][idx + i]){
                return false;
            }
            if(idx + i < n && line - i >=0 && place[line - i][idx + i]){
                return false;
            }
            if(idx - i >= 0 && line - i >=0 && place[line - i][idx - i]){
                return false;
            }
            if(idx - i >= 0 && line + i < n && place[line + i][idx - i]){
                return false;
            }
        }

        return true;
    }

    private List<String> toResult(boolean[][] place){
        List<String> result = new ArrayList<>(place.length);
        for (boolean[] booleans : place) {
            StringBuilder sb = new StringBuilder();
            for (boolean aBoolean : booleans) {
                sb.append(aBoolean? "Q" : ".");
            }
            result.add(sb.toString());
        }

        return result;
    }

    public int trap(int[] height) {
        int n = height.length;
        int[] leftMaxArr = new int[n];
        int[] rightMaxArr = new int[n];

        int leftMax = 0;
        int rightMax = 0;
        leftMaxArr[0] = 0;
        leftMaxArr[n - 1] = 0;
        for(int i = 1; i < n; i++){
            leftMax = Math.max(leftMax, height[i - 1]);
            leftMaxArr[i] = leftMax;

            rightMax = Math.max(rightMax, height[n - i]);
            rightMaxArr[n - i - 1] = rightMax;
        }

        int total = 0;
        for (int i = 0; i < n; i++){
            int value = Math.min(leftMaxArr[i], rightMaxArr[i]) - height[i];
            if(value > 0){
                total += value;
            }
        }

        return total;
    }
}
