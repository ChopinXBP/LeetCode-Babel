package Problems;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Given two arrays, write a function to compute their intersection.
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 */

public class IntersectionOfTwoArraysII {
    public int[] intersect2(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums1){
            map.putIfAbsent(num, 0);
            map.replace(num, map.get(num) + 1);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int num : nums2){
            if(map.containsKey(num) && map.get(num) > 0){
                list.add(num);
                map.replace(num, map.get(num) - 1);
            }
        }

        int[] result = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }
}
