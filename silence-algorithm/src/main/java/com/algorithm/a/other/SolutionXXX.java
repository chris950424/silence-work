package com.algorithm.a.other;

public class SolutionXXX {

    public static void main(String[] args) {
        ListNode node4 = new ListNode(5);
        ListNode node3 = new ListNode(4);
        ListNode node2 = new ListNode(3);

        ListNode node1 = new ListNode(2);
        ListNode head = new ListNode(1);

        head.next = node1;
        node1.next = null;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next=node1;
        boolean palindrome = hasCycle(head);
        System.out.println(palindrome);

    }

    public static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null && fast.next != null) {
            fast = fast.next;
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return false;
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
