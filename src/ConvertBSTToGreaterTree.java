/**
 *
 * Given a Binary Search Tree (BST),
 * convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 */

public class ConvertBSTToGreaterTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode convertBST(TreeNode root) {
        Solution(root);
        return root;
    }

    int sum = 0;
    public void Solution(TreeNode root){
        if(root == null){
            return;
        }
        Solution(root.right);
        root.val += sum;
        sum = root.val;
        Solution(root.left);
    }
}
