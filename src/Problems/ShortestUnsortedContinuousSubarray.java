package Problems;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order,
 * then the whole array will be sorted in ascending order, too.
 * You need to find the shortest such subarray and output its length.
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 */

public class ShortestUnsortedContinuousSubarray {
    //排序
    public int findUnsortedSubarray(int[] nums) {
        int[] newNums = nums.clone();
        Arrays.sort(newNums);
        int begin = 0;
        while(begin < nums.length && nums[begin] == newNums[begin]){
            begin++;
        }
        int end = nums.length - 1;
        while(end >= 0 && nums[end] == newNums[end]){
            end--;
        }
        return begin < end ? end - begin + 1 : 0;
    }

    //栈
    public int findUnsortedSubarray2(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        //将所有元素依次入栈，如果当前元素比栈顶元素大，则连续弹出栈顶元素。被弹出的元素为位置不正确的元素，将最小的弹出元素为左边界
        int leftEdge = nums.length - 1;
        for(int i = 0; i < nums.length; i++){
            while(!stack.isEmpty() && nums[stack.peek()] > nums[i]){
                leftEdge = Math.min(leftEdge, stack.pop());
            }
            stack.push(i);
        }
        stack.clear();
        //同理找出右边界
        int rightEdge = 0;
        for(int i = nums.length - 1; i >= 0; i--){
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                rightEdge = Math.max(rightEdge, stack.pop());
            }
            stack.push(i);
        }
        return rightEdge - leftEdge > 0 ? rightEdge - leftEdge + 1 : 0;
    }

    //寻找最小最大边界
    public int findUnsortedSubarray3(int[] nums) {
        //minEdge(maxEdge)分别为数组开始非升序（非降序）后的最小（最大）元素，也即位置不正确的最小（最大）元素
        int minEdge = Integer.MAX_VALUE;
        boolean recordFlag = false;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < nums[i - 1]){
                recordFlag = true;
            }
            if(recordFlag){
                minEdge = Math.min(minEdge, nums[i]);
            }
        }
        int maxEdge = Integer.MIN_VALUE;
        recordFlag = false;
        for(int i = nums.length - 2; i >= 0; i--){
            if(nums[i] > nums[i + 1]){
                recordFlag = true;
            }
            if(recordFlag){
                maxEdge = Math.max(maxEdge, nums[i]);
            }
        }

        //leftEdge(rightEdge)从第一个大于minEdge（小于maxEdge）的元素开始，代表位置不正确的子数组的左起（右起）第一个元素位置
        int leftEdge = 0;
        while(leftEdge < nums.length){
            if(nums[leftEdge] > minEdge){
                break;
            }
            leftEdge++;
        }
        int rightEdge = nums.length - 1;
        while(rightEdge >= 0){
            if(nums[rightEdge] < maxEdge){
                break;
            }
            rightEdge--;
        }

        return leftEdge < rightEdge ? rightEdge - leftEdge + 1 : 0;
    }
}
