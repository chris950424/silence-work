package com.algorithm.a.other;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolutionXIV {
    public static void main(String[] args) {
        ListNode node4 = new ListNode(5);
        ListNode node3 = new ListNode(4);
        ListNode node2 = new ListNode(3);

        ListNode node1 = new ListNode(2);
        ListNode headA = new ListNode(1);
        node2.next = node3;
        node1.next = node2;
        headA.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
        ListNode listNode = reverseList(headA);
        System.out.println(listNode);
    }

    public static ListNode reverseList(ListNode head) {
        return reverse(head,null);
    }

    private static ListNode reverse(ListNode currentNode, ListNode pre) {
        if (currentNode == null) {
            return pre;
        }
        ListNode nextNode = currentNode.next;
        currentNode.next = pre;
        return  reverse(nextNode, currentNode);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
