package com.qingqing.test.learn.string;

import com.qingqing.test.learn.array.ArrayUtils;

import java.util.Arrays;

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
        System.out.print(new MatchTest().isMatch3("abcabczzzde", "*abc???de*"));
    }

    public boolean is(String s, String p){
        int[][] matchResult = new int[s.length() + 1][p.length() + 1];
        return isMatch(s, s.length() - 1, p, p.length() - 1, matchResult);
    }

    /*
    递归解法
     */
    public boolean isMatch(String s, int sIdx, String p, int pIdx){
        if(pIdx < 0){
            return sIdx < 0;
        }

        if(sIdx < 0){
            if(p.charAt(pIdx) == '*'){
                return isMatch(s, sIdx, p, pIdx - 2);
            }else{
                return false;
            }
        }

        if(isMatch(s.charAt(sIdx), p.charAt(pIdx))){
            return isMatch(s, sIdx - 1, p, pIdx - 1);
        }else if(p.charAt(pIdx) == '*'){
            if(!isMatch(s.charAt(sIdx), p.charAt(pIdx - 1))){
                return isMatch(s, sIdx, p, pIdx - 2);
            }else{
                return isMatch(s, sIdx, p, pIdx - 2) || isMatch(s, sIdx - 1, p, pIdx);
            }
        }

        return false;
    }

    /*
    递归解法+记忆法
     */
    public boolean isMatch(String s, int sIdx, String p, int pIdx, int[][] matchResult){
        if(matchResult[sIdx + 1][pIdx + 1] != 0){
            return matchResult[sIdx + 1][pIdx + 1] == 1;
        }

        boolean result = false;
        if(pIdx < 0){
            result = sIdx < 0;
        }else if(sIdx < 0){
            if(p.charAt(pIdx) == '*'){
                result = isMatch(s, sIdx, p, pIdx - 2);
            }else{
                result = false;
            }
        }else{
            if(isMatch(s.charAt(sIdx), p.charAt(pIdx))){
                result = isMatch(s, sIdx - 1, p, pIdx - 1);
            }else if(p.charAt(pIdx) == '*'){
                if(!isMatch(s.charAt(sIdx), p.charAt(pIdx - 1))){
                    result = isMatch(s, sIdx, p, pIdx - 2);
                }else{
                    result = isMatch(s, sIdx - 1, p, pIdx);
                }
            }
        }

        matchResult[sIdx + 1][pIdx + 1] = result? 1 : -1;

        return result;
    }

    /*
    由递归+记忆化的方式，来使用动态规划
     */
    public boolean isMatch2(String s, String p){
        int n = p.length();
        int m = s.length();

        boolean[][] result = new boolean[n + 1][m + 1];
        result[0][0] = true;
        for(int i = 1; i <= n; i++){
            if(p.charAt(i - 1) == '*'){
                result[i][0] = result[i - 2][0];
            }
        }

        for (int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(isMatch(s.charAt(j), p.charAt(i))){
                    result[i + 1][j + 1] = result[i][j];
                }else if(p.charAt(i) == '*'){
                    if(isMatch(s.charAt(j), p.charAt(i - 1))){
                        result[i + 1][j + 1] = result[i + 1][j] || result[i - 1][j + 1];
                    }else{
                        result[i + 1][j + 1] = result[i - 1][j + 1];
                    }
                }
            }
        }

        ArrayUtils.print(Arrays.stream(result));

        return result[n][m];
    }

    public boolean isMatch3(String s, String p) {
        int n = p.length();
        int m = s.length();
        boolean[][] result = new boolean[n + 1][m + 1];
        result[0][0] = true;
        for(int i = 1; i <= n; i++){
            if(p.charAt(i - 1) == '*'){
                result[i][0] = result[i - 1][0];
            }
        }

        for (int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(isMatch2(s.charAt(j), p.charAt(i))){
                    result[i + 1][j + 1] = result[i][j];
                }else if(p.charAt(i) == '*'){
                    result[i + 1][j + 1] = result[i][j + 1] || result[i + 1][j];
                }
            }
        }

        ArrayUtils.print(Arrays.stream(result));

        return result[n][m];
    }

    private boolean isMatch2(char s, char p){
        return s == p || p == '?';
    }

    private boolean isMatch(char s, char p){
        return s == p || p == '.';
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

    public int subMatch(String a, String b){
        if(a == null || b == null || a.length() == 0 || b.length() == 0){
            return 0;
        }

        int aLen = a.length();
        int bLen = b.length();
        int[][] resultArr = new int[aLen + 1][bLen + 1];

        int maxResult = 0;
        for(int i = 0; i < aLen; i++){
            char aChar = a.charAt(i);
            for(int j = 0; j < bLen; j++){
                if(aChar == b.charAt(j)){
                    resultArr[i + 1][j + 1] = resultArr[i][j] + 1;
                    maxResult = Math.max(maxResult, resultArr[i + 1][j + 1]);
                }
            }
        }

        return maxResult;
    }

    private boolean isMatchTest(String s, String p){
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] matchResult = new boolean[sLen + 1][pLen + 1];
        matchResult[0][0] = true;
        for(int i = 0; i < p.length(); i++){
            char pChar = p.charAt(i);
            if(pChar == '*'){
                matchResult[0][i + 1] = matchResult[0][i - 1];
            }
        }

        for(int i = 0; i < sLen; i++) {
            char sChar = s.charAt(i);
            for (int j = 0; j < pLen; j++) {
                if(isCharMatchTest(sChar, p.charAt(j))){
                    matchResult[i + 1][j + 1] = matchResult[i][j];
                }else if(p.charAt(j) == '*'){
                    // 默认等于不匹配
                    matchResult[i + 1][j + 1] = matchResult[i + 1][j - 1];
                    if(sChar == p.charAt(j - 1)){
                        matchResult[i + 1][j + 1] |= matchResult[i][j + 1];
                    }
                }
            }
        }

        return matchResult[sLen][pLen];
    }

    private boolean isCharMatchTest(char s, char p){
        return s == p || p == '.';
    }
}
