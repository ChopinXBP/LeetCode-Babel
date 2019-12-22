package Problems;

import java.util.Arrays;
import java.util.TreeMap;

/**
 *
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
 * Return True if its possible otherwise return False.
 * 给你一个整数数组 nums 和一个正整数 k，请你判断是否可以把这个数组划分成一些由 k 个连续数字组成的集合。
 * 如果可以，请返回 True；否则，返回 False。
 *
 */

public class DivideArrayInSetsOfKConsecutiveNumbers {
    public boolean isPossibleDivide(int[] nums, int k) {
        if(nums.length % k != 0){
            return false;
        }
        Arrays.sort(nums);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num : nums){
            map.putIfAbsent(num, 0);
            map.put(num, map.get(num) + 1);
        }
        int times = nums.length / k;
        while(times > 0){
            int begin = map.firstKey();
            for(int i = begin; i < begin + k; i++) {
                if(!map.containsKey(i)){
                    return false;
                }
                if(map.get(i) == 1){
                    map.remove(i);
                }else{
                    map.put(i, map.get(i) - 1);
                }
            }
            times--;
        }
        return times == 0;
    }
}
