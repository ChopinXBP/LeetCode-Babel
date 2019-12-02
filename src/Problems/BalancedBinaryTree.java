package Problems;

/**
 *
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 */

public class BalancedBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        flag = true;
        return Solution(root) != -1 && isSearchTree(root);
    }

    private boolean isSearchTree(TreeNode root){
        if(root.left == null && root.right == null){
            return true;
        }
        if(root.left == null){
            return root.right.val > root.val && isSearchTree(root.right);
        }
        if(root.right == null){
            return root.left.val < root.val && isSearchTree(root.left);
        }
        return root.val > root.left.val && root.val < root.right.val && isSearchTree(root.left) && isSearchTree(root.right);
    }

    private boolean flag;
    private int Solution(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = Solution(root.left);
        if(!flag){
            return -1;
        }
        int right = Solution(root.right);
        if(!flag){
            return -1;
        }
        if(Math.abs(left - right) > 1){
            flag = false;
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
