/**
 *
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -.
 * For each integer, you should choose one from + and - as its new symbol.
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 */

public class TargetSum {
    //递归
    public int findTargetSumWays(int[] nums, int S) {
        result = 0;
        Solution(nums, 0, 0, S);
        return result;
    }

    private int result;
    public void Solution(int[] nums, int idx, int curNum, int sum){
        if(idx == nums.length){
            result = curNum == sum ? result + 1 : result;
            return;
        }
        Solution(nums, idx + 1, curNum + nums[idx], sum);
        Solution(nums, idx + 1, curNum - nums[idx], sum);
    }

    //动态规划：01背包
    //将数据N看成AB两个部分，A符号全取正，B符号全取负，有sumA-sumB = S
    //等式变换有2sumA = S+sumA+sumB = S+sumN = target
    //因此只要找到一个集合A满足sumA=target/2，即为一个可行解。
    public int findTargetSumWays2(int[] nums, int S) {
        int sumN = 0;
        for(int num : nums){
            sumN += num;
        }
        //如果数组之和不比S大，或者target是奇数则没有可行解
        if(sumN < S || ((sumN + S) & 1) == 1){
            return 0;
        }

        //dp[i]代表构成和为i的集合的数量，空集存在dp[0] = 1;
        int target = (sumN + S) >> 1;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int num : nums){
            for(int i = target; i >= num; i--){
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
}
