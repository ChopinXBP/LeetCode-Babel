package Problems;

import java.util.PriorityQueue;

/**
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 */

public class SortList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n4.next = n2;
        n2.next = n1;
        n1.next = n3;
        ListNode head = sortList(n4);
        System.out.print(head.val);
        head = head.next;
        while(head != null){
            System.out.print("," + head.val);
            head = head.next;
        }
        System.out.println("");
    }

    //堆排序
    public static ListNode sortList(ListNode head) {
        PriorityQueue<ListNode> minheap = new PriorityQueue<>((l1, l2) -> Integer.compare(l1.val, l2.val));
        ListNode p = head;
        while(p != null){
            minheap.add(p);
            p = p.next;
        }

        if(!minheap.isEmpty())
            p = minheap.poll();
        ListNode newhead = p;
        while(!minheap.isEmpty()){
            p.next = minheap.poll();
            p = p.next;
            //若不加此句，链表可能会成环并无限运行
            p.next = null;
        }

        return newhead;
    }

    //归并排序：最佳
    public static ListNode sortList1(ListNode head){
        if(head == null || head.next == null)
            return head;

        //快慢指针找中点
        ListNode fast = head.next.next;
        ListNode low = head;
        while(fast != null && fast.next != null){
             fast = fast.next.next;
             low = low.next;
        }

        //递归调用（非常重要：递归排序右半部，后将左右截断）
        ListNode l1 = sortList1(low.next);
        low.next = null;
        ListNode l2 = sortList1(head);

        //归并排序
        ListNode newhead = new ListNode(0);
        fast = newhead;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                fast.next = l1;
                l1 = l1.next;
            }else{
                fast.next = l2;
                l2 = l2.next;
            }
            fast = fast.next;
        }
        fast.next = l1 == null ? l2 : l1;

        return newhead.next;
    }

    //快速排序
    public static ListNode sortList2(ListNode head){
        return QuickSort(head, null);
    }

    public static ListNode QuickSort(ListNode begin, ListNode end){
        if(begin == end || begin.next == end)
            return begin;

        ListNode head = new ListNode(0);
        ListNode smaller = head;
        ListNode partition = begin;
        ListNode bigger = begin;

        while(begin.next != end){
            if(begin.next.val < partition.val){
                smaller.next = begin.next;
                smaller = smaller.next;
                begin.next = begin.next.next;
            }else{
                begin = begin.next;
            }
        }
        smaller.next = bigger;

        ListNode left = QuickSort(head.next, partition.next);
        ListNode right = QuickSort(partition.next, end);
        head.next = left;
        while(left.next != null)
            left = left.next;
        left = right;

        return head.next;
    }

    //插入排序
    public static ListNode sortList3(ListNode head){
        if(head == null){
            return head;
        }

        ListNode newhead = new ListNode(0);
        ListNode cur = head;
        ListNode pre = newhead;
        while(cur != null){
            ListNode next = cur.next;
            while(pre.next != null && pre.next.val < cur.val){
                pre = pre.next;
            }
            //将cur插入pre.next
            cur.next = pre.next;
            pre.next = cur;
            pre = newhead;
            cur = next;
        }
        return newhead.next;
    }
}
