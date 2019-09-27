/**
 * 
 * @author ChopinXBP
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 * ����һ��������ת����������ÿ���ڵ������ƶ� k ��λ�ã����� k �ǷǸ�����
 *
 */

public class RotateList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ListNode rotateRight(ListNode head, int k) {
		if(head == null || k < 0) return null;
		//����������Ŀ��ʹ����ɻ�
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
		
		//pǰ��length-k��������ʼ���
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