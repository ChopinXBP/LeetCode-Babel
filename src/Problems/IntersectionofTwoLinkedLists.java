package Problems;

import java.util.Stack;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * 编写一个程序，找到两个单链表相交的起始节点。
 */

public class IntersectionofTwoLinkedLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //辅助栈
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Stack<ListNode> stackA = new Stack<>();
        Stack<ListNode> stackB = new Stack<>();
        while(headA != null){
            stackA.push(headA);
            headA = headA.next;
        }
        while(headB != null){
            stackB.push(headB);
            headB = headB.next;
        }
        ListNode answer = null;
        while(!stackA.empty() && !stackB.empty() && stackA.peek() == stackB.peek()){
            answer = stackA.pop();
            stackB.pop();
        }
        return answer;
    }

    //快慢指针法
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        int lengthA = 0;
        int lengthB = 0;
        ListNode pa = headA;
        ListNode pb = headB;
        while(pa != null){
            lengthA++;
            pa = pa.next;
        }
        while(pb != null){
            lengthB++;
            pb = pb.next;
        }
        int gap;
        pa = headA;
        pb = headB;
        if(lengthA > lengthB){
            gap = lengthA - lengthB;
            while(gap-- != 0){
                pb = pb.next;
            }
        }else{
            gap = lengthB - lengthA;
            while(gap-- != 0){
                pa = pa.next;
            }
        }

        while(pa != null && pb != null && pa != pb){
            pa = pa.next;
            pb = pb.next;
        }
        return pa;
    }
}
