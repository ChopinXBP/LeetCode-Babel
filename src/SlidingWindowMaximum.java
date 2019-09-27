import java.util.LinkedList;

/**
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 */

public class SlidingWindowMaximum {

    //动态规划 + 双向链表
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || k == 0){
            return new int[0];
        }
        //创建双向队列list，每次遍历将小于nums[i]的队首元素出列，并将i放入队首。删去过期元素（超出窗口）后的队尾即为当前窗口元素最大值。
        LinkedList<Integer> list = new LinkedList<>();
        list.add(0);
        for(int i = 1; i < k - 1; i++){
            while(!list.isEmpty() && nums[i] > nums[list.peekFirst()]) {
                list.pollFirst();
            }
            list.addFirst(i);
        }
        int[] result = new int[nums.length - k + 1];
        for(int i = k - 1; i < nums.length; i++){
            while(!list.isEmpty() && nums[i] > nums[list.getFirst()]){
                list.pollFirst();
            }
            list.addFirst(i);
            while(i - list.getLast() >= k){
                list.pollLast();
            }
            result[i - k + 1] = nums[list.getLast()];
        }
        return result;
    }

    //动态规划 + 子块分割
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if(nums.length == 0 || k < 2){
            return nums;
        }

        //将数组分为长度为k的若干子块
        //left[i]代表从块首至i的元素最大值，动态转移方程为Math.max(left[i - 1], nums[i]);
        //right[j]代表从块尾至j的元素最大值，动态转移方程为Math.max(right[j + 1], nums[j]);
        int[] left = new int[nums.length];
        left[0] = nums[0];
        int[] right = new int[nums.length];
        right[nums.length - 1] = nums[nums.length - 1];
        for(int i = 1, j = nums.length - 2; i < nums.length; i++, j--){
            left[i] = i % k == 0 ? nums[i] : Math.max(left[i - 1], nums[i]);
            right[j] = (j + 1) % k == 0 ? nums[j] : Math.max(right[j + 1], nums[j]);
        }

        //长度为k的窗口i-j的最大值为Math.max(right[i], left[j])
        //如果恰为子块之一，那最大值 = right[i] = left[j]
        //若跨a-b子块，则right[i]为a子块中的最大值，left[j]为b子块中的最大值，取较大者即可。
        int[] result = new int[nums.length - k + 1];
        for(int i = 0; i < result.length; i++){
            result[i] = Math.max(right[i], left[i + k - 1]);
        }

        return result;
    }
}
