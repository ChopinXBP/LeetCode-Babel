package Problems;

/**
 *
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor and divide all the array by it and sum the result of the division.
 * Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
 * Each result of division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
 * It is guaranteed that there will be an answer.
 * 给你一个整数数组 nums 和一个正整数 threshold  ，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。
 * 请你找出能够使上述结果小于等于阈值 threshold 的除数中 最小 的那个。
 * 每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。
 * 题目保证一定有解。
 *
 */

public class FindTheSmallestDivisorGivenAThreshold {
    public int smallestDivisor(int[] nums, int threshold) {
        int max = Integer.MIN_VALUE;
        for(int num : nums){
            max = num > max ? num : max;
        }
        int begin = 1;
        int end = max;
        while(begin < end){
            int mid = (begin + end) >> 1;
            int curSum = Solution(nums, mid);
            if(curSum > threshold){
                begin = mid + 1;
            }else{
                end = mid;
            }
        }
        return begin;
    }

    private int Solution(int[] nums, int core){
        int result = 0;
        for(int num : nums) {
            result += num / core + (num % core == 0 ? 0 : 1);
        }
        return result;
    }
}

