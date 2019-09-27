/**
 *
 * Given an integer n, return the number of trailing zeroes in n!.
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 *
 */

public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        int result = 0;
        while(n >= 5){
            result += n / 5;
            n /= 5;
        }
        return result;
    }
}
