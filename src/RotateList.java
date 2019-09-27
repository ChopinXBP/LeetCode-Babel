/**
 * 
 * @author ChopinXBP
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 */

public class RotateList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ListNode rotateRight(ListNode head, int k) {
		if(head == null || k < 0) return null;
		//计算链表数目并使链表成环
		ListNode p = head;
		int length = 1;
		while(p.next != null) {
			length++;
			p = p.next;
		}		
		k = k % length;		
		if(k == 0)
			return head;
		p.next = head;
		
		//p前进length-k步即得起始结点
		p = head;
		int num = length - k - 1;
		while(num != 0) {
			p = p.next;
			num--;
		}
		ListNode newhead = p.next;
		p.next = null;
		
		return newhead;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}