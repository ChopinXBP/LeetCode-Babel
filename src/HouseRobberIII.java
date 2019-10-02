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
    //参考LeetCode198打家劫舍I的一维dp思路：dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1])，每一个结点的dp值取决于同一枝干前两个结点的dp值
    public TreeNode Solution(TreeNode root){
        //为树状dp数组设定初值：在叶子结点和空结点下构建子树，使得每一个非叶结点的动态转移方程的运算子结构都是一棵三层满二叉树
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
        //将所有dp值保存在已经遍历过的结点中，在结点值均为非负的情况下，在相邻结点不能同取的要求下，三层满二叉树的最大取值只可能有四种情况。
        //因此状态转移方程为dp[root] = Max(dp[l]+dp[r], root.val+dp[ll]+dp[lr]+dp[rr]+dp[rl], dp[l]+dp[rl]+dp[rr], dp[r]+dp[lr]+dp[rl])
        int case1 = root.left.val + root.right.val;
        int case2 = root.val + root.left.left.val + root.left.right.val + root.right.left.val + root.right.right.val;
        int case3 = root.left.val + root.right.left.val + root.right.right.val;
        int case4 = root.right.val + root.left.left.val + root.left.right.val;
        root.val = Math.max(Math.max(case1, case2), Math.max(case3, case4));
        return root;
    }
}
