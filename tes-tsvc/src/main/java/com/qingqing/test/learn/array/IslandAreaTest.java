package com.qingqing.test.learn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * Created by zhujianxing on 2020/11/2.
 */
public class IslandAreaTest {

    public static void main(String[] args) {
//        int[][] arr = new int[][]{
//                {0, 0, 0, 1, 1, 0, 0},
//                {0, 1, 1, 1, 1, 0, 0},
//                {0, 1, 0, 1, 1, 0, 0},
//                {0, 1, 1, 1, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 1},
//                {0, 0, 0, 1, 1, 0, 0},
//                {0, 0, 0, 1, 1, 0, 0},
//                {0, 0, 0, 1, 1, 0, 0}
//        };
//
//        System.out.println(new IslandAreaTest().maxAreaOfIsland(arr));
//        System.out.println(new IslandAreaTest().dfsWithStack(arr));
//
//        char[][] charGrid = new char[][]{
//                {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
//                {'x', 'x', 'o', 'x', 'x', 'o', 'x'},
//                {'x', 'o', 'o', 'o', 'x', 'x', 'x'},
//                {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
//                {'x', 'x', 'o', 'o', 'x', 'x', 'o'},
//                {'x', 'x', 'x', 'o', 'x', 'x', 'x'},
//                {'x', 'o', 'o', 'o', 'x', 'x', 'x'},
//                {'x', 'o', 'x', 'x', 'x', 'x', 'x'},
//        };
//
//        new IslandAreaTest().solve(charGrid);

        char[][] charArr = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','1','1','1'}};

        char[][] arr2 = {{'0','0','0','1'},{'1','1','0','1'},{'1','1','1','1'},{'0','1','1','1'},{'0','1','1','1'}};

        System.out.println(new IslandAreaTest().maximalSquare(arr2));
    }

    public int maximalSquare(char[][] matrix) {
        if(matrix == null){
            return 0;
        }

        int n = matrix.length;
        int m = matrix[0].length;

        if(n == 0){
            return 0;
        }

        int[][] result = new int[n][m];
        int maxResult = 0;
        for(int i = 0; i < n; i++){
            result[i][0] = matrix[i][0] - '0';
            maxResult = Math.max(maxResult, result[i][0]);
        }
        for(int j = 0; j < m; j++){
            result[0][j] = matrix[0][j] - '0';
            maxResult = Math.max(maxResult, result[0][j]);
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(matrix[i][j] == '1'){
                    result[i][j] = Math.min(Math.min(result[i - 1][j - 1], result[i - 1][j]), result[i][j - 1]) + 1;
                    maxResult = Math.max(maxResult, result[i][j]);
                }
            }
        }

        ArrayUtils.print(result);

        return maxResult * maxResult;
    }

    private int maxLenWithDirect(char[][] matrix, int i, int j, int n, int m, int[] direct){
        int result = 0;
        while(i>=0 && i < n && j>=0 && j < m && matrix[i][j] == '1'){
            result++;
            i += direct[0];
            j += direct[1];
        }

        return result;
    }

    public int[][] merge(int[][] intervals) {
        Queue<Integer> queue = new LinkedList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });


        for (int i = 0; i < intervals.length; i++) {
            int min = intervals[i][0];
            int max = intervals[i][1];
            while (i + 1 < intervals.length && !(intervals[i + 1][0] > max || min > intervals[i + 1][1])){
                max = Math.max(max, intervals[i + 1][1]);
                i++;
            }
            queue.offer(min);
            queue.offer(max);
        }

        int[][] resultArr = new int[queue.size() / 2][2];
        int resultIdx = 0;
        while (!queue.isEmpty()){
            resultArr[resultIdx][0] = queue.poll();
            resultArr[resultIdx][1] = queue.poll();
            resultIdx++;
        }

        return resultArr;
}

    /*
    [
        [1,0,0,1],
        [0,1,1,0],
        [0,1,1,1],
        [1,0,1,1]]
     */

    public int findCircleNum(int[][] M) {
        int n = M.length;

        boolean[] visit = new boolean[n];
        int result = 0;
        for(int i = 0; i < n; i++) {
            if(visit[i]){
                continue;
            }
            visit[i] = true;
            dfsCircleNum(M, n, visit, i);
            result++;
        }

        return result;
    }

    private void dfsCircleNum(int[][] M, int n, boolean[] visit, int i){
        for (int j = 0; j < n; j++) {
            if(M[i][j] == 1 && !visit[j]){
                visit[j] = true;
                dfsCircleNum(M, n, visit, j);
            }
        }
    }

    public int findCircleNumBfs(int[][] M) {
        int n = M.length;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[n];
        int result = 0;
        for(int i = 0; i < n; i++) {
            if(visit[i]){
                continue;
            }

            queue.offer(i);
            visit[i] = true;
            while (!queue.isEmpty()){
                int item = queue.poll();
                for(int j = 0; j < n; j++){
                    if(M[item][j] == 1 && !visit[j]){
                        visit[j] = true;
                        queue.offer(j);
                    }
                }
            }
            result++;
        }

        return result;
    }

