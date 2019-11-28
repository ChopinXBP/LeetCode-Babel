/**
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 *
 */

public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null){
            return 0;
        }
        int maxArea = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1){
                    curArea = 0;
                    Solution(grid, i, j);
                    maxArea = curArea > maxArea ? curArea : maxArea;
                }
            }
        }
        return maxArea;
    }

    private int curArea;
    private void Solution(int[][] grid, int x, int y){
        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1){
            return;
        }
        curArea++;
        grid[x][y] = 2;
        Solution(grid, x + 1, y);
        Solution(grid, x - 1, y);
        Solution(grid, x, y + 1);
        Solution(grid, x, y - 1);
    }
}
