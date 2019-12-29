package Problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Given two binary search trees root1 and root2.
 * Return a list containing all the integers from both trees sorted in ascending order.
 * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。
 *
 */

public class AllElementsInTwoBinarySearchTrees {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> result = new ArrayList<>();
        dfs(root1, result);
        dfs(root2, result);
        Collections.sort(result);
        return result;
    }

    private void dfs(TreeNode root, List<Integer> result){
        if(root == null){
            return;
        }
        result.add(root.val);
        dfs(root.left, result);
        dfs(root.right, result);
    }
}
