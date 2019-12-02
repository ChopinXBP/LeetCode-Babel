package Problems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a 2D grid of size n * m and an integer k. You need to shift the grid k times.
 * In one shift operation:
 * Element at grid[i][j] becomes at grid[i][j + 1].
 * Element at grid[i][m - 1] becomes at grid[i + 1][0].
 * Element at grid[n - 1][m - 1] becomes at grid[0][0].
 * Return the 2D grid after applying shift operation k times.
 * 给你一个 n 行 m 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
 * 每次「迁移」操作将会引发下述活动：
 * 位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
 * 位于 grid[i][m - 1] 的元素将会移动到 grid[i + 1][0]。
 * 位于 grid[n - 1][m - 1] 的元素将会移动到 grid[0][0]。
 * 请你返回 k 次迁移操作后最终得到的 二维网格。
 *
 */

public class Shift2DGrid {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> result =  new ArrayList<>();
        if(grid == null || grid.length == 0){
            return result;
        }
        int row = grid.length;
        int col = grid[0].length;

        while(k-- > 0){
            int[][] temp = grid.clone();
            for(int i = 0; i < row; i++){
                temp[i] = grid[i].clone();
            }
            for(int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    if(i == row - 1 && j == col - 1){
                        grid[0][0] = temp[i][j];
                    }else if(j == col - 1){
                        grid[i + 1][0] = temp[i][j];
                    }else{
                        grid[i][j + 1] = temp[i][j];
                    }
                }
            }
        }

        for(int i = 0; i < row; i++){
            ArrayList<Integer> newlist = new ArrayList<>();
            for(int j = 0; j < col; j++){
                newlist.add(grid[i][j]);
            }
            result.add(newlist);
        }

        return result;
    }
}
