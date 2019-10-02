/**
 *
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root."
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 */

public class HouseRobberIII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int rob(TreeNode root) {
        root = Solution(root);
        return root.val;
    }

    //树形动态规划：自底向上
    //参考LeetCode198打家劫舍I的一维dp思路：dp[i] = Math.max(dp[i-2]+nums[cur], dp[i-1])，每一个节点的dp值与三层二叉树的结点dp值相关。
    public TreeNode Solution(TreeNode root){
        //设定初值：在叶子结点和空结点下进行构建，使得每一个非叶结点作为根节点的子树都是一棵三层满二叉树，方便动态转移方程运算。
        //对于左/右子树为空的非叶节点，我们在其左/右添加一棵两层值为0的满二叉树。
        //对于叶子结点，我们给其添加值为0的左右子结点。
        if(root == null){
            TreeNode newNode = new TreeNode(0);
            return Solution(newNode);
        }
        if(root.left == null && root.right == null){
            root.left = new TreeNode(0);
            root.right = new TreeNode(0);
            return root;
        }
        root.left = Solution(root.left);
        root.right = Solution(root.right);

        //那么我们可以自底向上递归进行这个dp运算，令dp[i]代表以i结点为根节点的子树的最大偷窃金额值，计算结束后将dp值直接保存在i结点的val值当中返回。
        //在每个结点的金额非负的情况下，且要保证取值结点不相邻，三层满二叉树的最大取值只可能有四种情况。
        //状态转移方程为dp[root] = Max(dp[l]+dp[r], root.val+dp[ll]+dp[lr]+dp[rr]+dp[rl], dp[l]+dp[rl]+dp[rr], dp[r]+dp[lr]+dp[rl])
        int case1 = root.left.val + root.right.val;
        int case2 = root.val + root.left.left.val + root.left.right.val + root.right.left.val + root.right.right.val;
        int case3 = root.left.val + root.right.left.val + root.right.right.val;
        int case4 = root.right.val + root.left.left.val + root.left.right.val;
        root.val = Math.max(Math.max(case1, case2), Math.max(case3, case4));

        return root;
    }
}
