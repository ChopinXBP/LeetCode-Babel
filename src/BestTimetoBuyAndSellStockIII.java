/**
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 */

public class BestTimetoBuyAndSellStockIII {
    public static void main(String[] args){
        int[] prices = {1,2,4,2,5,7,2,4,9,0};
        System.out.println(maxProfit(prices));
    }
    /**
     对于任意一天考虑四个变量:
     fstBuy: 在该天第一次买入股票可获得的最大收益
     fstSell: 在该天第一次卖出股票可获得的最大收益
     secBuy: 在该天第二次买入股票可获得的最大收益
     secSell: 在该天第二次卖出股票可获得的最大收益
     分别对四个变量进行相应的更新, 最后secSell就是最大收益值(secSell >= fstSell)
     **/
    public static int maxProfit(int[] prices) {
        int fstBuy = Integer.MIN_VALUE, fstSell = 0;
        int secBuy = Integer.MIN_VALUE, secSell = 0;
        for(int p : prices) {
            fstBuy = Math.max(fstBuy, -p);
            fstSell = Math.max(fstSell, fstBuy + p);
            secBuy = Math.max(secBuy, fstSell - p);
            secSell = Math.max(secSell, secBuy + p);
        }
        return secSell;
    }
}
