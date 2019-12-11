package Problems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a binary tree, return all root-to-leaf paths.
 * Note: A leaf is a node with no children.
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 */

public class BinaryTreePaths {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private List<String> result;
    public List<String> binaryTreePaths(TreeNode root) {
        result = new ArrayList<>();
        if(root != null){
            Solution(root, "");
        }
        return result;
    }

    private void Solution(TreeNode node, String str){
        if(node.left == null && node.right == null){
            result.add(str + node.val);
            return;
        }
        if(node.left != null){
            Solution(node.left, str + node.val + "->");
        }
        if(node.right != null){
            Solution(node.right, str + node.val + "->");
        }
    }
}
