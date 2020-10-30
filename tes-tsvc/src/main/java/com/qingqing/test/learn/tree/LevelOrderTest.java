package com.qingqing.test.learn.tree;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhujianxing on 2020/10/30.
 */
public class LevelOrderTest {


    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
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
