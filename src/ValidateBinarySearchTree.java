/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */

public class ValidateBinarySearchTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //Morris算法
    public boolean isValidBST(TreeNode root) {
        //防止出现结点值为一个或多个Integer.MIN_VALUE
        double last = -Double.MAX_VALUE;
        TreeNode curr = root;
        while(curr != null){
            if(curr.left == null){
                if(curr.val <= last){
                    return false;
                }
                last = curr.val;
                curr = curr.right;
            }
            else{
                TreeNode pre = curr.left;
                while(pre.right != null){
                    pre = pre.right;
                }
                pre.right = curr;
                TreeNode temp = curr.left;
                curr.left = null;
                curr = temp;
            }
        }
        return true;
    }

    //递归中序遍历
    double lastNum = -Double.MAX_VALUE;
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isValidBST(root.left)) {
            if (lastNum < root.val) {
                lastNum = root.val;
                return isValidBST(root.right);
            }
        }
        return false;
    }
}
