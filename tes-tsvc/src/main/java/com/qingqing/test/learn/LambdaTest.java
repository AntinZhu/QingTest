package com.qingqing.test.learn;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhujianxing on 2020/5/19.
 */
public class LambdaTest {


    /*
    语法格式：
        用逗号分隔的参数列表
        -> 符号
        和 语句块 组成
        Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
     */
    public static void main(String[] args) {
        Arrays.asList("1", "2", "3").forEach( mn -> {System.out.println(mn);});

        List<String> originList = Arrays.asList("1", "2", "3");
        originList.sort((a, b) -> b.compareTo(a));
        System.out.print("after sort:");
        originList.forEach(e  -> {System.out.println(e);});


    }
}
