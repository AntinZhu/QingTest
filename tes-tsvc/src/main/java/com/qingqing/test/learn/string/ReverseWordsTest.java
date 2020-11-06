package com.qingqing.test.learn.string;

import java.util.Stack;

/**
 * Created by zhujianxing on 2020/11/6.
 */
public class ReverseWordsTest {

    public static void main(String[] args) {
        System.out.println(new ReverseWordsTest().reverseWords2( "  Bob    Loves  Alice   "));
    }

    public String reverseWords2(String s) {
        char[] sCharArr = s.toCharArray();
        // 整个字符串翻转
        reverseRange(sCharArr, 0, sCharArr.length - 1);
//        // 每个单词翻转
//        revertWord(sCharArr);

        // 处理空格：将单词以此
        int mergeLen = revertWordAndMerge(sCharArr);
        if(sCharArr[mergeLen - 1] == ' '){
            mergeLen--;
        }

        return new String(sCharArr, 0, mergeLen);
    }

    private int mergeBlank(char[] arr){
        int arrangeIdx = 0;
        int end = 0;
        while (end < arr.length){
            if(arr[end] != ' '){
                while (end < arr.length && arr[end] != ' '){
                    arr[arrangeIdx++] = arr[end++];
                }
                arr[arrangeIdx++] = ' ';
            }

            end++;
        }

        return arrangeIdx - 1;
    }
    private void revertWord(char[] arr){
        int start = -1;
        int end = 0;
        while (end < arr.length){
            if(arr[end] != ' '){
                if(start == -1){
                    start = end;
                }

                while (end < arr.length && arr[end] != ' '){end++;}
                reverseRange(arr, start, end - 1);
                start = -1;
            }

            end++;
        }
    }


    private int revertWordAndMerge(char[] arr){
        int start = -1;
        int end = 0;
        int arrangeIdx = 0;
        while (end < arr.length){
            if(arr[end] != ' '){
                if(start == -1){
                    start = end;
                }

                while (end < arr.length && arr[end] != ' '){end++;}
                reverseRange(arr, start, end - 1);
                // 翻转之后，直接移到前面去
                if(arrangeIdx != start){
                    while (start < end){
                        arr[arrangeIdx++] = arr[start++];
                    }
                    arr[arrangeIdx++] = ' ';
                }else {
                    arrangeIdx = end + 1;
                }
                start = -1;
            }

            end++;
        }

        return arrangeIdx;
    }

    private void reverseRange(char[] arr, int start, int end){
        while(start < end){
            char tmp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = tmp;
        }
    }

    public String reverseWords(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder(s.length());

        int idx = s.length() - 1;
        while(idx >= 0){
            if(s.charAt(idx) == ' '){
                if(!stack.isEmpty()){
                    while (!stack.isEmpty()){
                        result.append(stack.pop());
                    }
                    result.append(" ");
                }
            }else{
                stack.push(s.charAt(idx));
            }

            idx--;
        }

        if(stack.isEmpty()){
            result.deleteCharAt(result.length() - 1);
        }else{
            while (!stack.isEmpty()){
                result.append(stack.pop());
            }
        }

        return result.toString();
    }
}
