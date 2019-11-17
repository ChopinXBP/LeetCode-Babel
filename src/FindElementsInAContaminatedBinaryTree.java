import java.util.HashSet;

/**
 *
 * Given a binary tree with the following rules:
 * root.val == 0
 * If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
 * If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
 * Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.
 * You need to first recover the binary tree and then implement the FindElements class:
 * FindElements(TreeNode* root) Initializes the object with a contamined binary tree, you need to recover it first.
 * bool find(int target) Return if the target value exists in the recovered binary tree.
 * 给出一个满足下述规则的二叉树：
 * root.val == 0
 * 如果 treeNode.val == x 且 treeNode.left != null，那么 treeNode.left.val == 2 * x + 1
 * 如果 treeNode.val == x 且 treeNode.right != null，那么 treeNode.right.val == 2 * x + 2
 * 现在这个二叉树受到「污染」，所有的 treeNode.val 都变成了 -1。
 * 请你先还原二叉树，然后实现 FindElements 类：
 * FindElements(TreeNode* root) 用受污染的二叉树初始化对象，你需要先把它还原。
 * bool find(int target) 判断目标值 target 是否存在于还原后的二叉树中并返回结果。
 *
 */

public class FindElementsInAContaminatedBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class FindElements {

        HashSet<Integer> set;

        public FindElements(TreeNode root) {
            if(root == null){
                return;
            }
            set = new HashSet<>();
            root.val = 0;
            set.add(root.val);
            Solution(root.left, root, true);
            Solution(root.right, root, false);
        }

        public void Solution(TreeNode root, TreeNode pre, boolean dist) {
            if(root == null){
                return;
            }
            root.val = 2 * pre.val + (dist ? 1 : 2);
            set.add(root.val);
            Solution(root.left, root, true);
            Solution(root.right, root, false);
        }

        public boolean find(int target) {
            return set.contains(target);
        }
    }

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
}