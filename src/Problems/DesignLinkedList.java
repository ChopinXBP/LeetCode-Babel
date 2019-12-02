package Problems;

/**
 *
 * Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list.
 * 设计链表的实现。您可以选择使用单链表或双链表。
 *
 */

public class DesignLinkedList {
    class MyLinkedList {

        class Node{
            int val;
            Node next;
            public Node(int val){
                this.val = val;
            }
        }

        private Node head;
        private int size;

        /** Initialize your data structure here. */
        public MyLinkedList() {}

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            if(index < 0 || index >= size){
                return -1;
            }
            Node cur = head;
            for(int i = 0; i < index; i++){
                cur = cur.next;
            }
            return cur.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            Node cur = head;
            head = new Node(val);
            head.next = cur;
            size++;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            Node newNode = new Node(val);
            if (head == null) {
                head = newNode;
            } else {
                Node cur = head;
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = newNode;
            }
            size++;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            if(index > size){
                return;
            }
            if(index <= 0){
                addAtHead(val);
                return;
            }
            Node cur = head;
            for(int i = 0; i < index - 1; i++){
                cur = cur.next;
            }
            Node newNode = new Node(val);
            newNode.next = cur.next;
            cur.next = newNode;
            size++;
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            if(index < 0 || index >= size){
                return;
            }
            size--;
            if(index == 0){
                head = head.next;
                return;
            }
            Node cur = head;
            for(int i = 0; i < index - 1; i++){
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
    }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
}
