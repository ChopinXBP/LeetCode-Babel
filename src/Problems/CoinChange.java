package Problems;

import java.util.Arrays;

/**
 *
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 */

public class CoinChange {

    //动态规划
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        //每一次value循环的dp[sum]表示，在加入面值为value的硬币之后，获得总额为sum所需要的最少硬币数
        for(int value : coins){
            for(int sum = value; sum <= amount; sum++){
                dp[sum] = Math.min(dp[sum], dp[sum - value] + 1);
            }
        }

        return dp[amount] >= amount + 1 ? -1 : dp[amount];
    }

    //贪心 + 剪枝DFS
    int mintimes = Integer.MAX_VALUE;
    public int coinChange1(int[] coins, int amount){
        //贪心1：硬币面值按照从大到小的顺序取
        Arrays.sort(coins);
        Solution(coins, coins.length - 1, amount, 0);
        return mintimes == Integer.MAX_VALUE ? - 1 : mintimes;
    }

    public void Solution(int[] coins, int coinidx, int amount, int times) {
        int value = coins[coinidx];
        int maxnums = amount / value;
        if (coinidx == 0) {
            if (amount % value == 0) {
                mintimes = times + maxnums < mintimes ? times + maxnums : mintimes;
            }
        } else {
            //贪心2：当前面值使用次数按照从大到小的顺序取
            //剪枝：减去当面面值使用次数超过最小次数的枝干
            for (int usenums = maxnums; usenums >= 0 && usenums < mintimes - times; --usenums) {
                Solution(coins, coinidx - 1, amount - value * usenums, times + usenums);
            }
        }
    }
}
