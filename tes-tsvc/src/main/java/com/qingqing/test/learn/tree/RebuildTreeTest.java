package com.qingqing.test.learn.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhujianxing on 2020/11/2.
 */
public class RebuildTreeTest {

    public static void main(String[] args) {
        RebuildTreeTest test = new RebuildTreeTest();
        TreeNode result = test.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        System.out.print(result.val);
    }

    /*
    前序遍历 preorder = [3,9,20,15,7]
    中序遍历 inorder = [9,3,15,20,7]
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        Map<Integer, Integer> inValueIdxMap = new HashMap<Integer, Integer>();
        for(int i =0; i < inorder.length; i++){
            inValueIdxMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inValueIdxMap);
    }

    private TreeNode buildTree(int[] preorder, int preStartIdx, int preEndIdx, int[] inorder, int inStartIdx, int inEndIdx, Map<Integer, Integer> inValueIdxMap){
        if(preStartIdx > preEndIdx || inStartIdx > inEndIdx){
            return null;
        }

        int rootValue = preorder[preStartIdx];
        TreeNode thisNode = new TreeNode(rootValue);
        int inIndex = inValueIdxMap.get(rootValue);
        int leftCount = inIndex - inStartIdx;
        int rightCount = inEndIdx - inIndex;
        if(leftCount > 0){
            thisNode.left = buildTree(preorder, preStartIdx + 1, preStartIdx + leftCount, inorder, inStartIdx, inIndex - 1, inValueIdxMap);
        }
        if(rightCount > 0){
            thisNode.right = buildTree(preorder, preStartIdx + leftCount + 1, preEndIdx, inorder, inIndex + 1, inEndIdx, inValueIdxMap);
        }

        return thisNode;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
