package com.qingqing.test.learn.tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by zhujianxing on 2020/11/2.
 */
public class TreeUtils {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void printPre(TreeNode treeNode){

    }

    public List<Integer> inOrderTest(TreeNode root){
        List<Integer> resultList = new LinkedList<>();
        if(root == null){
            return resultList;
        }

        TreeNode node = root;
        Deque<TreeNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()){
            if(node != null){
                stack.push(node);
                node = node.left;
            }else{
                TreeNode pop = stack.pop();
                resultList.add(pop.val);

                node = pop.right;
            }
        }

        return resultList;
    }

    public List<Integer> postOrderTest(TreeNode root){
        LinkedList<Integer> resultList = new LinkedList<>();
        if(root == null){
            return resultList;
        }

        Deque<TreeNode> stack1 = new LinkedList<>();
        stack1.push(root);
        while (!stack1.isEmpty()){
            TreeNode node = stack1.pop();
            resultList.addFirst(node.val);

            if(node.left != null){
                stack1.push(node.left);
            }

            if(node.right != null){
                stack1.push(node.right);
            }
        }

        return resultList;
    }

    public List<Integer> preOrderTest(TreeNode root){
        List<Integer> resultList = new LinkedList<>();
        if(root == null){
            return resultList;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            resultList.add(node.val);

            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(-2);
        treeNode.right = new TreeNode(-3);

        System.out.println(hasPathSum(treeNode, -5));
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }

        if(root.left == null && root.right == null){
            return sum == root.val;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }

        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if(start > end){
            return null;
        }

        int midIdx = (start + end + 1)/2;
        TreeNode node = new TreeNode(nums[midIdx]);
        node.left = sortedArrayToBST(nums, start, midIdx - 1);
        node.right = sortedArrayToBST(nums, midIdx + 1, end);

        return node;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length == 0){
            return null;
        }

        Map<Integer, Integer> valudeIdxMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            valudeIdxMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, valudeIdxMap, 0, inorder.length);
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, Map<Integer, Integer> valueIdxMap, int inStart, int inEnd) {
        if(preStart > preEnd){
            return null;
        }

        int val = preorder[preStart];
        TreeNode node = new TreeNode(val);

        int valIdx = valueIdxMap.get(val);
        node.left = buildTree(preorder, preStart + 1, valIdx - inStart + preStart, valueIdxMap, inStart, valIdx - 1);
        node.right = buildTree(preorder, valIdx - inStart + preStart + 1, preEnd, valueIdxMap, valIdx + 1, inEnd);

        return node;
    }

    public TreeNode buildTree2(int[] postorder, int postStart, int postEnd, Map<Integer, Integer> valueIdxMap, int inStart, int inEnd) {
        if(postStart > postEnd){
            return null;
        }

        int val = postorder[postEnd];
        TreeNode node = new TreeNode(val);

        int valIdx = valueIdxMap.get(val);
        node.left = buildTree2(postorder, postStart, postStart + valIdx - inStart, valueIdxMap, inStart, valIdx - 1);
        node.right = buildTree2(postorder, postStart + valIdx - inStart + 1, postEnd - 1, valueIdxMap, valIdx + 1, inEnd);

        return node;
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }

        LinkedList<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while(!queue.isEmpty()){
            int queueSize = queue.size();

            for(int i = 0; i < queueSize; i++){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }

            if(!isSymmetric(queue)){
                return false;
            }
        }

        return true;
    }

    public boolean isSymmetric(LinkedList<TreeNode> list) {
        if(list.isEmpty()){
            return true;
        }

        int size = list.size();
        if(size % 2 == 1){
            return false;
        }


        int idx = 0;
        Iterator<TreeNode> pre = list.iterator();
        Iterator<TreeNode> last = list.descendingIterator();
        while (idx < size && pre.hasNext() && last.hasNext()){
            if(pre.next().val != last.next().val){
                return false;
            }

            idx+=2;
        }

        return  true;
    }
}
