/**
 * Given a binary tree, flatten it to a linked list in-place.
 * 给定一个二叉树，原地将它展开为链表。
 */

public class FlattenBinaryTreeToLinkedList {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //将根节点的右子树接在左子树的最右结点，再将根节点的左子树变为右子树，递归向右子树旋转
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        if(root.left == null){
            flatten(root.right);
            return;
        }
        TreeNode leftTreeRightestNode = root.left;
        while(leftTreeRightestNode.right != null){
            leftTreeRightestNode = leftTreeRightestNode.right;
        }
        leftTreeRightestNode.right = root.right;
        root.right = root.left;
        root.left = null;
        flatten(root.right);
    }

}
