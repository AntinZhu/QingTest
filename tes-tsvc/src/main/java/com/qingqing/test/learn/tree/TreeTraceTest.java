package com.qingqing.test.learn.tree;

import com.qingqing.test.learn.tree.LevelOrderTest.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zhujianxing on 2020/11/5.
 */
public class TreeTraceTest {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

    /*
    前序遍历
     */
    private List<Integer> preTrace(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> resultList = new ArrayList<>();
        if (root != null) {
            stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                resultList.add(root.val);

                if (root.right != null) {
                    stack.push(root.right);
                }

                if (root.left != null) {
                    stack.push(root.left);
                }
            }
        }

        return resultList;
    }

    /*
  中序遍历
   */
    private List<Integer> midTrace(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> resultList = new ArrayList<>();
        if (root != null) {
            stack.push(root);
            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    resultList.add(root.val);
                    root = root.right;
                }
            }
        }

        return resultList;
    }

    /*
  后序遍历
   */
    private List<Integer> backTrace(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        List<Integer> resultList = new ArrayList<>();

        if(root != null){
            stack1.push(root);
            while(!stack1.isEmpty()){
                TreeNode node = stack1.pop();
                stack2.push(node);
                if(node.left != null){
                    stack1.push(node.left);
                }

                if(node.right != null){
                    stack1.push(node.right);
                }
            }

            while(!stack2.isEmpty()){
                resultList.add(stack2.pop().val);
            }
        }

        return resultList;
    }
}