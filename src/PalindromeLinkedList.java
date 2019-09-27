/**
 * Given a singly linked list, determine if it is a palindrome.
 * Could you do it in O(n) time and O(1) space?
 * 请判断一个链表是否为回文链表。
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */

public class PalindromeLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        //计算个数
        int num = 0;
        ListNode p1 = head;
        while(p1 != null){
            num++;
            p1 = p1.next;
        }
        //令p1指向第一条链，p2的下一结点为第二条链
        p1 = head;
        for(int i = 1; i < num >> 1; i++){
            p1 = p1.next;
        }
        ListNode p2 = p1;
        if((num & 1) == 1){
            p2 = p2.next;
        }
        //将第二条链翻转
        p1 = p2.next;
        p2.next = null;
        ListNode pre;
        while(p1 != null){
            pre = p1.next;
            p1.next = p2;
            p2 = p1;
            p1 = pre;
        }
        //比较链表
        p1 = head;
        while(p2 != null && p1 != null){
            if(p2.val != p1.val){
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
}
