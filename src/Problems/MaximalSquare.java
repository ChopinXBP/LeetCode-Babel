package Problems;

import java.util.Arrays;

/**
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 */

public class MaximalSquare {
    //动态规划：参考LeetCode85（最大矩形）
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        int col = matrix[0].length;
        int[] height = new int[col];
        int[] left = new int[col];
        int[] right = new int[col];
        Arrays.fill(right, col - 1);

        int maxSquareEdge = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < col; j++){
                height[j] = matrix[i][j] == '1' ? height[j] + 1 : 0;
            }
            int leftBegin = 0;
            for(int j = leftBegin; j < col; j++){
                if(matrix[i][j] == '1'){
                    left[j] = Math.max(left[j], leftBegin);
                }else{
                    left[j] = 0;
                    leftBegin = j + 1;
                }
            }
            int rightBegin = col - 1;
            for(int j = rightBegin; j >= 0; j--){
                if(matrix[i][j] == '1'){
                    right[j] = Math.min(right[j], rightBegin);
                }else{
                    right[j] = col - 1;
                    rightBegin = j - 1;
                }
            }
            for(int j = 0; j < col; j++){
                maxSquareEdge = Math.max(maxSquareEdge, Math.min(height[j], right[j] - left[j] + 1));
            }
        }

        return maxSquareEdge * maxSquareEdge;
    }

    //动态规划：边长拓展
    public int maximalSquare2(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        //dp[i][j]代表以(i-1,j-1)位置为右下角的最大正方形边长（可压缩至一维）
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];

        int maxSquareEdge = 0;
        for(int i = 1; i <= matrix.length; i++){
            for(int j = 1; j <= matrix[0].length; j++){
                //当ij位置为'1'时，ij点的最大正方形边长取决于左上侧三点的最大正方形边长，状态转移方程为dp[i][j] = Min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1;
                if(matrix[i - 1][j - 1] == '1'){
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    maxSquareEdge = Math.max(maxSquareEdge, dp[i][j]);
                }
            }
        }

        return maxSquareEdge * maxSquareEdge;
    }
}
