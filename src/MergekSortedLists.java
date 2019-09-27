/**
 * 
 * @author ChopinXBP
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 */

public class MergekSortedLists {


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

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        
        return mergeSolution(lists, 0, lists.length - 1);
    }
    
    public ListNode mergeSolution(ListNode[] lists, int begin, int end) {
    	if(begin == end) {
    		return lists[begin];
    	}else {
    		int mid = (begin + end) >> 1;
    		ListNode l1 = mergeSolution(lists, begin, mid);
    		ListNode l2 = mergeSolution(lists, mid + 1, end);
    		return mergeTwoLists(l1, l2);
    	}
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
