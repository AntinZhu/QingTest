package com.qingqing.test.learn.array;

import java.util.Arrays;

/**
 * Created by zhujianxing on 2020/11/2.
 */
public class MaxConcatLenTest {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0, 0, 0, 1, 1, 0, 0},
                {0, 1, 0, 1, 1, 0, 0},
                {0, 1, 0, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0}
        };

        System.out.println(new MaxConcatLenTest().maxAreaOfIsland(arr));
    }

    // 四个方向
    public int[][] directs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1 , 0}};
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0){
            return 0;
        }
        int M = grid.length;
        int N = grid[0].length;
        boolean[][] access = new boolean[M][N];
        int ans = 0;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                ans = Math.max(ans, dfs(i, j, grid, access));
            }
        }
        return ans;
    }
    public int dfs(int i, int j, int[][] grid, boolean[][] access){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || access[i][j]){
            return 0;
        }
        int ans = 1;
        access[i][j] = true;
        for(int[] direct : directs){
            ans += dfs(i + direct[0], j + direct[1], grid, access);
        }
        return ans;
    }

    private static void print(int[][] arr){
        for(int i =0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
    }
}
