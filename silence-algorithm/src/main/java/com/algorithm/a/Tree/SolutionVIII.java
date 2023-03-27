package com.algorithm.a.Tree;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class SolutionVIII {


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(6);
//        TreeNode node4 = new TreeNode(4);
//        TreeNode node5 = new TreeNode(5);
//        TreeNode node6 = new TreeNode(6);
//        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
//        node2.right = node4;
//        node1.right = node5;
//        node5.right = node7;
//        node5.left = node6;
        boolean validBST = isValidBST(node1);
        System.out.println(validBST);
    }

    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }


}
