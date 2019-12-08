package Problems;

/**
 *
 * Given an integer number n, return the difference between the product of its digits and the sum of its digits.
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 *
 */

public class SubtractTheProductAndSumOfDigitsOfAnInteger {
    public int subtractProductAndSum(int n) {
        String num = n + "";
        int time = 1;
        int sum = 0;
        for(char c : num.toCharArray()){
            int m = c - '0';
            time *= m;
            sum += m;
        }
        return time - sum;
    }
}
