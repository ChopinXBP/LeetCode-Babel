package Problems;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 * 给定一个二叉树，返回它的中序 遍历。
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */

public class BinaryTreeInorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //循环法
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        //当前结点与栈均空时，遍历结束
        while(p != null || !stack.empty()){
            //当前结点非空时，左子树不断入栈
            while(p != null){
                stack.push(p);
                p = p.left;
            }
            //当前结点为空时，元素出栈，遍历其右子树。若右子树为空，下一次循环其父节点会出栈。
            p = stack.pop();
            result.add(p.val);
            p = p.right;
        }
        return result;
    }

    //Morris Method：不借助栈进行二叉树线索化
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        TreeNode curr = root;
        //不断迭代，将第一个有左子树的根节点连接在其左子树的最右结点上，最后形成一棵只有右子树的二叉树
        while(curr != null){
            //若左子树为空，则遍历右子树
            if(curr.left == null){
                result.add(curr.val);
                curr = curr.right;
            }
            //出现第一个左子树非空的根节点，令pre成为左子树的根节点，将curr连接在pre的最右结点上
            else{
                TreeNode pre = curr.left;
                while(pre.right != null){
                    pre = pre.right;
                }
                pre.right = curr;       //根节点连接在其左子树的最右结点上
                TreeNode temp = curr;
                curr = curr.left;       //当前指针curr指回现在的根节点（曾经的pre）
                temp.left = null;       //曾经的根节点左子树置null
            }
        }
        return result;
    }
}
