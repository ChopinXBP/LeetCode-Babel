package Problems;

/**
 *
 * Reverse bits of a given 32 bits unsigned integer.
 * 颠倒给定的 32 位无符号整数的二进制位。
 *
 */

public class ReverseBits {
    public int reverseBits(int n) {
        int result = 0;
        int count = 0;
        while(count++ < 32){
            result <<= 1;
            int flag = (n & 1) == 1 ? 1 : 0;
            result += flag;
            n >>= 1;
        }
        return result;
    }
}
