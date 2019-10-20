/**
 *
 * You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer,
 * which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on,
 * to produce a multilevel data structure, as shown in the example below.
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.
 * 您将获得一个双向链表，除了下一个和前一个指针之外，它还有一个子指针，可能指向单独的双向链表。
 * 这些子列表可能有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * 扁平化列表，使所有结点出现在单级双链表中。您将获得列表第一级的头部。
 *
 */

public class FlattenAMultilevelDoublyLinkedList {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

    public Node flatten(Node head) {
        if(head == null){
            return head;
        }
        if(head.child == null){
            head.next = flatten(head.next);
        }else{
            Node next = head.next;
            Node child = flatten(head.child);
            child.prev = head;
            head.next = child;
            head.child = null;

            Node p = head;
            while(p.next != null){
                p = p.next;
            }
            if(next == null){
                p.next = next;
            }else {
                next.prev = p;
                p.next = flatten(next);
            }
        }
        return head;
    }

}
