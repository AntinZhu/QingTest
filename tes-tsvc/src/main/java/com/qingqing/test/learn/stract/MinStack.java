package com.qingqing.test.learn.stract;

import java.util.Stack;

/**
 * Created by zhujianxing on 2020/11/12.
 */
public class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty()){
            minStack.push(x);
        }else{
            minStack.push(x > minStack.peek()? minStack.peek() : x);
        }
    }

    public void pop() {
        if(stack.isEmpty()){
           return;
        }
        stack.pop();
        minStack.pop();
    }

    public int top() {
        if(stack.isEmpty()){
            return -1;
        }

        return stack.peek();
    }

    public int getMin() {
        if(minStack.isEmpty()){
            return  -1;
        }
        return minStack.peek();
    }

}
