import java.util.*;

/**
 *
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream.
 * For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 *
 */

public class KthLargestElementInAStream {
    class KthLargest {
        private PriorityQueue<Integer> minheap;
        private int size;

        public KthLargest(int k, int[] nums) {
            minheap = new PriorityQueue<>();
            for(int num : nums){
                minheap.add(num);
            }
            size = k;
            while(minheap.size() > size){
                minheap.poll();
            }
        }

        public int add(int val) {
            minheap.add(val);
            if(minheap.size() > size){
                minheap.poll();
            }
            return minheap.peek();
        }
    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
}
