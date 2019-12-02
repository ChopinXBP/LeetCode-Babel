package Problems;

import java.util.List;

/**
 *
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 */

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[] dp = new int[size];
        for(int i = 0; i < size; i++){
            dp[i] = triangle.get(size - 1).get(i);
        }
        for(int i = size - 1; i >= 0; i--) {
            for(int j = 0; j < i; j++) {
                dp[j] = triangle.get(i - 1).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }
}

