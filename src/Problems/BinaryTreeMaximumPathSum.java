package Problems;

/**
 *
 * Given a non-empty binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 */
public class BinaryTreeMaximumPathSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int result;
    public int maxPathSum(TreeNode root) {
        result = Integer.MIN_VALUE;
        SubTreeRouteSum(root);
        return result;
    }

    //路径和 = 根节点 + 左子树路径和 + 右子树路径和
    public int SubTreeRouteSum(TreeNode root){
        if(root == null)
            return Integer.MIN_VALUE;

        int leftsum = Math.max(0, SubTreeRouteSum(root.left));
        int rightsum = Math.max(0, SubTreeRouteSum(root.right));

        if(root.val + leftsum + rightsum > result)
            result = root.val + leftsum + rightsum;
        return root.val + Math.max(leftsum, rightsum);
    }
}
