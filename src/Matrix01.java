import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 *
 */

public class Matrix01 {
    //BFS超时
    public int[][] updateMatrix(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] != 0){
                    Solution(matrix, i, j);
                }
            }
        }
        return matrix;
    }

    class Point{
        int x;
        int y;
        Point(int px, int py){
            x = px;
            y = py;
        }
    }

    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void Solution(int[][] matrix, int x, int y){
        boolean[][] isVisited = new boolean[matrix.length][matrix[0].length];
        LinkedList<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        isVisited[x][y] = true;
        int dist = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int k = 0; k < size; k++){
                Point p = queue.pollFirst();
                if(matrix[p.x][p.y] == 0){
                    matrix[x][y] = dist;
                    return;
                }
                for(int[] dirt : directions){
                    int nx = p.x + dirt[0];
                    int ny = p.y + dirt[1];
                    if(nx >= 0 && ny >= 0 && nx < matrix.length && ny < matrix[0].length && !isVisited[nx][ny]){
                        queue.add(new Point(nx, ny));
                        isVisited[nx][ny] = true;
                    }
                }
            }
            dist++;
        }
    }

    //动态规划
    //对于四个方向的矩阵动态规划问题，可以进行右下和左上两个方向的两次动态遍历
    public int[][] updateMatrix2(int[][] matrix) {

        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int[] arr : dp){
            Arrays.fill(arr, Integer.MAX_VALUE - 1);
        }

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = i > 0 ? Math.min(dp[i][j], dp[i - 1][j] + 1) : dp[i][j];
                dp[i][j] = j > 0 ? Math.min(dp[i][j], dp[i][j - 1] + 1) : dp[i][j];
            }
        }

        for(int i = matrix.length - 1; i >= 0; i--){
            for(int j = matrix[0].length - 1; j >= 0; j--){
                if(matrix[i][j] == 0){
                    continue;
                }
                dp[i][j] = i < matrix.length - 1 ? Math.min(dp[i][j], dp[i + 1][j] + 1) : dp[i][j];
                dp[i][j] = j < matrix[0].length - 1 ? Math.min(dp[i][j], dp[i][j + 1] + 1) : dp[i][j];
            }
        }

        return dp;
    }
}
