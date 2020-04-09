package Problems;

/**
 *
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist,
 * return the maximum number. The time complexity must be in O(n).
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 *
 */

public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        long max = Long.MIN_VALUE;
        long mid = Long.MIN_VALUE;
        long min = Long.MIN_VALUE;
        for(int num : nums){
            if(num == max || num == mid || num == min){
                continue;
            }
            if(num > max){
                min = mid;
                mid = max;
                max = num;
            }else if(num > mid){
                min = mid;
                mid = num;
            }else if(num > min){
                min = num;
            }
        }
        return min == Long.MIN_VALUE ? (int)max : (int)min;
    }
}
