package Problems;

/**
 *
 * Given a m x n matrix mat and an integer threshold.
 * Return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.
 * 给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
 * 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
 *
 */

public class MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold {
    public int maxSideLength(int[][] mat, int threshold) {
        int result = 0;
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                if(j != 0){
                    mat[i][j] += mat[i][j - 1];
                }
                int len = 0;
                int maxlen = Math.min(i, j) + 1;
                while(len < maxlen){
                    int area = 0;
                    for(int k = 0; k < len + 1; k++) {
                        int prefix = j - len - 1 < 0 ? 0 : mat[i - k][j - len - 1];
                        area += mat[i - k][j] - prefix;
                    }
                    if(area > threshold){
                        break;
                    }
                    len++;
                }
                result = len > result ? len : result;
            }
        }
        return result;
    }
}