//    public int findCircleNum(int[][] M) {
//        int n = M.length;
//
//        Set<Integer> numSet = new HashSet<>();
//        for(int i = 0; i < n; i++){
//            numSet.add(i);
//        }
//
//        int result = 0;
//        while (!numSet.isEmpty()){
//            int item = numSet.iterator().next();
//            Stack<Integer> friendStack = new Stack<>();
//            friendStack.push(item);
//            while (!friendStack.isEmpty()){
//                int friend = friendStack.pop();
//                if(numSet.contains(friend)){
//                    // 将朋友的朋友中还没有查看朋友关系的放入friendStack继续遍历
//                    for(int i = 0; i < n; i++){
//                        if(i != friend && M[friend][i] == 1){
//                            if(numSet.contains(i)){
//                                friendStack.push(i);
//                            }
//                        }
//                    }
//                    numSet.remove(friend);
//                }
//            }
//
//            result++;
//        }
//
//        return result;
//    }

    public int count(int[][] grid){
        if(grid.length == 0){
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;
        boolean[][] accessArr = new boolean[n][m];

        int result = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1 && !accessArr[i][j]){
                    dfs3(grid, i, j, accessArr);
                    result++;
                }
            }
        }

        return result;
    }

    private void dfs3(int[][] grid, int i, int j, boolean[][] accessArr){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == 0 || accessArr[i][j]) {
            return;
        }

        accessArr[i][j] = true;
        for(int[] direct : directs){
            dfs3(grid, i + direct[0], j + direct[1], accessArr);
        }
    }

    public void solve(char[][] grid){
        if(grid.length == 0){
            return;
        }

        int n = grid.length;
        int m = grid[0].length;
        boolean[][] accessArr = new boolean[n][m];
        for(int i =0; i < n; i++){
            dfs2(grid, i, 0, accessArr);
            dfs2(grid, i, m - 1, accessArr);
        }

        for(int j =0; j < m; j++){
            dfs2(grid, 0, j, accessArr);
            dfs2(grid, n - 1, j, accessArr);
        }

        for(int i =0; i < n; i++){
            for(int j =0; j < m; j++){
                if(grid[i][j] == 'o'){
                    grid[i][j] = 'x';
                }else if(grid[i][j] == 'u'){
                    grid[i][j] = 'o';
                }
            }
        }

        ArrayUtils.print(grid);
    }

    private void dfs2(char[][] grid, int i, int j, boolean[][] accessArr){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == 'x' || accessArr[i][j]) {
            return;
        }

        grid[i][j] = 'u';
        accessArr[i][j] = true;
        for(int[] direct : directs){
            dfs2(grid, i + direct[0], j + direct[1], accessArr);
        }
    }



    // 四个方向
    public int[][] directs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0){
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;
        boolean[][] accessArr = new boolean[n][m];

        int result = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                result = Math.max(result, dfs(grid, i, j, accessArr));
            }
        }

        return result;
    }

    private int dfs(int[][] grid, int i, int j, boolean[][] accessArr) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == 0 || accessArr[i][j]){
            return 0;
        }

        int count = 1;
        accessArr[i][j] = true;
        for(int[] direct : directs){
            count += dfs(grid, i + direct[0], j + direct[1], accessArr);
        }

        return count;
    }

    private int dfsWithStack(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] access = new boolean[n][m];

        Stack<Integer> stackI = new Stack<>();
        Stack<Integer> stackJ = new Stack<>();

        int max = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if(grid[i][j] == 0 || access[i][j]){
                    continue;
                }

                stackI.push(i);
                stackJ.push(j);
                int count = 0;
                while (!stackI.isEmpty()){
                    int x = stackI.pop();
                    int y = stackJ.pop();
                    if(x < 0 || x == n || y < 0 || y == m || access[x][y] || grid[x][y] == 0){
                        continue;
                    }

                    access[x][y] = true;
                    count++;
                    for (int[] direct : directs) {
                        stackI.push(x + direct[0]);
                        stackJ.push(y + direct[1]);
                    }
                }

                max = Math.max(max, count);
            }

            return max;
        }


        return max;
    }
}
