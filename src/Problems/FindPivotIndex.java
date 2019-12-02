package Problems;

/**
 *
 * Given an array of integers nums, write a method that returns the "pivot" index of this array.
 * We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.
 * If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
 * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 *
 */

public class FindPivotIndex {
    public int pivotIndex(int[] nums) {
        if(nums.length == 0){
            return -1;
        }
        int pivot = 0;
        int sumLeft = 0;
        int sumRight = 0;
        for(int i = 1; i < nums.length; i++){
            sumRight += nums[i];
        }
        if(sumLeft == sumRight){
            return pivot;
        }
        while(pivot < nums.length - 1){
            sumLeft += nums[pivot++];
            sumRight -= nums[pivot];
            if(sumLeft == sumRight){
                return pivot;
            }
        }
        return -1;
    }
}
