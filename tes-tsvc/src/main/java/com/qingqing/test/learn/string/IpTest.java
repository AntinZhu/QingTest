package com.qingqing.test.learn.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zhujianxing on 2020/11/6.
 */
public class IpTest {

    public static void main(String[] args) {
        String sa = "25525511135";
        System.out.println(Arrays.toString(new IpTest().restoreIpAddresses(sa).toArray()));
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> resultList = new ArrayList<>();
        if(s.length() > 12){
            return resultList;
        }

        match(s, 0, "", resultList, 4);

        return resultList;
    }

    private void match(String s, int idx, String resultStr, List<String> resultList, int count){
        if(idx >= s.length() || s.length() - idx > 3 * count || s.length() - idx < count){
            return;
        }

        if(count == 1){
            if(s.length() - idx == 1 || s.charAt(idx) != '0') {
                String rest = s.substring(idx);
                if(Integer.valueOf(rest) <= 255){
                    resultStr += rest;
                    resultList.add(resultStr);
                }
            }
            return;
        }

        match(s, idx + 1, resultStr + String.valueOf(s.charAt(idx)) + ".", resultList, count - 1);
        if(s.charAt(idx) != '0'){
            if(s.length() - idx > 2){
                match(s, idx + 2, resultStr + s.substring(idx, idx + 2) + ".", resultList, count - 1);
            }

            if(s.length() - idx > 3){
                String threeStr = s.substring(idx, idx + 3);
                Integer threeValue = Integer.valueOf(threeStr);
                if(threeValue <= 255){
                    match(s, idx + 3, resultStr + threeValue + ".", resultList, count - 1);
                }
            }
        }
    }
}
