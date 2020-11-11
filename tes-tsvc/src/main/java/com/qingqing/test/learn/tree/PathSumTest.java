package com.qingqing.test.learn.tree;

import com.qingqing.test.learn.tree.TreeUtils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Created by zhujianxing on 2020/10/30.
 */
public class PathSumTest {

    /**
     *
     * @param root TreeNode类
     * @param sum int整型
     * @return int整型ArrayList<ArrayList<>>
     */
    public ArrayList<ArrayList<Integer>> pathSum (TreeNode root, int sum) {
        // write code here
        ArrayList<Integer> pathLink = new ArrayList<>();
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();

        innerPathSum(root, sum, pathLink, resultList);

        return resultList;
    }

    private void innerPathSum(TreeNode node, int sum, ArrayList<Integer> valueStack, ArrayList<ArrayList<Integer>> resultList){
        if(node == null){
            return;
        }

        // 叶子节点
        if(node.left == null && node.right == null){
            if(sum == node.val) {
                valueStack.add(node.val);
                resultList.add(new ArrayList<>(valueStack));
                valueStack.remove(valueStack.size() - 1);
            }

            return;
        }else{
            valueStack.add(node.val);
            if(node.left != null){
                innerPathSum(node.left, sum - node.val, valueStack, resultList);
            }
            if(node.right!= null){
                innerPathSum(node.right, sum - node.val, valueStack, resultList);
            }
            valueStack.remove(valueStack.size() - 1);
        }
    }

}
