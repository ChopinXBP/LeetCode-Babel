package Problems;

/**
 *
 * Find the sum of all left leaves in a given binary tree.
 * 计算给定二叉树的所有左叶子之和。
 *
 */

public class SumOfLeftLeaves {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public int sumOfLeftLeaves(TreeNode root) {
        Solution(root, false);
        return result;
    }

    private int result = 0;

    public void Solution(TreeNode root, boolean leftChild){
        if(null == root){
            return;
        }
        if(null == root.left && null == root.right && leftChild){
            result += root.val;
        }
        Solution(root.left, true);
        Solution(root.right, false);
    }
}
