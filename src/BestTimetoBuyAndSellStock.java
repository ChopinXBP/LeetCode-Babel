/**
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票。
 *
 */

public class BestTimetoBuyAndSellStock {

    //两个数组，一个数组记录每个i对应的最小买入价格；一个数组记录每个i对应的最大卖出价格
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if(length < 2)
            return 0;
        int[] buyprice = new int[length];
        int[] sellprice = new int[length];
        int buy = prices[0];
        int sell = prices[length - 1];
        for(int i = 0; i < length; i++){
            if(prices[i] < buy){
                buyprice[i] = prices[i];
                buy = prices[i];
            }else{
                buyprice[i] = buy;
            }
            if(prices[length - 1 - i] > sell){
                sellprice[length - 1 - i] = prices[length - 1 - i];
                sell = prices[length - 1 - i];
            }else{
                sellprice[length - 1 - i] = sell;
            }
        }
        int profit = 0;
        for(int i = 0; i < length; i++){
            if(sellprice[i] - buyprice[i] > profit){
                profit = sellprice[i] - buyprice[i];
            }
        }
        return profit;
    }

    //记录最小买入价格与最大利润，动态更新
    public int maxProfit2(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}
