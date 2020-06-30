package com.clover.leetcode;

/**
 * LeetCode题目2：两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */


/**
 * 链表结构
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}


public class LeetCode2 {

    public static void main(String[] args) {
        LeetCode2 code = new LeetCode2();

        ListNode node9 = new ListNode(9);
        ListNode node1 = new ListNode(1);
        ListNode node91 = new ListNode(9);
        ListNode node92 = new ListNode(9);
        ListNode node93 = new ListNode(9);
        ListNode node94 = new ListNode(9);
        ListNode node95 = new ListNode(9);
        ListNode node96 = new ListNode(9);
        ListNode node97 = new ListNode(9);
        ListNode node98 = new ListNode(9);
        ListNode node99 = new ListNode(9);
        node1.next = node91;
        node91.next = node92;
        node92.next = node93;
        node93.next = node94;
        node94.next = node95;
        node95.next = node96;
        node96.next = node97;
        node97.next = node98;
        node98.next = node99;

        ListNode re = code.addTwoNumbers(node9, node1);
        System.out.println("");
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String reStr = "";
        boolean bol = false;
        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum;
            if (bol) {
                sum = x + y + 1;
            } else {
                sum = x + y;
            }
            bol = sum > 9 ? true : false;
            reStr += (sum % 10) + "";
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (bol){
            reStr += "1";
        }
        ListNode result = new ListNode(Integer.parseInt(reStr.charAt(0) + ""));
        ListNode cache = result;
        for (int j = 1; j < reStr.length(); j++) {
            ListNode node = new ListNode(0);
            node.val = Integer.parseInt(reStr.charAt(j) + "");
            cache.next = node;
            cache = node;
        }

        return result;
    }

}
