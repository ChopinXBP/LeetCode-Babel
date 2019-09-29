import java.util.Arrays;
import java.util.Stack;

/**
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 */

public class MaximalRectangle {
    //动态规划：宽度拓展
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        //dp[i][j]代表以位置ij为右下角的矩形的宽
        int[][] dp = new int[matrix.length][matrix[0].length];

        int maxArea = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == '1'){
                    //第一列矩形宽dp值初始化为1，其余列的状态转移方程为dp[i][j] = dp[i][j - 1] + 1;
                    dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;
                    //根据ij位置为右下角的矩形的宽，从下往上遍历调整宽度，可以计算该矩形的最大面积
                    int width = dp[i][j];
                    for(int k = i; k >= 0; k--){
                        width = Math.min(width, dp[k][j]);
                        if(width == 0){
                            break;
                        }
                        maxArea = Math.max(maxArea, width * (i - k + 1));
                    }
                }
            }
        }

        return maxArea;
    }

    //单调递增栈
    public int maximalRectangle2(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        //dp[i][j]代表从上往下截至i行位置j的最大高度，可压缩至一维
        int[] dp = new int[matrix[0].length];

        int maxArea = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
            }
            //对于每一行dp值，可以看成LeetCode84中的求柱状图中矩形最大面积的问题，对求解方法进行复用
            maxArea = Math.max(maxArea, largestRectangleArea(dp));
        }

        return maxArea;
    }

    //https://blog.csdn.net/qq_20304723/article/details/91427990
    //LeetCode84：单调递增栈法：利用辅助栈动态存储当前最大高度柱子对应下标
    public int largestRectangleArea(int[] heights){
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

    //动态规划:按点拓展（最优）
    //https://leetcode-cn.com/problems/maximal-rectangle/solution/zui-da-ju-xing-by-leetcode/
    public int maximalRectangle3(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        //left（right）代表每一个点向左（右）延伸的最大长度，height代表每一个点向上延伸的最大高度，
        int[] height = new int[matrix[0].length];
        int[] left = new int[matrix[0].length];
        int[] right = new int[matrix[0].length];
        Arrays.fill(right, matrix[0].length);
        //遍历每一个ij，从该点向上获取最大高度，再以此高度向左右延伸获得计算矩形面积
        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            int leftBegin = 0;
            int cur_right = matrix[0].length;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            //当前字符为'0'时，left值为0，leftBegin更新为下一点；当前字符为'1'时，left值为上一行left值和leftBegin的较右值（矩阵边界）。
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], leftBegin);
                } else {
                    left[j] = 0;
                    leftBegin = j + 1;
                }
            }
            //right计算同理
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], cur_right);
                } else {
                    right[j] = matrix[0].length;
                    cur_right = j;
                }
            }
            //每一点对应的最大矩阵面积即为right[j] - left[j]) * height[j]
            for (int j = 0; j < matrix[0].length; j++) {
                maxArea = Math.max(maxArea, (right[j] - left[j]) * height[j]);
            }
        }
        return maxArea;
    }
}
