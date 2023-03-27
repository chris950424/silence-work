package com.algorithm.a.Tree;

import java.util.ArrayList;
import java.util.List;

public class SolutionV {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node2.left = node3;
        node2.right = node4;
        node1.right = node5;
        node5.right = node7;
        node5.left = node6;
        flatten(node1);
        System.out.println(node1);
    }

    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode processNode = next;
                while (processNode.right != null) {
                    processNode = processNode.right;
                }
                processNode.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }


}
