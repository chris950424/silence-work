package com.algorithm.a.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class SolutionIII {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(2);
        node1.right = node2;
        node1.left = node3;
        node2.left = node4;
        node3.right = node5;

        final List<List<Integer>> lists = levelOrder(node1);
        System.out.println(lists);
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        level(map, root, 0);

        return new ArrayList<>(map.values());
    }

    public static void level(Map<Integer, List<Integer>> map, TreeNode root, Integer level) {
        if (root == null) {
            return;
        }
        List<Integer> list = map.getOrDefault(level, new ArrayList<>());
        list.add(root.val);
        map.put(level, list);
        level(map, root.left, level + 1);
        level(map, root.right, level + 1);
    }
}
