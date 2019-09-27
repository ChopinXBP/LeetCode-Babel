/**
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 */

public class SwapNodesInPairs {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newhead = new ListNode(0);
        newhead.next = head;
        ListNode left = newhead;
        ListNode mid = head;
        ListNode right = head.next;
        while(left.next != null && left.next.next != null){
            mid.next = right.next;
            right.next = mid;
            left.next = right;
            left = mid;
            mid = mid.next;
            right = mid == null ? mid : mid.next;
        }
        return newhead.next;
    }
}
