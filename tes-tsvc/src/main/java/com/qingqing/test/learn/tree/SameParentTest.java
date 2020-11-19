package com.qingqing.test.learn.tree;

import com.qingqing.test.learn.tree.TreeUtils.TreeNode;

/**
 * Created by zhujianxing on 2020/11/11.
 */
public class SameParentTest {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root.val == p.val || root.val == q.val){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left == null){
            return right;
        }

        if(right == null){
            return left;
        }

        return root;
    }

}
