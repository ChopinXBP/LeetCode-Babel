package Problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * Given an n-ary tree, return the preorder traversal of its nodes' values.
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 *
 */

public class NaryTreeTraversal {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    //前序遍历
    private ArrayList<Integer> preorderResult;
    public List<Integer> preorder(Node root) {
        preorderResult = new ArrayList<>();
        preTra(root);
        return preorderResult;
    }

    public void preTra(Node root){
        if(root == null){
            return;
        }
        preorderResult.add(root.val);
        for(Node node : root.children){
            preTra(node);
        }
    }

    //后序遍历
    private ArrayList<Integer> postorderResult;
    public List<Integer> postorder(Node root) {
        postorderResult = new ArrayList<>();
        postTra(root);
        return postorderResult;
    }

    public void postTra(Node root){
        if(root == null){
            return;
        }
        for(Node node : root.children){
            postTra(node);
        }
        postorderResult.add(root.val);
    }

    //层序遍历
    public List<List<Integer>> levelOrder(Node root) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> newlist = new ArrayList<>();
            for(int i = 0; i < size; i++){
                Node curNode = queue.pollFirst();
                newlist.add(curNode.val);
                for(Node node : curNode.children){
                    queue.add(node);
                }
            }
            result.add(newlist);
        }
        return result;
    }

    //最大深度
    public int maxDepth(Node root) {
        if(root == null){
            return 0;
        }
        int maxdepth = 0;
        for(Node node : root.children){
            maxdepth = Math.max(maxdepth, maxDepth(node));
        }
        return maxdepth + 1;
    }
}
