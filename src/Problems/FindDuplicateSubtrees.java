package Problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure with same node values.
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 *
 */

public class FindDuplicateSubtrees {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    //二叉树序列化+哈希表
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        ArrayList<TreeNode> result = new ArrayList<>();
        Solution(root, new HashMap<>(), result);
        return result;
    }

    private String Solution(TreeNode root, HashMap<String, Integer> map, ArrayList<TreeNode> result){
        if(root == null){
            return "#";
        }
        //后序遍历序列化的二叉树
        String serial = root.val + "," + Solution(root.left, map, result) + "," + Solution(root.right, map, result);
        //只在第一次重复时加入
        if(map.getOrDefault(serial, 0) == 1){
            result.add(root);
        }
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        return serial;
    }
}
