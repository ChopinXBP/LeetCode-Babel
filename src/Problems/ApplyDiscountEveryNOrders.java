package Problems;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * There is a sale in a supermarket, there will be a discount every n customer.
 * There are some products in the supermarket where the id of the i-th product is products[i]
 * and the price per unit of this product is prices[i].
 * The system will count the number of customers and when the n-th customer arrive he/she will have a discount on the bill.
 * (i.e if the cost is x the new cost is x - (discount * x) / 100). Then the system will start counting customers again.
 * The customer orders a certain amount of each product where product[i] is the id of the i-th product the customer ordered
 * and amount[i] is the number of units the customer ordered of that product.
 * Implement the Cashier class:
 *
 * Cashier(int n, int discount, int[] products, int[] prices) Initializes the object with n, the discount, the products and their prices.
 * double getBill(int[] product, int[] amount) returns the value of the bill and apply the discount if needed.
 * Answers within 10^-5 of the actual value will be accepted as correct.
 * 超市里正在举行打折活动，每隔 n 个顾客会得到 discount 的折扣。
 * 超市里有一些商品，第 i 种商品为 products[i] 且每件单品的价格为 prices[i] 。
 * 结账系统会统计顾客的数目，每隔 n 个顾客结账时，该顾客的账单都会打折，折扣为 discount
 * （也就是如果原本账单为 x ，那么实际金额会变成 x - (discount * x) / 100 ），然后系统会重新开始计数。
 * 顾客会购买一些商品， product[i] 是顾客购买的第 i 种商品， amount[i] 是对应的购买该种商品的数目。
 * 请你实现 Cashier 类：
 * Cashier(int n, int discount, int[] products, int[] prices) 初始化实例对象，
 * 参数分别为打折频率 n ，折扣大小 discount ，超市里的商品列表 products 和它们的价格 prices 。
 * double getBill(int[] product, int[] amount) 返回账单的实际金额（如果有打折，请返回打折后的结果）。
 * 返回结果与标准答案误差在 10^-5 以内都视为正确结果。
 *
 */

public class ApplyDiscountEveryNOrders {
    class Cashier {
        private final int num;
        private final double discount;
        private Map<Integer, Integer> map;
        private int count;

        public Cashier(int n, int discount, int[] products, int[] prices) {
            this.num = n;
            this.discount = 1 - (double)discount / 100;
            map = new HashMap<>();
            for (int i = 0; i < products.length; i++) {
                map.put(products[i], prices[i]);
            }
            count = 0;
        }

        public double getBill(int[] product, int[] amount) {
            double sum = 0;
            for (int i = 0; i < product.length; i++) {
                sum += map.get(product[i]) * amount[i];
            }
            return hasDiscount() ? sum * discount : sum;
        }

        private boolean hasDiscount(){
            if(++count == num){
                count = 0;
                return true;
            }
            return false;
        }
    }

/**
 * Your Cashier object will be instantiated and called as such:
 * Cashier obj = new Cashier(n, discount, products, prices);
 * double param_1 = obj.getBill(product,amount);
 */
}
