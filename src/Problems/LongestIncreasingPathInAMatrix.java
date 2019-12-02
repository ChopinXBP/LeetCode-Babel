package Problems;

import java.util.ArrayList;

/**
 *
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 *
 */

public class LongestIncreasingPathInAMatrix {
    //记忆化递归
    private final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0){
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] cache = new int[row][col];
        int result = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                result = Math.max(result, dfs(matrix, row, col, i, j, cache));
            }
        }
        return result;
    }

    //将计算过的最大长度值存储在cache中，避免重复计算
    private int dfs(int[][] matrix, int row, int col, int i, int j, int[][] cache){
        if(cache[i][j] != 0){
            return cache[i][j];
        }
        for(int[] dirct : directions){
            int x = i + dirct[0];
            int y = j + dirct[1];
            if(x >= 0 && x < row && y >= 0 && y < col && matrix[x][y] > matrix[i][j]) {
                cache[i][j] = Math.max(cache[i][j], dfs(matrix, row, col, x, y, cache));
            }
        }
        return ++cache[i][j];
    }


    //动态规划+拓扑排序
    public int longestIncreasingPath2(int[][] matrix) {
        if(matrix.length == 0){
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        //在matrix外围加上一层初值为0（或最小值）的数组，方便运算
        int[][] grid = new int[row + 2][col + 2];
        for(int i = 0; i < row; i++){
            System.arraycopy(matrix[i], 0, grid[i + 1], 1, col);
        }

        //计算矩阵中每一点的出度并存入outdegree数组，出度为周围大于该元素的点数，出度为0代表局部最大
        int[][] outdegree = new int[row + 2][col + 2];
        for(int i = 1; i <= row; i++){
            for(int j = 1; j <= col; j++){
                for(int[] dirct : directions){
                    if(grid[i][j] < grid[i + dirct[0]][j + dirct[1]]){
                        outdegree[i][j]++;
                    }
                }
            }
        }

        //将所有出度为0（局部最大）的元素标记为叶子元素存储到leaves中
        ArrayList<int[]> leaves = new ArrayList<>();
        for(int i = 1; i < row + 1; i++){
            for(int j = 1; j < col + 1; j++){
                if(outdegree[i][j] == 0){
                    leaves.add(new int[]{i, j});
                }
            }
        }

        //逆向思维，每次迭代所有叶子元素，最大长度+1。
        //将每一叶子结点相邻的比叶子结点小的元素的出度-1，若其出度为0，则作为新的叶子结点继续迭代，直至没有叶子结点。
        int maxlength = 0;
        while(!leaves.isEmpty()){
            maxlength++;
            ArrayList<int[]> newLeaves = new ArrayList<>();
            for(int[] node : leaves){
                for(int[] dirct : directions){
                    int x = node[0] + dirct[0];
                    int y = node[1] + dirct[1];
                    if(grid[node[0]][node[1]] > grid[x][y]){
                        if(--outdegree[x][y] == 0){
                            newLeaves.add(new int[]{x, y});
                        }
                    }
                }
            }
            leaves = newLeaves;
        }

        return maxlength;
    }
}
