package com.qingqing.test.learn.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhujianxing on 2020/11/4.
 */
public class ConcatMinTest {

    public static void main(String[] args) {
        System.out.print(new ConcatMinTest().minNumber(new int[]{3,30,34,5,9}));
    }

    public String minNumber(int[] nums) {
        List<String> strList = new ArrayList<>();
        for (int num : nums) {
            strList.add(String.valueOf(num));
        }
        strList.sort((s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        StringBuilder sb = new StringBuilder();
        for (String str : strList) {
            sb.append(str);
        }
        return sb.toString();
    }
}
