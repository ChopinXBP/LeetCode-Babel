package Problems;

/**
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 *
 */

public class UniquePaths {

    public static void main(String[] args){
        System.out.println(uniquePaths(10, 10));
    }

    public static int uniquePaths(int m, int n) {
        if(m == 1 || n == 1)
            return 1;

        //令m为较大的数
        if(m < n){
            int temp = m;
            m = n;
            n = temp;
        }
        long numerator = (m - 1) + (n - 1);
        long denominator = n - 1;
        for(int i = n - 2; i >= 1; i--){
            denominator *= i;
            numerator *= ((m - 1) + (n - 1) - i);
        }

        return (int)(numerator / denominator);
    }
}
