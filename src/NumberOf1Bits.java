/**
 *
 * Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 *
 */

public class NumberOf1Bits {
    public int hammingWeight(int n) {
        int result = 0;
        while(n != 0){
            result++;
            n &= n - 1;
        }
        return result;
    }
}
