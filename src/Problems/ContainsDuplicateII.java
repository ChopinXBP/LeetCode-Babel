package Problems;

import java.util.HashSet;

/**
 *
 * Given an array of integers and an integer k,
 * find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 *
 */

public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums.length < 2){
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
        k = Math.min(k, nums.length - 1);
        for(int i = 0; i <= k; i++){
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        int beginIdx = 0;
        for(int i = k + 1; i < nums.length; i++){
            set.remove(nums[beginIdx++]);
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}
