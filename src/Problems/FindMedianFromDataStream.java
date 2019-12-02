package Problems;

import java.util.*;

/**
 *
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * For example, [2,3,4], the median is 3. [2,3], the median is (2 + 3) / 2 = 2.5
 * Design a data structure that supports the following two operations:
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 例如，[2,3,4] 的中位数是 3. [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 */

public class FindMedianFromDataStream {
    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */
    class MedianFinder {

        PriorityQueue<Integer> minheap;
        PriorityQueue<Integer> maxheap;
        boolean oddFlag;

        /** initialize your data structure here. */
        public MedianFinder() {
            minheap = new PriorityQueue<>();
            maxheap = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            oddFlag = false;
        }

        public void addNum(int num) {
            if(oddFlag){
                minheap.add(num);
                maxheap.add(minheap.poll());
            }else{
                maxheap.add(num);
                minheap.add(maxheap.poll());
            }
            oddFlag = !oddFlag;
        }

        public double findMedian() {
            return minheap.isEmpty() ? 0 : (oddFlag ? minheap.peek() : ((double)maxheap.peek() + minheap.peek()) / 2);
        }
    }
}
