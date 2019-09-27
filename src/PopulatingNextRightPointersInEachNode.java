import java.util.LinkedList;

/**
 *
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 */

public class PopulatingNextRightPointersInEachNode {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {
        if(root == null){
            return root;
        }
        LinkedList<Node> list = new LinkedList<>();
        list.add(root);
        while(!list.isEmpty()){
            int size = list.size();
            Node firstNode = list.pollFirst();
            if(firstNode.left != null){
                list.add(firstNode.left);
            }
            if(firstNode.right != null){
                list.add(firstNode.right);
            }
            for(int i = 0; i < size - 1; i++) {
                Node nextNode = list.pollFirst();
                firstNode.next = nextNode;
                nextNode.next = null;
                firstNode = nextNode;
                if(firstNode.left != null){
                    list.add(firstNode.left);
                }
                if(firstNode.right != null){
                    list.add(firstNode.right);
                }
            }
        }
        return root;
    }
}
