import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 */

public class BinaryTreePostorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    private List<Integer> result;
    public List<Integer> postorderTraversal(TreeNode root){
        result = new LinkedList<>();
        post(root);
        return result;
    }

    private void post(TreeNode root){
        if(root == null){
            return;
        }
        post(root.left);
        post(root.right);
        result.add(root.val);
    }

    //非递归写法
    public List<Integer> postorderTraversal2(TreeNode root){
        LinkedList<Integer> result = new LinkedList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode curNode = stack.pop();
            //出栈后加入结果顺序：left、right、root
            result.addFirst(curNode.val);
            if(curNode.left != null){
                stack.push(curNode.left);
            }
            if(curNode.right != null){
                stack.push(curNode.right);
            }
        }
        return result;
    }
}
