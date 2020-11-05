package com.qingqing.test.learn.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zhujianxing on 2020/11/3.
 */
public class MoveCountTest {

    public static void main(String[] args) {
        System.out.println(new MoveCountTest().bfs(1, 2, 1));
    }

    public int movingCount(int m, int n, int k) {
        return dfs(m, n, 0, 0, k, new boolean[m][n]);
    }

    int[][] directs = new int[][]{{0, 1},{0 , -1},{1, 0},{-1, 0}};
    private int dfs(int m, int n, int i, int j, int k, boolean[][] accessArr){
        if(i < 0 || i >= m || j < 0 || j >=n || accessArr[i][j]){
            return 0;
        }
        accessArr[i][j] = true;

        if(gtK(i, j, k)){
            return 0;
        }

        int count = 1;
        for (int[] direct : directs) {
            count += dfs(m, n, i + direct[0], j + direct[1], k, accessArr);
        }

        return count;
    }

    private boolean gtK(int i, int j, int k){
        return (get(i) + get(j)) > k;
    }

    private int get(int i){
        int ret = 0;
        while(i != 0){
            ret += i % 10;
            i = i / 10;
        }

        return ret;
    }

    int[][] bfsDirects = new int[][]{{0, 1},{1, 0}};
    private int bfs(int m, int n, int k){
        Queue<int[]> nodeQueue = new LinkedList<>();
        nodeQueue.offer(new int[]{0, 0});

        int total = 1;
        boolean[][] accessArr = new boolean[m][n];
        accessArr[0][0] = true;
        while(!nodeQueue.isEmpty()){
            int[] node = nodeQueue.poll();
            for(int[] direct : bfsDirects){
                int x = node[0] + direct[0];
                int y = node[1] + direct[1];
                if(x >= m || y >=n || accessArr[x][y] || gtK(x, y, k)){
                    continue;
                }
                accessArr[x][y] = true;
                nodeQueue.offer(new int[]{x, y});
                total++;
            }
        }

        return total;
    }
}
