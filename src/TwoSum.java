import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 */

public class TwoSum {

    //排序找首尾o（nlgn）
    public int[] twoSum2(int[] nums, int target) {
        int[] newnums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(newnums);
        int begin = 0;
        int end = newnums.length - 1;
        while(begin <= end){
            if(newnums[begin] + newnums[end] > target)
                end--;
            else if(newnums[begin] + newnums[end] < target)
                begin++;
            else
                break;
        }
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == newnums[begin]) {
                result[0] = i;
                break;
            }
        }
        for(int i = nums.length - 1; i >= 0; i--){
            if(nums[i] == newnums[end]) {
                result[1] = i;
                break;
            }
        }
        return result;
    }

    //两次哈希表o（n）
    public int[] twoSum1(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        //记录元素下标，如果存在相同元素，记录后一个元素的下标
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], i);
        }
        for(int i = 0 ; i < nums.length; i++){
            int temp = target - nums[i];
            //该元素不能是nums[i]本身，如果存在两个相同元素，由于数组从前往后遍历，不会取到i本身
            if(map.containsKey(temp) && (map.get(temp) != i))
                return new int[]{i, map.get(temp)};
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    //一次哈希表o(n)
    public int[] twoSum(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        //在记录下标的同时回访之前已经加入的元素
        for(int i = 0; i < nums.length; i++){
            int temp = target - nums[i];
            if(map.containsKey(temp))
                return new int[]{map.get(temp), i};
            else
                map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
