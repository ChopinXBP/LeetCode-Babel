package Problems;

/**
 *
 *  Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 *  给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 */

public class CountingBits {
    //依次计数
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for(int i = 0; i <= num; i++){
            result[i] = Solution(i);
        }
        return result;
    }

    public int Solution(int num){
        int result = 0;
        while(num != 0){
            num &= num - 1;
            result++;
        }
        return result;
    }

    //动态规划+最高有效位
    public int[] countBits2(int num) {
        int[] result = new int[num + 1];
        int offset = 0;
        int base = 1;
        //base = 1开始，将范围在[0, base)的数最高位补1，可以生成[base, 2*base)的所有数，不断循环直至num即可
        //状态转移方程dp[base + offset] = dp[offset] + 1, base = 2^k > offset
        while(base <= num){
            while(offset < base && base + offset <= num){
                result[base + offset] = result[offset] + 1;
                offset++;
            }
            offset = 0;
            base <<= 1;
        }
        return result;
    }

    //动态规划+最低有效位
    public int[] countBits3(int num) {
        int[] result = new int[num + 1];
        //每一个数字i的二进制1的个数 = i右移一位的数字二进制1的个数 + 最低位是1的情况
        //状态转移方程dp[i] = dp[i>>1] + i&1;
        for(int i = 1; i <= num; i++){
            result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }

    //动态规划+最后设置位
    public int[] countBits4(int num) {
        int[] result = new int[num + 1];
        //i&(i-1)可以去掉最右边的1，因此可以有状态转移方程dp[i] = dp[i & (i - 1)] + 1;
        for(int i = 1; i <= num; i++){
            result[i] = result[i & (i - 1)] + 1;
        }
        return result;
    }
}
