package com.qingqing.test.learn.string;

/**
 * Created by zhujianxing on 2020/11/5.
 */
public class MatchTest {

    /*
    s = "aab"
    p = "c*a*b"
    输出: true

    i = s.length() - 1;
    j = p.length() - 1;
    if(p.char){
    }
     */

    public static void main(String[] args) {
        System.out.print(new MatchTest().isMatch("aa", "a"));
    }


    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] matchResult = new boolean[n + 1][m + 1];
        matchResult[0][0] = true;
        for(int j = 0; j < n; j++){
            char pChar = p.charAt(j);
            if(pChar == '*'){
                matchResult[j + 1][0] = matchResult[j + 1 - 2][0];
            }
        }

        /*
         s = "aab"
        p = "c*a*b"
        matchResult[i + 1][j + 1] =
                    pChar = a-z : pChar == sChar && matchResult[i][j]
                    pChar = "." : matchResult[i][j]
                    pChar = "*":
                        if(!isCharMatch(sChar, p.charAt(i - 1))){
                            matchResult[i - 1][j + 1]
                        }else{
                            // *前面的字符与当前匹配
                            matchResult[i][j + 1] || matchResult[i + 1][j] || matchResult[i - 1][j + 1]
                             匹配一个                        匹配全部                不匹配
                        }


         */
        for(int i = 0; i <n; i++){
            char pChar = p.charAt(i);
            for(int j = 0; j <m; j++){
                char sChar = s.charAt(j);
                if(isCharMatch(sChar, pChar)){
                    matchResult[i + 1][j + 1] = matchResult[i][j];
                }else if(pChar == '*'){
                    if(!isCharMatch(sChar, p.charAt(i - 1))){
                        matchResult[i + 1][j + 1] = matchResult[i - 1][j + 1];
                    }else{
                        // 当前字符与*之前的字符相匹配
                        matchResult[i + 1][j + 1] = matchResult[i - 1][j + 1] || matchResult[i][j + 1] || matchResult[i + 1][j];
//                                                      不匹配                     匹配一个                    全部匹配
                        // 全部匹配包含匹配一个
                    }
                }
            }
        }

        return matchResult[n][m];
    }

    private boolean isCharMatch(char s, char p){
        return s == p || p == '.';
    }

}
