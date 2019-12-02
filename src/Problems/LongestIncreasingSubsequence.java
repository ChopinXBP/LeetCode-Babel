package Problems;

import java.util.Arrays;

/**
 *
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 */

public class LongestIncreasingSubsequence {
    //记忆化递归：时间复杂度o(n^2)，空间复杂度o(n^2)
    public int lengthOfLIS(int[] nums) {
        //memo[i + 1][j]表示从i-j构成的序列长度
        int[][] memo = new int[nums.length + 1][nums.length];
        for(int[] list : memo){
            Arrays.fill(list, -1);
        }
        return lengthOfLIS(nums, -1, 0, memo);
    }

    public int lengthOfLIS(int[] nums, int preIdx, int curPos, int[][] memo){
        if(curPos == nums.length){
            return 0;
        }
        if(memo[preIdx + 1][curPos] > 0){
            return memo[preIdx + 1][curPos];
        }
        //如果当前元素大于之前元素，选择当前元素的递归可能
        int taken = 0;
        if(preIdx < 0 || nums[curPos] > nums[preIdx]){
            taken = lengthOfLIS(nums, curPos, curPos + 1, memo) + 1;
        }
        //不选择当前元素的递归可能
        int nottaken = lengthOfLIS(nums, preIdx, curPos, memo);

        memo[preIdx + 1][curPos] = Math.max(taken, nottaken);
        return memo[preIdx + 1][curPos];
    }

    //动态规划：时间复杂度o(n^2)，空间复杂度o(n)
    public int lengthOfLIS2(int[] nums){
        if(nums.length == 0){
            return 0;
        }
        //dp[i]代表从0-i的最长上升子序列
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int result = 1;
        for(int i = 1; i < nums.length; i++) {
            //遍历0-i的每一个元素j，如果nums[i]>nums[j]，说明可以在序列0-j的基础dp[j]上，加上一个元素i拓展序列
            //如果当前i全局最小，则dp[i]=1
            int maxval = 0;
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            result = dp[i] > result ? dp[i] : result;
        }
        return result;
    }

    //动态规划+二分搜索：时间复杂度o(nlgn)，空间复杂度o(n)
    public int lengthOfLIS3(int[] nums){
        //dp用于存储当前遇到的元素形成的上升子序列。
        int[] dp = new int[nums.length];
        int endIdx = 0;
        //遍历原序列，将每一个num二分插入dp中。如果num比dp元素大，将num插到dp最后；否则用num覆盖第一个比它大的元素。
        //遍历结束后，dp始终存储着比较小的元素（贪心），长度也与真实的最长上升子序列相同。
        for(int num : nums){
            //若num在dp中，返回下标；若num比dp元素小，返回-1；若num比dp元素大，返回-(endIdx+1)；若num可以插入dp，返回-(大于num的插入下标+1)
            int loc = Arrays.binarySearch(dp, 0, endIdx, num);
            if(loc < 0){
                loc = -(loc + 1);
            }
            dp[loc] = num;
            if(loc == endIdx){
                endIdx++;
            }
        }
        return endIdx;
    }
}
