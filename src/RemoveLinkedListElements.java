/**
 *
 * Remove all elements from a linked list of integers that have value val.
 * 删除链表中等于给定值 val 的所有节点。
 *
 */

public class RemoveLinkedListElements {
    class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode newhead = new ListNode(0);
        newhead.next = head;
        ListNode p = newhead;
        while(p.next != null){
            if(p.next.val == val){
                p.next = p.next.next;
            }else {
                p = p.next;
            }
        }
        return newhead.next;
    }
}
