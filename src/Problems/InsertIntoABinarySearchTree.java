package Problems;

/**
 *
 * Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST.
 * Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 * Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
 * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
 *
 */

public class InsertIntoABinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    //循环
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if(root == null){
            root = new TreeNode(val);
            return root;
        }
        TreeNode child = root;
        TreeNode cur = child;
        boolean leftFlag = false;
        while(child != null){
            cur = child;
            if(cur.val == val){
                return root;
            }else if(cur.val > val){
                leftFlag = true;
                child = cur.left;
            }else{
                leftFlag = false;
                child = cur.right;
            }
        }
        if(leftFlag){
            cur.left = new TreeNode(val);
        }else{
            cur.right = new TreeNode(val);
        }
        return root;
    }

    //递归
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }
        if(root.val > val){
            root.left = insertIntoBST(root.left, val);
        }else{
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}
