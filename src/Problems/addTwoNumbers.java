package Problems;

/**
 * 
 * @author ChopinXBP
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 给出两个 非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 */

public class addTwoNumbers {
	
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null)return null;
        ListNode p1 = l1;
        ListNode p2 = l2;
        
        ListNode head = new ListNode(0);
        ListNode ph = head;
        
        int sum;
        int flag = 0;
        while(p1 != null || p2 != null) {

        	if(p1 == null) {
        		sum = p2.val + flag;
        		p2 = p2.next;
        	}
        	else if(p2 == null){
        		sum = p1.val + flag;
        		p1 = p1.next;
        	}
        	else {
        		sum = p1.val + p2.val + flag;
        		p1 = p1.next;
        		p2 = p2.next;
        	}
        	
        	if(sum > 9) {
        		flag = 1;
        		sum %= 10;
        	}else {
        		flag = 0;
        	}
        	
        	ListNode newnode = new ListNode(sum);
        	ph.next = newnode;
        	ph = ph.next;
        }
        
        if(flag == 1) {
        	ListNode newnode = new ListNode(1);
        	ph.next = newnode;
        }
        
        return head.next;
    }	

}
