package Problems;

/**
 * 
 * @author ChopinXBP
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * ��������������ϲ�Ϊһ���µ������������ء���������ͨ��ƴ�Ӹ�����������������нڵ���ɵġ�
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
