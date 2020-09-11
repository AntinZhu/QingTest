package com.qingqing.test.learn.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * Created by zhujianxing on 2020/8/27.
 */
public class StringTest {

    public static void main(String[] args) {
        Joiner joiner = Joiner.on("-").skipNulls();
        String afterJoin1 = joiner.join("1", null, "2", "3");
        System.out.println(afterJoin1);

        joiner = Joiner.on("-").useForNull("nil");
        String afterJoin2 = joiner.join("1", null, "2", "3");
        System.out.println(afterJoin2);

        Splitter splitter = Splitter.on("-");
        Iterable<String> afterSplit1 = splitter.split(afterJoin1);
        System.out.println(afterSplit1);

        splitter.trimResults(CharMatcher.DIGIT);
        Iterable<String> afterSplit2 = splitter.split(afterJoin1);
        System.out.println(afterSplit2);


        //CaseFormat
        //Charsets
        //CharMatcher
    }
}
