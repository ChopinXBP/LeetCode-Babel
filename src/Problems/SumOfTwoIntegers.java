package Problems;

/**
 *
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 *
 */

public class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        int loc = a ^ b;
        int bit = (a & b) << 1;
        while(bit != 0){
            int carrybit = (loc & bit) << 1;
            loc = loc ^ bit;
            bit = carrybit;
        }
        return loc;
    }
}
