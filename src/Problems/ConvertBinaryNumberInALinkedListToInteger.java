package Problems;

/**
 * Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1.
 * The linked list holds the binary representation of a number.
 * Return the decimal value of the number in the linked list.
 * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
 * 请你返回该链表所表示数字的 十进制值 。
 */

public class ConvertBinaryNumberInALinkedListToInteger {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int getDecimalValue(ListNode head) {
        StringBuilder str = new StringBuilder();
        ListNode p = head;
        while (p != null) {
            str.append(p.val + "");
            p = p.next;
        }
        return Integer.parseInt(str.toString(), 2);
    }
}
