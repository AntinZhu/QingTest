package com.qingqing.test.learn.array;

/**
 * Created by zhujianxing on 2020/11/3.
 */
public class ExistWordTest {


    /*
    请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。

    [["a","b","c","e"],
    ["s","f","c","s"],
    ["a","d","e","e"]]

    但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。

    示例 1：

    输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
    输出：true
    示例 2：

    输入：board = [["a","b"],["c","d"]], word = "abcd"
    输出：false

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        String word = "ABCESEEEFS";

        System.out.print(new ExistWordTest().exist(board, word));
    }

    public boolean exist(char[][] board, String word) {
        if(word == null || word == ""){
            return false;
        }

        char firstChar = word.charAt(0);
        int n = board.length;
        int m = board[0].length;
        for(int i =0; i < n; i++){
            for(int j =0; j < m; j++){
                if(board[i][j] == firstChar){
                    boolean result = find(board, i, j, 0, word, new boolean[n][m]);
                    if(result){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    int[][] directs = new int[][]{{0, 1},{0, -1},{1, 0},{-1, 0}};
    private boolean find(char[][] numbers, int i, int j, int charIdx, String work, boolean[][] accessArr){
        if(i < 0 || i >= numbers.length || j < 0 || j >= numbers[0].length || accessArr[i][j] || work.charAt(charIdx) != numbers[i][j]){
            return false;
        }

        charIdx++;
        if(charIdx == work.length()){
            return true;
        }
        accessArr[i][j] = true;
        for (int[] direct : directs){
            boolean result = find(numbers, i + direct[0], j + direct[1], charIdx, work, accessArr);
            if(result){
                return result;
            }
        }

        accessArr[i][j] = false;
        return false;
    }
}
