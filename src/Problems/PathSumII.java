package Problems;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * Note: A leaf is a node with no children.
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 */

public class PathSumII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    List<List<Integer>> result = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null){
            return result;
        }
        solution(root, sum, new LinkedList<>());
        return result;
    }

    private void solution(TreeNode root, int sum, LinkedList<Integer> list){
        sum -= root.val;
        if(root.left == null && root.right == null){
            if(sum == 0){
                list.add(root.val);
                result.add(new LinkedList<>(list));
                list.removeLast();
            }
            return;
        }
        if(null != root.left){
            list.add(root.val);
            solution(root.left, sum, list);
            list.removeLast();
        }
        if(null != root.right){
            list.add(root.val);
            solution(root.right, sum, list);
            list.removeLast();
        }
    }
}
