package Problems;

/**
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 */

public class MinimumPathSum {

    //回溯法：超时
    private int result;
    public int minPathSum(int[][] grid) {
        result = Integer.MAX_VALUE;
        Solution(grid, 0, 0, 0, grid.length, grid[0].length);
        return result;
    }

    public void Solution(int[][] grid, int x, int y, int sum, int row, int col){
        if(x == row - 1 && y == col - 1){
            sum += grid[row - 1][col - 1];
            result = sum < result ? sum : result;
            return;
        }
        if(x < 0 || x >= row || y < 0 || y >= col || grid[x][y] + sum > result){
            return;
        }
        sum += grid[x][y];
        Solution(grid, x + 1, y, sum, row, col);
        Solution(grid, x, y + 1, sum, row, col);
    }

    //二维DP
    public int minPathSum2(int[][] grid) {
        //dp[i][j]代表起点至ij点的路径和
        int[][] dp = new int[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                //从起点开始遍历
                if(i == 0 && j == 0){
                    dp[i][j] = grid[i][j];
                }
                //第一行路径和只取决于左一个位置路径和
                else if(i == 0 && j != 0){
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }
                //第一列路径和只取决于上一个位置路径和
                else if(i != 0 && j == 0){
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                }
                //其他元素取决于左上两个位置的路径和
                else{
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    //一维DP
    public int minPathSum3(int[][] grid) {
        int[] dp = new int[grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                //从起点开始遍历
                if(i == 0 && j == 0){
                    dp[j] = grid[i][j];
                }
                //第一行路径和只取决于左一个位置路径和
                else if(i == 0 && j != 0){
                    dp[j] = dp[j - 1] + grid[i][j];
                }
                //第一列路径和只取决于上一个位置路径和
                else if(i != 0 && j == 0){
                    dp[j] = dp[j] + grid[i][j];
                }
                //其他元素取决于左上两个位置的路径和
                else{
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                }
            }
        }
        return dp[grid[0].length - 1];
    }

    //原地DP
    public int minPathSum4(int[][] grid) {
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                //从起点开始遍历
                if(i == 0 && j == 0){
                    continue;
                }
                //第一行路径和只取决于左一个位置路径和
                else if(i == 0 && j != 0){
                    grid[i][j] += grid[i][j - 1];
                }
                //第一列路径和只取决于上一个位置路径和
                else if(i != 0 && j == 0){
                    grid[i][j] += grid[i - 1][j];
                }
                //其他元素取决于左上两个位置的路径和
                else{
                    grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}
