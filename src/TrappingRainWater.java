import java.util.Stack;

/**
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 */

public class TrappingRainWater {

    //首尾逼近：从两端朝内逼近，每次更新较矮的端点minheight，在每次循环逼近过程中将小于minheight的差值即为可以接入的雨水
    public int trap(int[] height) {
        int begin = 0;
        int end = height.length - 1;
        int minheight = 0;
        int result = 0;
        while(begin < end){
            //begin和end指针在移动到比minheight高的位置前，依次将元素值与minheight的差值加入雨水累计值
            while(begin < end && height[begin] <= minheight){
                result += minheight - height[begin];
                begin++;
            }
            while(begin < end && height[end] <= minheight){
                result += minheight - height[end];
                end--;
            }
            //每次minheight更新为较矮的边
            minheight = Math.min(height[begin], height[end]);
        }
        return result;
    }

    //同一思路另一算法：每次逼近较矮的一边
    public int trap1(int[] height) {
        int begin = 0;
        int end = height.length - 1;
        int leftMaxHeight = 0;
        int rightMaxHeight = 0;
        int result = 0;
        while(begin < end){
            if(height[begin] < height[end]){
                if(height[begin] >= leftMaxHeight){
                    leftMaxHeight = height[begin];
                }else{
                    result += (leftMaxHeight - height[begin]);
                }
                ++begin;
            }
            else{
                if(height[end] >= rightMaxHeight){
                    rightMaxHeight = height[end];
                }else{
                    result += (rightMaxHeight - height[end]);
                }
                --end;
            }
        }
        return result;
    }

    //暴力法
    //对于数组中的每一个元素，其容纳雨水最大水位等于两边杆的最大高度的最小值减去它自身的高度。

    //动态规划存高度
    //为每一个i存储其左端最大高度和右端最大高度，存储在leftMaxHeight和rightMaxHeight中，再用暴力的思路解
    public int trap2(int[] height) {
        if(height.length == 0){
            return 0;
        }
        //左端最大高度数组
        int[] leftMaxHeight = new int[height.length];
        leftMaxHeight[0] = height[0];
        for(int i = 1; i < height.length; ++i){
            leftMaxHeight[i] = Math.max(leftMaxHeight[i - 1], height[i]);
        }
        //右端最大高度数组
        int[] rightMaxHeight = new int[height.length];
        rightMaxHeight[height.length - 1] = height[height.length - 1];
        for(int i = height.length - 2; i >= 0; --i){
            rightMaxHeight[i] = Math.max(rightMaxHeight[i + 1], height[i]);
        }
        //对于数组中的每一个元素，其容纳雨水最大水位等于两边杆的最大高度的最小值减去它自身的高度。
        int result = 0;
        for(int i = 1; i < height.length - 1; i++){
            result += Math.min(leftMaxHeight[i], rightMaxHeight[i]) - height[i];
        }
        return result;
    }

    //利用栈
    //建立一个栈，当idx对应元素高度不大于栈顶元素高度的时候，元素入栈。当栈不空且idx对应元素高度大于栈顶元素高度时，将所有小于idx高度的元素依次出栈，并进行计算。
    // 例如序列[3, 2, 1, 1, 2, 3]，当idx<4时，元素入栈；当idx=4时，两个元素“1”出栈并计算；当idx=5时，元素“2”出栈并计算。
    public int trap3(int[] height){
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int idx = 0;
        while(idx < height.length){
            while(!stack.empty() && height[idx] > height[stack.peek()]){
                int top = stack.pop();
                if(stack.empty()){
                    break;
                }
                int distance = idx - stack.peek() - 1;
                int boundHeight = Math.min(height[idx], height[stack.peek()]) - height[top];
                result += distance * boundHeight;
            }
            stack.push(idx++);
        }
        return result;
    }

}
