package Problems;

/**
 *
 * Given an integer n, return any array containing n unique integers such that they add up to 0.
 * 给你一个整数 n，请你返回 任意 一个由 n 个 各不相同 的整数组成的数组，并且这 n 个数相加和为 0 。
 *
 */

public class FindNUniqueIntegersSumUpToZero {
    public int[] sumZero(int n) {
        int[] result = new int[n];
        result[0] = n >> 1;
        result[n - 1] = -result[0];
        int begin = 1;
        int end = n - 2;
        while(begin < end){
            result[begin] = result[begin - 1] - 1;
            result[end] = result[end + 1] + 1;
            begin++;
            end--;
        }
        return result;
    }
}
