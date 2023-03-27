package com.algorithm.a.other;

import java.lang.annotation.Target;
import java.util.List;

public class SolutionVIII {

    public static void main(String[] args) {
//        ListNode node4 = new ListNode(1);
//        ListNode node3 = new ListNode(2);
//        ListNode node2 = new ListNode(3);

        ListNode node1 = new ListNode(2);
        ListNode head = new ListNode(1);

        head.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
        boolean palindrome = isPalindrome(head);
        System.out.println(palindrome);

    }


    public static boolean isPalindrome(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode newHead = reverseRecursion(slow.next, null);
        boolean flag = true;
        while (newHead !=null) {
            if (head.val != newHead.val) {
                flag = false;
                break;
            }
            head = head.next;
            newHead = newHead.next;
        }
        return flag;
    }

    public static ListNode reverseRecursion(ListNode currentNode, ListNode pre) {
        if (currentNode == null) {
            return pre;
        }
        ListNode nextNode = currentNode.next;
        currentNode.next = pre;
        return reverseRecursion(nextNode, currentNode);
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
