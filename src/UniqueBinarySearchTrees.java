import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 */

public class UniqueBinarySearchTrees {
    //递归：超时
    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += numTrees(i) * numTrees(n - 1 - i);
        }
        return result;
    }

    //动态规划
    public int numTrees2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }

    //数学归纳法
    public int numTrees3(int n) {
        long result = 1;
        for (int i = 0; i < n; i++) {
            result = result * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //返回所有二叉搜索树
    //递归
    public List<TreeNode> generateTrees(int n) {
        if(n == 0){
            return new LinkedList<>();
        }
        return Solution(1, n);
    }

    public LinkedList<TreeNode> Solution(int begin, int end){
        LinkedList<TreeNode> result = new LinkedList<>();
        if(begin > end){
            result.add(null);
            return result;
        }

        for(int i = begin; i <= end; i++){
            LinkedList<TreeNode> leftTree = Solution(begin, i - 1);
            LinkedList<TreeNode> rightTree = Solution(i + 1, end);
            for(TreeNode left : leftTree){
                for(TreeNode right : rightTree){
                    TreeNode curTree = new TreeNode(i);
                    curTree.left = left;
                    curTree.right = right;
                    result.add(curTree);
                }
            }
        }

        return result;
    }

    //动态规划
    public List<TreeNode> generateTrees2(int n){
        ArrayList<TreeNode>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<>();
        if(n == 0){
            return dp[0];
        }

        dp[0].add(null);
        for(int len = 1; len <= n; len++){
            dp[len] = new ArrayList<>();
            for(int root = 1; root <= len; root++){
                int leftLen = root - 1;
                int rightLen = len - root;
                for(TreeNode leftTree : dp[leftLen]){
                    for(TreeNode rightTree : dp[rightLen]){
                        TreeNode treeRoot = new TreeNode(root);
                        treeRoot.left = leftTree;
                        treeRoot.right = clone(rightTree, root);
                        dp[len].add(treeRoot);
                    }
                }
            }
        }

        return dp[n];
    }

    //克隆右子树并且加上偏差
    public TreeNode clone(TreeNode n, int offset){
        if(n == null){
            return n;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }
}
