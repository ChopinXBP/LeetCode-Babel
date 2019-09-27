import java.util.HashMap;

/**
 *
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的深拷贝。
 *
 */

public class CopyListWithRandomPointer {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node newHead = new Node();
        Node pNew = newHead;
        Node pOld = head;
        while(pOld != null){
            pNew.next = new Node(pOld.val, null, null);
            pNew = pNew.next;
            map.put(pOld, pNew);
            pOld = pOld.next;
        }
        pNew = newHead.next;
        pOld = head;
        while(pOld != null){
            pNew.random = map.get(pOld.random);
            pNew = pNew.next;
            pOld = pOld.next;
        }
        return newHead.next;
    }

    //O(1)空间的迭代
    public Node copyRandomList2(Node head){
        if(head == null){
            return null;
        }
        Node p = head;
        //遍历原来的链表并拷贝每一个节点，将拷贝节点放在原来节点的旁边，创造出一个旧节点和新节点交错的链表。
        while(p != null){
            Node newNode = new Node(p.val, null, null);
            newNode.next = p.next;
            p.next = newNode;
            p = newNode.next;
        }

        //迭代这个新旧节点交错的链表，并用旧节点的 random 指针去更新对应新节点的 random 指针。
        p = head;
        while(p != null){
            p.next.random = (p.random != null) ? p.random.next : null;
            p = p.next.next;
        }

        //拆分还原两个链表。
        Node pOld = head;
        Node pNew = head.next;
        Node newHead = head.next;
        while (pOld != null) {
            pOld.next = pOld.next.next;
            pNew.next = (pNew.next != null) ? pNew.next.next : null;
            pOld = pOld.next;
            pNew = pNew.next;
        }
        return newHead;
    }
}
