package Problems;

import java.util.Arrays;

/**
 *
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * Note:Each of the array element will not exceed 100. The array size will not exceed 200.
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 注意: 每个数组中的元素不会超过 100. 数组的大小不会超过 200
 *
 */

public class PartitionEqualSubsetSum {
    //动态规划：01背包
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if((sum & 1) == 1){
            return false;
        }

        //dp[i][j]代表从在数组前i个数字中不重复取数能否组成和为j的数
        int target = sum >> 1;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        dp[0][0] = true;

        for(int i = 1; i <= nums.length; i++){
            for(int j = target; j >= 0; j--){
                //在数组前i个数字中不重复取数能否组成和为j的数，可能有两种情况：
                //1.数组的前i-1个数字就已经能够组合和为j的数，状态转移方程：dp[i][j] = dp[i - 1][j];
                //2.加上第i个数字nums[i]正好使得条件达成，状态转移方程：dp[i][j] = dp[i - 1][j - nums[i-1]];
                dp[i][j] = dp[i - 1][j] || (j >= nums[i - 1] && dp[i - 1][j - nums[i - 1]]);
            }
        }

        return dp[nums.length][target];
    }

    //一维动态规划
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if((sum & 1) == 1){
            return false;
        }

        int target = sum >> 1;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for(int num : nums){
            for(int j = target; j >= num; j--){
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[target];
    }
}
