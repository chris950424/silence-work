package com.algorithm.a.other;

import java.util.HashSet;
import java.util.Set;

public class SolutionXIII {

    public static void main(String[] args) {
        ListNode node4 = new ListNode(5);
        ListNode node3 = new ListNode(4);
        ListNode node2 = new ListNode(3);

        ListNode node1 = new ListNode(2);
        ListNode headA = new ListNode(3);

        ListNode headB = new ListNode(3);
        node2.next=node1;
        headB.next = node2;
        headA.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
        ListNode listNode = getIntersectionNode(headA, headB);
        System.out.println(listNode);
    }


    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode temp = headA;
        while(temp != null){
            set.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null){
            if (set.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return  null;
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
