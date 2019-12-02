package Problems;

import java.util.Stack;

/**
 *
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 */

public class LargestRectangleInHistogram {
    //方法一：左右扫描。以每一根柱子为中心向左右拓展获得最大区域
    public int largestRectangleArea(int[] heights) {
        int result = 0;
        for(int i = 0; i < heights.length; i++){
            int begin = i;
            while(begin >= 0 && heights[begin] >= heights[i]){
                --begin;
            }
            int end = i;
            while(end < heights.length && heights[end] >= heights[i]){
                ++end;
            }
            if(heights[i] * (end - begin - 1) > result){
                result = heights[i] * (end - begin - 1);
            }
        }
        return result;
    }

    //方法二：单调递增栈。利用辅助栈动态存储当前最大高度柱子对应下标
    public int largestRectangleArea1(int[] heights){
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for(int i = 0; i <= heights.length; i++){
            //最后一次扫描令柱子的高度为0，使栈中元素全部弹出
            int height = i == heights.length ? 0 : heights[i];
            //当栈空或当前高度大于栈顶高度时，将元素入栈。
            if(stack.empty() || height >= heights[stack.peek()]){
                stack.push(i);
            }
            //当当前高度小于栈顶高度时，可以计算并更新最大矩形面积。
            //由于栈顶元素t高度比p小，i也比p高度小。因此必能构成从t至i以i-t-1为底，height[p]为高的矩形。
            //若计算后当前高度依然小于栈顶高度，则继续计算。
            else{
                int curArea = heights[stack.pop()] * (stack.empty() ? i : i - stack.peek() - 1);
                maxArea = curArea > maxArea ? curArea : maxArea;
                --i;
            }
        }
        return maxArea;
    }

    //单调递增栈写法2
    public int largestRectangleArea11(int[] heights){
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxArea = 0;
        for(int i = 0; i < heights.length; i++){
            //弹出栈中所有不大于当前高度heights[i]的元素，计算矩形面积
            while(stack.peek() != -1 && heights[stack.peek()] >= heights[i]){
                maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        //处理剩余栈中元素
        while(stack.peek() != -1){
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return maxArea;
    }

    //方法三：动态规划（最优）
    public int largestRectangleArea2(int[] heights){
        if(heights.length == 0){
            return 0;
        }
        //lessFromLeft[i]代表从i向左第一个高度小于i的元素的下标
        //lessFromRight[i]代表从i向右第一个高度小于i的元素的下标
        int[] lessFromLeft = new int[heights.length];
        lessFromLeft[0] = -1;
        int[] lessFromRight = new int[heights.length];
        lessFromRight[heights.length - 1] = heights.length;

        for(int i = 1; i < heights.length; ++i){
            int p = i - 1;
            while(p >= 0 && heights[p] >= heights[i]){
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for(int i = heights.length - 2; i >= 0; --i){
            int p = i + 1;
            while(p < heights.length && heights[p] >= heights[i]){
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for(int i = 0; i < heights.length; ++i){
            //以lessFromRight[i] - lessFromLeft[i] - 1为底，heights[i]为高构成的矩形是以i为中心的最大矩形
            int curArea = heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1);
            maxArea = curArea > maxArea ? curArea : maxArea;
        }
        return maxArea;
    }
}
