/**
 *
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1.Given N, calculate F(N).
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。给定 N，计算 F(N)。
 *
 */

public class FibonacciNumber {
    public int fib(int N) {
        int cur = 1;
        int pre = 0;
        while(N-- > 0){
            int temp = cur + pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
