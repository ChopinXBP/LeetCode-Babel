package Problems;

/**
 *
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 *
 */

public class DiameterOfBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public int result;
    public int diameterOfBinaryTree(TreeNode root) {
        result = 0;
        Solution(root);
        return result;
    }

    public int Solution(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = Solution(root.left);
        int right = Solution(root.right);
        int length = left + right;
        result = length > result ? length : result;
        return Math.max(left, right) + 1;
    }
}
