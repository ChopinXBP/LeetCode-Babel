import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 *
 */

public class LongestConsecutiveSequence {
    //桶排序+动态规划
    //无法通过测试用例[2147483646,-2147483647,0,2,2147483644,-2147483645,2147483645]
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        boolean[] bucket = new boolean[max - min + 1];
        for(int num : nums){
            bucket[num - min] = true;
        }

        int left = 0;
        int right = 0;
        int curlength = 0;
        int result = 0;
        while(right < bucket.length){
            while(right < bucket.length && !bucket[right]){
                right++;
                left = right;
            }
            while(right < bucket.length && bucket[right]){
                right++;
            }
            curlength = right - left;
            result = curlength > result ? curlength : result;
        }
        return result;
    }

    //哈希表+动态规划
    public int longestConsecutive2(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }

        int result = 0;
        for(int num : set){
            //若num-1不在哈希表中，说明num是连续序列的第一个元素，由此元素开始遍历
            if(!set.contains(num - 1)){
                int curNum = num;
                int curLen = 1;
                while(set.contains(curNum + 1)){
                    curNum++;
                    curLen++;
                }
                result = curLen > result ? curLen : result;
            }
        }

        return result;
    }
}
