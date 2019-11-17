import java.util.Arrays;

/**
 *
 * 5265. 可被三整除的最大和
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 *
 */

public class GreatestSumDivisibleByThree {
    //01背包
    public int maxSumDivThree(int[] nums) {
        //dp[i][j]代表到第i位数字（num[i-1]）模3的余数为j的最大累加和
        //i=0时的初始值为{0,-INT,-INT}，若第1位数字num是3的倍数，则dp={num,-INT,-INT}；若不是，则dp={0,num,-INT}/{0,-INT,num}
        int[][] dp = new int[nums.length + 1][3];
        for(int[] list : dp){
            Arrays.fill(list, Integer.MIN_VALUE);
        }
        dp[0][0] = 0;

        for(int i = 1; i <= nums.length; i++){
            //利用第i位数字（num[i-1]），计算与上一轮累加和的新模值newmod=(oldmod+num[i-1])%3
            //最新累加和可以通过01背包思路建立动态转移方程
            //dp[i][newmod] = Math.max(dp[i-1][newmod], dp[i-1][oldmod] + nums[i-1]);
            for(int j = 0; j < 3; j++){
                int mod = (j + nums[i - 1]) % 3;
                dp[i][mod] = Math.max(dp[i - 1][mod], dp[i - 1][j] + nums[i - 1]);
            }
        }
        return dp[nums.length][0];
    }
}
