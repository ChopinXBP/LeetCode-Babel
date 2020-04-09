package Problems;

import java.util.Arrays;

/**
 *
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal
 * , where a move is incrementing n - 1 elements by 1.
 * 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动可以使 n - 1 个元素增加 1。
 *
 */

public class MinimumMovesToEqualArrayElements {
    //排序后，第1次更新除最大值nums[len-1]外的其余值，更新nums[i]-nums[0]次，使得nums[0]=nums[len-1]同为最小值
    //而nums[len-2]为新的最大值，第二次更新nums[len-2]-nums[0]次，使得nums[0]=nums[len-1]=nums[len-2]同为最小值
    //循环往复直至nums[0]为最大值
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            result += nums[i] - nums[0];
        }
        return result;
    }
}
