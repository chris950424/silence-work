package com.algorithm.a.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class SolutionIV {

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        final TreeNode treeNode = buildTree(preorder, inorder);
        System.out.println(treeNode);
    }

    private static final Map<Integer, Integer> MAP = new HashMap<>();

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        final int n = preorder.length;
        for (int i = 0; i < n; i++) {
            MAP.put(inorder[i], i);
        }
        return myBuildTree(preorder, 0, n - 1, 0, n - 1);
    }


    public static TreeNode myBuildTree(int[] preorder, int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {
        if (preorderLeft > preorderRight) {
            return null;
        }
        Integer inorderRoot = MAP.get(preorder[preorderLeft]);
        TreeNode root = new TreeNode(preorder[preorderLeft]);
        int sizeLength = inorderRoot - inorderLeft;
        root.left = myBuildTree(preorder, preorderLeft+1, preorderLeft + sizeLength, inorderLeft, inorderRoot - 1);
        root.right = myBuildTree(preorder, preorderLeft + sizeLength + 1, preorderRight, inorderRoot + 1, inorderRight);
        return root;
    }

}
