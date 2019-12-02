package Problems;

import java.util.LinkedList;

/**
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * Note: Bonus points if you could solve it both recursively and iteratively.
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 说明: 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 *
 */

public class SymmetricTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //递归法
    public boolean isSymmetric(TreeNode root) {
        //return root == null || Solution(root.left, root.right);
        return Solution(root, root);
    }

    public boolean Solution(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return true;
        }
        if(left == null || right == null){
            return false;
        }
        return left.val == right.val && Solution(left.right, right.left) && Solution(left.left, right.right);
    }

    //迭代法：模拟层序遍历，每提取两个结点后按相反的顺序放入队列
    public boolean isSymmetric2(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        /*
        if(root == null){
            return true;
        }
        queue.add(root.left);
        queue.add(root.right);
        */
        while(!queue.isEmpty()){
            TreeNode left = queue.pop();
            TreeNode right = queue.pop();
            if(left == null && right == null){
                continue;
            }
            if(left == null || right == null || left.val != right.val){
                return false;
            }
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }
}
