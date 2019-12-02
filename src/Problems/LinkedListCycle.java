package Problems;

/**
 *
 *  Given a linked list, determine if it has a cycle in it.
 *  To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to.
 *  If pos is -1, then there is no cycle in the linked list.
 *  给定一个链表，判断链表中是否有环。
 *  为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 *  Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *  To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *  Note: Do not modify the linked list.
 *  给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *  为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *  说明：不允许修改给定的链表。
 *
 */

public class LinkedListCycle {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public boolean hasCycle(ListNode head) {
        ListNode low = head;
        ListNode fast = head;
        while(fast != null && low != null){
            fast = fast.next;
            low = low.next;
            if(fast == null || low == null || fast.next == null)
                return false;
            fast = fast.next;
            if(fast == low)
                return true;
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode low = head;
        ListNode fast = head;
        int fastcount = 0;
        int lowcount = 0;
        while(fast != null && low != null){
            fast = fast.next;
            low = low.next;
            if(fast == null || low == null || fast.next == null)
                return null;
            fast = fast.next;
            fastcount += 2;
            lowcount++;
            //快指针比慢指针多走了一个环的距离cirlen
            //重置快慢指针，令快指针比慢指针快cirlen，同时出发，第一次在入口结点相遇
            if(fast == low){
                int cirlen = fastcount - lowcount;
                low = head;
                fast = head;
                while(cirlen > 0){
                    fast = fast.next;
                    cirlen--;
                }
                while(fast != low){
                    low = low.next;
                    fast = fast.next;
                }
                return fast;
            }
        }
        return null;
    }
}
