package com.qingqing.test.learn.array;

/**
 * Created by zhujianxing on 2020/11/2.
 */
public class IslandAreaTest {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0, 0, 0, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 1, 0, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0}
        };

//        System.out.println(new IslandAreaTest().maxAreaOfIsland(arr));
        System.out.println(new IslandAreaTest().count(arr));

        char[][] charGrid = new char[][]{
                {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'x', 'x', 'o', 'x', 'x', 'o', 'x'},
                {'x', 'o', 'o', 'o', 'x', 'x', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'x', 'x', 'o', 'o', 'x', 'x', 'o'},
                {'x', 'x', 'x', 'o', 'x', 'x', 'x'},
                {'x', 'o', 'o', 'o', 'x', 'x', 'x'},
                {'x', 'o', 'x', 'x', 'x', 'x', 'x'},
        };

        new IslandAreaTest().solve(charGrid);
    }

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
}
