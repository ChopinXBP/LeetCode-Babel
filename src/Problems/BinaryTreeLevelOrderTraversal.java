package Problems;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 */

public class BinaryTreeLevelOrderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //层次遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        if (root != null) {
            list.add(root);
        }
        while (!list.isEmpty()) {
            int size = list.size();
            LinkedList<Integer> rownums = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode p = list.pollFirst();
                rownums.add(p.val);
                if (p.left != null) {
                    list.add(p.left);
                }
                if (p.right != null) {
                    list.add(p.right);
                }
            }
            result.add(rownums);
        }
        return result;
    }

    //锯齿遍历/Z字形遍历（分层打印）
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        if (root != null) {
            list.add(root);
        }
        boolean rowflag = true;
        while (!list.isEmpty()) {
            int size = list.size();
            LinkedList<Integer> rownums = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode p = list.pollFirst();
                if (rowflag) {
                    rownums.add(p.val);
                } else {
                    rownums.addFirst(p.val);
                }
                if (p.left != null) {
                    list.add(p.left);
                }
                if (p.right != null) {
                    list.add(p.right);
                }
            }
            rowflag = !rowflag;
            result.add(rownums);
        }
        return result;
    }

    //层次遍历：自底向上
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        if (root != null) {
            list.add(root);
        }
        while (!list.isEmpty()) {
            int size = list.size();
            LinkedList<Integer> rownums = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode p = list.pollFirst();
                rownums.add(p.val);
                if (p.left != null) {
                    list.add(p.left);
                }
                if (p.right != null) {
                    list.add(p.right);
                }
            }
            result.add(rownums);
        }
        Collections.reverse(result);
        return result;
    }

}
