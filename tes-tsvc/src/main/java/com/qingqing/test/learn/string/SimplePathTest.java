package com.qingqing.test.learn.string;

import sun.awt.image.ImageWatched.Link;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zhujianxing on 2020/11/6.
 */
public class SimplePathTest {

    public static void main(String[] args) {
        System.out.println(new SimplePathTest().simplifyPath("/a/..//b////c/d//././/.."));
    }

    public String simplifyPath(String path) {
        String[] pathArr = path.split("/");
        LinkedList<String> pathQueue = new LinkedList<>();
        for (String p : pathArr){
            if(!"".equals(p) && !".".equals(p)){
                if("..".equals(p)){
                    pathQueue.removeLast();
                }else{
                    pathQueue.offer(p);
                }
            }
        }

        if(pathQueue.isEmpty()){
            return "/";
        }else{
            StringBuilder result = new StringBuilder();
            while (!pathQueue.isEmpty()){
                result.append("/" + pathQueue.pop());
            }

            return result.toString();
        }
    }
}
