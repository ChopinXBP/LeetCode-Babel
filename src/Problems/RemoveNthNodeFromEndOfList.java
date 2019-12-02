package Problems;

/**
 *
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 */

public class RemoveNthNodeFromEndOfList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(n <= 0){
            return null;
        }
        ListNode newhead = new ListNode(-1);
        newhead.next = head;
        ListNode fast = newhead;
        ListNode low = newhead;
        while(n-- > 0 && fast != null){
            fast = fast.next;
        }
        if(n > 0){
            return null;
        }
        while(fast.next != null){
            fast = fast.next;
            low = low.next;
        }
        low.next = low.next.next;
        return newhead.next;
    }
}
