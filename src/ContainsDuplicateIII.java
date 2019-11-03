import java.util.HashMap;
/**
 *
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the
 * absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 *
 */

public class ContainsDuplicateIII {
    //桶思想+哈希表+滑动窗口
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0){
            return false;
        }
        HashMap<Long, Long> buckets = new HashMap<>();
        //滑动窗口
        for(int i = 0; i < nums.length; i++){
            long bucketIdx = getBucketID(nums[i], t);
            //若桶中已经存在元素，则其绝对值差必≤t
            if(buckets.containsKey(bucketIdx)){
                return true;
            }
            //否则检查两侧的桶的元素与当前元素差的绝对值是否符合要求
            if(buckets.containsKey(bucketIdx - 1) && Math.abs(nums[i] - buckets.get(bucketIdx - 1)) <= t){
                return true;
            }
            if(buckets.containsKey(bucketIdx + 1) && Math.abs(nums[i] - buckets.get(bucketIdx + 1)) <= t){
                return true;
            }
            //向新桶中添加元素，并且将窗口外的元素从桶中移除
            buckets.put(bucketIdx, (long)nums[i]);
            if(i >= k){
                long oldBucketIdx = getBucketID(nums[i - k], t);
                buckets.remove(oldBucketIdx);
            }
        }
        return false;
    }

    //用哈希表设计多个以t+1为桶容量的桶，桶划分为……[-(t+1),-1][0,t][t+1,2t+1]……（注意负数）。key为桶id，value为桶中的元素，每个时刻桶中仅会存在一个元素。
    private long getBucketID(long x, long t) {
        return x < 0 ? (x + 1) / (t + 1) - 1 : x / (t + 1);
    }
}
