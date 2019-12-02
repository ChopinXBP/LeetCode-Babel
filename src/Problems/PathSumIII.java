package Problems;

import java.util.HashMap;

/**
 *
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * 找出路径和等于给定数值的路径总数。
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 */

public class PathSumIII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //递归:从每一个结点开始，DFS遍历所有路径
    public int pathSum(TreeNode root, int sum) {
        if(root == null){
            return 0;
        }
        return Solution(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    public int Solution(TreeNode root, int sum){
        if(root == null){
            return 0;
        }
        return (root.val == sum ? 1 : 0) + Solution(root.left, sum - root.val) + Solution(root.right, sum - root.val);
    }

    //记忆化回溯
    public int pathSum2(TreeNode root, int sum) {
        //哈希表preSum用于存储到和为key的路径条数value
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        return Solution(root, 0, sum, preSum);
    }

    public int Solution(TreeNode root, int curSum, int target, HashMap<Integer, Integer> preSum){
        if(root == null){
            return 0;
        }

        //当前路径长度为curSum，如果之前有路径长度为presum=curSum-target，说明presum至curSum对应结点间必存在一条长度为target的路径
        curSum += root.val;
        int result = preSum.getOrDefault(curSum - target, 0);
        //将curSum路径值加入哈希表，对左右子树进行回溯
        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);
        result += Solution(root.left, curSum, target, preSum) + Solution(root.right, curSum, target, preSum);
        preSum.put(curSum, preSum.get(curSum) - 1);

        return result;
    }
}
