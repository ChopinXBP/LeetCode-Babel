package Problems;

import java.util.Arrays;

/**
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 */

public class CoinChange2 {
    public int change(int amount, int[] coins) {
        //dp[sum]表示凑成总金额sum的硬币组合数
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        //每次更新在当前面额下所有总额的组合数，当前sum的组合数等于所有sum-coin的组合数加上coin面额的钞票
        //内外层循环不能颠倒，外层循环每过一个面值的coin，在当前coin数量下所有可以实现的总额必然不会用到大于coin面值的硬币
        //且已经遍历了所有小于coin面值硬币的组合，不会和新加入的面值重复。
        for (int coin : coins) {
            for (int sum = coin; sum <= amount; sum++) {
                dp[sum] += dp[sum - coin];
            }
        }
        return dp[amount];
    }
}
