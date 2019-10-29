import java.util.Arrays;

/**
 *
 * Given an integer array, return the k-th smallest distance among all the pairs.
 * The distance of a pair (A, B) is defined as the absolute difference between A and B.
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 *
 */

public class FindKthSmallestPairDistance {
    //二分查找+前缀和
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        //二分查找中前缀和计算所需最大距离为最大值的两倍
        int width = nums[nums.length - 1] << 1;

        //sameValue[i]代表在i之前且和i相等的元素个数
        int[] sameValue = new int[nums.length];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i - 1]){
                sameValue[i] = sameValue[i - 1] + 1;
            }
        }

        //smallerNums[i]表示≤当前坐标i的元素个数
        int[] smallerNums = new int[width];
        int left = 0;
        for(int i = 0; i < width; i++){
            while(left < nums.length && nums[left] <= i){
                left++;
            }
            smallerNums[i] = left;
        }

        //第k小的距离一定出现在[0, max-min]之中，对此距离进行二分查找
        int begin = 0;
        int end = nums[nums.length - 1] - nums[0];
        while(begin < end){
            int mid = (begin + end) >> 1;
            //对于每一个位置i，满足i<j && nums[j]-nums[i]≤mid的j的个数为：从nums[i]到nums[i]+mid的元素个数（前缀和） + 在i之前且和i相等的元素个数
            //count = smallerNums[nums[i] + mid] - smallerNums[nums[i]] + sameValue[i]
            int count = 0;
            for(int i = 0; i < nums.length; i++){
                count += smallerNums[nums[i] + mid] - smallerNums[nums[i]] + sameValue[i];
            }
            if(count >= k){
                end = mid;
            }else{
                begin = mid + 1;
            }
        }

        return begin;
    }

    //二分查找+双指针
    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);
        //第k小的距离一定出现在[0, max-min]之中，对此距离进行二分查找
        int begin = 0;
        int end = nums[nums.length - 1] - nums[0];
        while(begin < end){
            int mid = (begin + end) >> 1;
            //对于每一个位置left，满足left<right && nums[right]-nums[left]≤mid的j的个数可以通过双指针遍历求得
            int count = 0;
            int left = 0;
            for(int right = 0; right < nums.length; right++){
                while(nums[right] - nums[left] > mid){
                    left++;
                }
                count += right - left;
            }
            if(count >= k){
                end = mid;
            }else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
