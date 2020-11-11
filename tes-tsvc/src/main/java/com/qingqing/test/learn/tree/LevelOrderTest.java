package com.qingqing.test.learn.tree;


import com.qingqing.test.learn.tree.TreeUtils.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zhujianxing on 2020/10/30.
 */
public class LevelOrderTest {

    public int[] levelOrder2(TreeNode root) {
        if(root == null){
            return new int[]{};
        }

        List<Integer> resultList = new LinkedList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();

            resultList.add(node.val);
            if(node.left != null){
                nodeQueue.offer(node.left);
            }
            if(node.right != null){
                nodeQueue.offer(node.right);
            }
        }

        int[] resultAtt = new int[resultList.size()];
        int idx = 0;
        for (Integer integer : resultList) {
            resultAtt[idx++] = integer;
        }

        return resultAtt;
    }


    public int[] preOrder(TreeNode root) {
        if(root == null){
            return new int[]{};
        }

        Stack<TreeNode> stack=new Stack<TreeNode>();

        List<Integer> resultList = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();

            resultList.add(node.val);
            if(node.right != null){
                stack.push(root.right);
            }
            if(node.left != null){
                stack.push(root.left);
            }
        }

        int[] resultAtt = new int[resultList.size()];
        int idx = 0;
        for (Integer integer : resultList) {
            resultAtt[idx++] = integer;
        }

        return resultAtt;
    }

    public int[] midOrder(TreeNode root) {
        if(root == null){
            return new int[]{};
        }

        Stack<TreeNode> stack=new Stack<TreeNode>();

        List<Integer> resultList = new LinkedList<>();
        stack.push(root);
        while(root != null || !stack.isEmpty()){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                resultList.add(root.val);
                root = root.right;
            }
        }

        int[] resultAtt = new int[resultList.size()];
        int idx = 0;
        for (Integer integer : resultList) {
            resultAtt[idx++] = integer;
        }

        return resultAtt;
    }

    public int[] backOrder(TreeNode root) {
        if(root == null){
            return new int[]{};
        }

        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.push(root);
        while(!stack1.isEmpty()){
            root = stack1.pop();
            stack2.push(root);
            if(root.left != null){
                stack1.push(root.left);
            }
            if(root.right != null){
                stack1.push(root.right);
            }
        }

        int[] resultAtt = new int[stack2.size()];
        int idx = 0;
        while (!stack1.isEmpty()){
            resultAtt[idx++] = stack2.pop().val;
        }

        return resultAtt;
    }

    /**
     *
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList<>>
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write code here
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
        if(root == null){
            return resultList;
        }

        List<TreeNode> nodeList = Arrays.asList(root);
        while(!nodeList.isEmpty()){
            ArrayList<Integer> result = new ArrayList<>(nodeList.size());
            for (TreeNode treeNode : nodeList) {
                result.add(treeNode.val);
            }
            resultList.add(result);

            nodeList = traverseLevel(nodeList);
        }

        return resultList;
    }

    private List<TreeNode> traverseLevel(List<TreeNode> treeNodeList){
        List<TreeNode> subNodeResult = new ArrayList<>(treeNodeList.size() * 2);
        for (TreeNode treeNode : treeNodeList) {
            if(treeNode.left != null){
                subNodeResult.add(treeNode.left);
            }

            if(treeNode.right != null){
                subNodeResult.add(treeNode.right);
            }
        }

        return subNodeResult;
    }
}
