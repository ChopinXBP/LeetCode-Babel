/**
 *
 * Given an integer, write a function to determine if it is a power of two.
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 */

public class PowerofTwo {

    //移位运算：二进制各位上只能出现一次1。
    public boolean isPowerOfTwo(int n) {
        while(n > 0){
            if((n & 0x01) == 1)
                break;
            n = n >> 1;
        }
        return n > 0 && (n >> 1) == 0;
    }

    //移位运算：1<<30得到最大的2的整数次幂，对n取模如果等于0，说明n只有因子2。
    public boolean isPowerOfTwo1(int n) {
        return n > 0 && (1 << 30) % n == 0;
    }

    //&运算：2的幂次方与自身负数相与返回原数，例如8&(-8)->00001000 & 11111000 得 00001000，即8。
    public boolean isPowerOfTwo2(int n){
        return n > 0 && (n & -n) == n;
    }

}
