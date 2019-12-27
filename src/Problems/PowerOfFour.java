package Problems;

/**
 *
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
 *
 */

public class PowerOfFour {
    //1.该数是2的幂（仅有一位为1），用n&(n-1)可以验证（去掉最右边的1）
    //2.这个1出现在奇数位上，用0x[5] = 0b[0101]可以验证
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) > 0;
    }

    //1.该数是2的幂（仅有一位为1），用n&(n-1)可以验证（去掉最右边的1）
    //2.保证上一条的前提下，可以将4的幂写成(3+1)^n的展开（一个数的N次方-1总能除尽比这个数小1的数）
    public boolean isPowerOfFour2(int num) {
        return num > 0 && (num & (num - 1)) == 0 && num % 3 == 1;
    }

    //换底公式求n的幂次：4^n=lg10(n)/lg10(4)
    public boolean isPowerOfFour3(int num) {
        double newnum = Math.log10(num) / Math.log10(4);
        return newnum == (int)newnum;
    }
}
