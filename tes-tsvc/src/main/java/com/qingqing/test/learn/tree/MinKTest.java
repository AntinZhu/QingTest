package com.qingqing.test.learn.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by zhujianxing on 2020/11/5.
 */
public class MinKTest {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

    private int findMinK(TreeNode root, int k){
        Stack<TreeNode> stack =  new Stack<>();
        int minCount = 0;
        while(root != null || !stack.isEmpty()){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                TreeNode node = stack.pop();
                if(++minCount == k){
                    return node.val;
                }
                root = node.right;
            }
        }

        return -1;
    }

    class FindResult{
        boolean find;
        int val;
        int count;

        public FindResult(boolean find, int count, int val) {
            this.find = find;
            this.count = count;
            this.val = val;
        }
    }

    private int findMinK2(TreeNode root, int k){
        FindResult result = innerFindMinK2(root, k);
        if(result.find){
            return result.val;
        }

        return -1;
    }

    private FindResult innerFindMinK2(TreeNode root, int k){
        if(root == null){
            return new FindResult(false, 0, -1);
        }

        FindResult left = innerFindMinK2(root.left, k);
        if(left.find){
            return left;
        }

        if(left.count == k - 1){
            return new FindResult(true, 0, root.val);
        }

        FindResult right = innerFindMinK2(root.right, k - left.count - 1);
        if(right.find){
            return right;
        }

        return new FindResult(false, left.count + right.count + 1, -1);
    }
}