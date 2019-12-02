package Problems;

import java.util.Stack;

/**
 *
 * Reverse a singly linked list.
 * 反转一个单链表。
 *
 */

public class ReverseLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //辅助栈
    public ListNode reverseList(ListNode head) {
        if(head == null)
            return null;
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        while(p != null){
            stack.push(p);
        }
        p = stack.pop();
        ListNode newhead = p;
        while(!stack.empty()){
            p.next = stack.pop();
            p = p.next;
        }
        p.next = null;
        return newhead;
    }

    //快慢链表
    public ListNode reverseList1(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode left = head;
        ListNode center = head.next;
        head.next = null;
        while(center.next != null) {
            ListNode right = center.next;
            center.next = left;
            left = center;
            center = right;
        }
        center.next = left;
        return center;
    }

    //递归
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
