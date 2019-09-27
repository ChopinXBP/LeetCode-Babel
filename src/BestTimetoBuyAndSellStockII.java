/**
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 */

public class BestTimetoBuyAndSellStockII {
    //峰谷法：
    //若单次最佳买卖点之间存在局部波峰和波谷，那么直接进行单次买卖获得的总利润总是小于包含波峰波谷的多次买卖所获得的利润
    //因此对所有波峰波谷记录并进行多次买卖
    public int maxProfit(int[] prices) {
        if(prices.length < 2)
            return 0;
        int buyprice = prices[0];
        int sellprice = prices[0];
        int profit = 0;

        int idx  = 0;
        while(idx < prices.length - 1){
            while(idx < prices.length - 1 && prices[idx] >= prices[idx + 1])
                idx++;
            buyprice = prices[idx];
            while(idx < prices.length - 1 && prices[idx] <= prices[idx + 1])
                idx++;
            sellprice = prices[idx];
            profit += sellprice - buyprice;
        }
        return profit;
    }

    //记录所有波峰波谷或者连续提升进行加和
    public int maxProfit1(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}
