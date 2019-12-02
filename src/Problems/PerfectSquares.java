package Problems;

/**
 *
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 */

public class PerfectSquares {
    public int numSquares(int n) {
        int sqrtNum = (int)Math.sqrt(n);
        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++){
            dp[i] = i;
        }

        for(int i = 2; i <= sqrtNum; i++){
            int powNum = i * i;
            for(int j = powNum; j <= n; j++){
                dp[j] = Math.min(dp[j], dp[j - powNum] + 1);
            }
        }

        return dp[n];
    }
}
