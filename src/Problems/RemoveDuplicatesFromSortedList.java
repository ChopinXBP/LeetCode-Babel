package Problems;

public class RemoveDuplicatesFromSortedList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //留下一个重复值
    public ListNode deleteDuplicates(ListNode head) {
        ListNode newhead = new ListNode(-1);
        newhead.next = head;
        ListNode p = head;
        while(p != null){
            ListNode np = p.next;
            while(np != null && np.val == p.val){
                np = np.next;
            }
            p.next = np;
            p = np;
        }
        return newhead.next;
    }

    //删除所有重复值
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode newhead = new ListNode(-1);
        newhead.next = head;
        ListNode p = newhead;
        while(p.next != null){
            ListNode cp = p.next;
            ListNode np = cp.next;
            if(np == null || np.val != cp.val){
                p = cp;
                continue;
            }
            while(np.next != null && np.next.val == cp.val){
                np = np.next;
            }
            p.next = np.next;
        }
        return newhead.next;
    }
}
