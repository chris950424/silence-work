package com.algorithm.a.other;

/**
 * SolutionXI
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/5/16
 */
public class SolutionXI {

    public static void main(String[] args) {
        ListNode node4 = new ListNode(5);
        ListNode node3 = new ListNode(4);
        ListNode node2 = new ListNode(3);

        ListNode node1 = new ListNode(2);
        ListNode head = new ListNode(1);

//        head.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
        ListNode listNode = removeNthFromEnd(head, 1);
        System.out.println(listNode);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        countN(newHead,n);
        return newHead.next;
    }

    private static Integer countN(ListNode head, int n) {
        if (head != null) {
            int integer = countN(head.next, n);
            integer =  integer+1;
            if (integer - 1 == n) {
                head.next = head.next.next;
            }
            return integer;
        }
        return 0;
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
