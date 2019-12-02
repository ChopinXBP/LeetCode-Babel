package Problems;

import java.util.HashSet;
import java.util.Map;

/**
 *
 * Given two arrays, write a function to compute their intersection.
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 */

public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for(int num : nums1){
            set1.add(num);
        }
        for(int num : nums2){
            if(set1.contains(num)){
                set2.add(num);
            }
        }
        int[] result = new int[set2.size()];
        int idx = 0;
        for (int num : set2){
            result[idx++] = num;
        }
        return result;
    }
}
