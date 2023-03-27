package com.algorithm.a.other;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SolutionXVII {

    public static void main(String[] args) {
        ListNode node4 = new ListNode(5);
        ListNode node3 = new ListNode(4);
        ListNode node2 = new ListNode(3);

        ListNode node1 = new ListNode(2);
        ListNode head = new ListNode(1);

        head.next = node2;
        node2.next = node4;
        node1.next = node3;
        ListNode listNode = mergeTwoLists(head, node1);
        System.out.println(listNode);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        Deque<ListNode> listNodeStack = new LinkedList<>();
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                listNodeStack.offer(list1);
                list1 = list1.next;
            } else {
                listNodeStack.offer(list2);
                list2 = list2.next;
            }
        }
        if (list1 != null) {
            while (list1 != null) {
                listNodeStack.offer(list1);
                list1 = list1.next;
            }
        }
        if (list2 != null) {
            while (list2 != null) {
                listNodeStack.offer(list2);
                list2 = list2.next;
            }
        }
        ListNode listNode = new ListNode(-1);
        ListNode temp = listNode;
        while (!listNodeStack.isEmpty()) {
            temp.next = listNodeStack.pollFirst();
            temp = temp.next;
        }
        return listNode.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
