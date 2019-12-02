package Problems;

/**
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 */

public class BestTimeToBuyAndSellStockWithCooldown {
    //动态规划
    public int maxProfit(int[] prices) {
        if(prices.length == 0){
            return 0;
        }
        //dp[i][0]代表股票第i天休市时的状态是不持有所得的最大利润，dp[i][1]代表股票第i天休市时的状态是持有所得的最大利润
        int[][] dp = new int[prices.length + 1][2];
        dp[0][1] = Integer.MIN_VALUE;
        dp[1][1] = -prices[0];
        for(int i = 2; i <= prices.length; i++){
            //第i天休市时的状态是不持有有两种可能：第i-1天也不持有，第i天保持；第i-1天持有，第i天卖出。
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
            //第i天休市时的状态是持有有两种可能：第i-1天也持有，第i天保持；第i-2天不持有，第i-1天冷冻，第i天买入。
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i - 1]);
        }
        return dp[prices.length][0];
    }

    //压缩至一维
    public int maxProfit2(int[] prices) {
        int preSellValue = 0;
        int buyValue = Integer.MIN_VALUE;
        int sellValue = 0;
        for(int i = 0; i < prices.length; i++){
            int temp = sellValue;
            sellValue = Math.max(sellValue, buyValue + prices[i]);
            buyValue = Math.max(buyValue, preSellValue - prices[i]);
            preSellValue = temp;
        }
        return sellValue;
    }
}
