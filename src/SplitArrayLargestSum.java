/**
 *
 *  Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
 *  Write an algorithm to minimize the largest sum among these m subarrays.
 *  给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 *
 */

public class SplitArrayLargestSum {
    //最大和最小问题
    public int splitArray(int[] nums, int m) {
        long max = Integer.MIN_VALUE;
        long sum = 0;
        for(int num : nums){
            max = num > max ? num : max;
            sum += num;
        }
        //对分隔数组最大和（最大桶容量）进行二分查找，范围为[数组最大元素值，数组元素和]
        long begin = max;
        long end = sum;
        while(begin < end){
            long mid = (begin + end) >> 1;
            //判断在当前桶数量m和最大桶容量mid限定下，数组能否完成分割
            if(canBeSplit(nums, m, mid)){
                end = mid;
            }else{
                begin = mid + 1;
            }
        }
        return (int)begin;
    }

    private boolean canBeSplit(int[] nums, int m, long maxSum){
        long curSum = 0;
        long curNum = 0;
        for(int i = 0; i < nums.length; i++){
            curSum += nums[i];
            if(curSum > maxSum){
                curSum = nums[i];
                curNum++;
            }else if(curSum == maxSum){
                curSum = 0;
                curNum++;
            }
            if(curNum > m){
                return false;
            }
        }
        if(curSum > 0){
            curNum++;
        }
        return curNum <= m;
    }
}
