package Problems;

/**
 *
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given two integers x and y, calculate the Hamming distance.
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 */

public class HammingDistance {
    //汉明距离表示两个相同长度数字对应位不同的数量
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
