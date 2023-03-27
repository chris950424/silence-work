package com.algorithm.a.Tree;

public class SolutionVI {

    private static int ans;

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
        final int i = diameterOfBinaryTree(node1);
        System.out.println(i);
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        diameter(root);
        return ans - 1;
    }

    public static int diameter(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = diameter(root.left);
        int r = diameter(root.right);
        ans = Math.max(ans, l + r + 1);
        return Math.max(l, r) + 1;
    }
}
