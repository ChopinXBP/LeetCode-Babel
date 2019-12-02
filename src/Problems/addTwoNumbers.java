package Problems;

/**
 * 
 * @author ChopinXBP
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * �������� �ǿ� ������������ʾ�����Ǹ���������
 * ���У����Ǹ��Ե�λ���ǰ��� ���� �ķ�ʽ�洢�ģ��������ǵ�ÿ���ڵ�ֻ�ܴ洢 һλ ���֡�
 * ��������ǽ��������������������᷵��һ���µ���������ʾ���ǵĺ͡�
 * �����Լ���������� 0 ֮�⣬���������������� 0 ��ͷ��
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
