package Problems;

/**
 * 
 * @author ChopinXBP
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 */

public class MergeTwoSortedLists {


	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	if(l1 == null && l2 == null)return null;
    	if(l1 == null) return l2;
    	if(l2 == null) return l1;
    	
    	ListNode result = new ListNode(-1);
    	ListNode p = result;
    	ListNode pl1 = l1;
    	ListNode pl2 = l2;
    	while(pl1 != null && pl2 != null) {
    		if(pl1.val < pl2.val) {
    			p.next = pl1;
    			pl1 = pl1.next;
    		}else {
    			p.next = pl2;
    			pl2 = pl2.next;
    		}
    		p = p.next;
    	}
    	
    	if(pl1 == null) {
    		p.next = pl2;
    	}
    	if(pl2 == null) {
    		p.next = pl1;
    	}
    	
    	return result.next;
    }
    
}
