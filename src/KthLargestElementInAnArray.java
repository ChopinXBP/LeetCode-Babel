import java.util.PriorityQueue;

/**
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 */

public class KthLargestElementInAnArray {

    //最小堆
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        for(int num : nums){
            minheap.add(num);
            if(minheap.size() > k)
                minheap.poll();
        }
        return minheap.poll();
    }

    //快速排序
    public int findKthLargest1(int[] nums, int k){
        //第k大的数在升序数组中的下标是nums.length - k
        return findKthNum(nums, nums.length - k, 0, nums.length - 1);
    }

    private int findKthNum(int[] nums, int idx, int begin, int end){
        while(begin < end){
            int pivot = partition(nums, begin, end);
            if(pivot < idx)
                begin = pivot + 1;
            else if(pivot > idx)
                end = pivot - 1;
            else
                return nums[pivot];
        }
        return nums[begin];

        //若要进行快排
        /*
        if(begin < end){
            int pivot = partition(nums, begin, end);
            findKthNum(nums, begin, pivot - 1);
            findKthNum(nums, pivot + 1, end);
        }
        */
    }

    private int partition(int[] nums, int begin, int end){
        int pivot = begin;
        while(begin <= end){
            while(begin <= end && nums[begin] <= nums[pivot])
                begin++;
            while(begin <= end && nums[end] > nums[pivot])
                end--;
            if(begin > end)
                break;
            swap(nums, begin, end);
        }
        //此时end的右边数字均比nums[pivot]大，交换后pivot对应数字位于正确位置end
        swap(nums, pivot, end);
        return end;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
