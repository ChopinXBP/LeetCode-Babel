package Problems;

/**
 *
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero.
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
 * For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 *
 */

public class DivideTwoIntegers {
    //dividend = divisor * (2^n + 2^(n - 1)... + 2^1 + 2^0)
    //商一定可以表示为answer = 2^n + 2^(n - 1)... + 2^1 + 2^0
    public int divide(int dividend, int divisor) {
        long answer = 0;
        long up = Math.abs((long)dividend);
        long down = Math.abs((long)divisor);
        //贪心方法求商，每次dividend减去最大的divisor * 2^n
        while(up >= down){
            long count = 1;
            long base = down;
            while(up > base << 1){
                count <<= 1;
                base <<= 1;
            }
            answer += count;
            up -= base;
        }
        answer = dividend < 0 ^ divisor < 0 ? -answer : answer;
        return (answer > Integer.MAX_VALUE || answer < Integer.MIN_VALUE) ? Integer.MAX_VALUE : (int)answer;
    }
}
