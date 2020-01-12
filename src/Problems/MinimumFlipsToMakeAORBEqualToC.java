package Problems;

/**
 *
 * Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
 * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
 * 给你三个正整数 a、b 和 c。
 * 你可以对 a 和 b 的二进制表示进行位翻转操作，返回能够使按位或运算   a OR b == c  成立的最小翻转次数。
 * 「位翻转操作」是指将一个数的二进制表示任何单个位上的 1 变成 0 或者 0 变成 1 。
 *
 */

public class MinimumFlipsToMakeAORBEqualToC {
    public int minFlips(int a, int b, int c) {
        int result = 0;
        int mask = 0x80000000;
        for (int i = 0; i < 32; i++){
            if((c & mask) == 0){
                result = (a & mask) == 0 ? result : result + 1;
                result = (b & mask) == 0 ? result : result + 1;
            }else {
                result = (mask & (a | b)) > 0 ? result : result + 1;
            }
            mask >>= 1;
            System.out.println(mask);
        }
        return result;
    }
}
