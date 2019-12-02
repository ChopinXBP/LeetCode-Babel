package Problems;

import java.util.HashSet;

/**
 *
 * You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server.
 * Two servers are said to communicate if they are on the same row or on the same column.
 * Return the number of servers that communicate with any other server.
 * 这里有一幅服务器分布图，服务器的位置标识在 m * n 的整数矩阵网格 grid 中，1 表示单元格上有服务器，0 表示没有。
 * 如果两台服务器位于同一行或者同一列，我们就认为它们之间可以进行通信。
 * 请你统计并返回能够与至少一台其他服务器进行通信的服务器的数量。
 *
 */

public class CountServersThatCommunicate {
    public int countServers(int[][] grid) {
        if(grid.length == 0){
            return 0;
        }
        int[] row = new int[grid.length];
        int[] col = new int[grid[0].length];
        HashSet<int[]> set = new HashSet<>();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    row[i]++;
                    col[j]++;
                    set.add(new int[]{i, j});
                }
            }
        }
        int result = 0;
        for(int[] p : set){
            if(row[p[0]] > 1 || col[p[1]] > 1){
                result++;
            }
        }
        return result;
    }
}
