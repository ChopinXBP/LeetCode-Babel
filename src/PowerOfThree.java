/**
 *
 * Given an integer, write a function to determine if it is a power of three.
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 *
 */

public class PowerOfThree {
    //进制转换：换为3进制数，且字符串满足最高位为1其余为0
    public boolean isPowerOfThree(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }

    //换底公式：用%1判断log3(n)是否为整数
    public boolean isPowerOfThree2(int n){
        //用log代替log10会产生double精度误差
        //return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon;
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    //最大指数：int型中3的幂最大值为19，对应数字为1162261467
    public boolean isPowerOfThree3(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
