package com.algorithm.a.other;

import java.util.List;

/**
 * SolutionXI
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/5/16
 */
public class SolutionXII2 {

    public static void main(String[] args) {
        ListNode node4 = new ListNode(5);
        ListNode node3 = new ListNode(4);
        ListNode node2 = new ListNode(3);

        ListNode node1 = new ListNode(2);
        ListNode head = new ListNode(3);

        head.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
        ListNode listNode = sortList(head);
        System.out.println(listNode);
    }

    public static ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public static ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }
        if (head.next == tail){
            head.next = null;
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        return merge(list1,list2);
    }

    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
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
