package Problems;

import java.util.Stack;

/**
 *
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 * 说明：你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 */

public class KthSmallestElementInABST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //递归中序遍历
    public int num;
    public TreeNode result;
    public int kthSmallest(TreeNode root, int k) {
        num = 0;
        result = null;
        Solution(root, k);
        return result.val;
    }

    public void Solution(TreeNode root, int k){
        if(num == k || root == null)
            return;
        Solution(root.left, k);
        if(num == k)
            return;
        result = root;
        ++num;
        Solution(root.right, k);
    }

    //循环中序遍历
    public int kthSmallest1(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        int num = 0;
        while(curNode != null || !stack.empty()){
            if(curNode != null){
                stack.push(curNode);
                curNode = curNode.left;
            }else{
                curNode = stack.pop();
                if(++num == k)
                    return curNode.val;
                curNode = curNode.right;
            }
        }
        return -1;
    }

}
