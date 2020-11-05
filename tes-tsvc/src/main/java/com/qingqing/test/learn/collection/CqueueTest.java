package com.qingqing.test.learn.collection;

import java.util.Stack;

/**
 * Created by zhujianxing on 2020/11/3.
 */
public class CqueueTest {

    static class CQueue {
        // 保存顺序
        Stack<Integer> s1 = new Stack<>();
        // 保存倒序
        Stack<Integer> s2 = new Stack<>();

        public CQueue() {

        }

        public void appendTail(int value) {
            s1.push(value);
        }

        public int deleteHead() {
            if(!s2.isEmpty()){
                return s2.pop();
            }else{
                while(!s1.isEmpty()){
                    s2.push(s1.pop());
                }

                if(!s2.isEmpty()){
                    return s2.pop();
                }
            }

            return -1;
        }
    }
}
