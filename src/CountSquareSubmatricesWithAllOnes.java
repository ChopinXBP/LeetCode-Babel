/**
 *
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 *
 */

public class CountSquareSubmatricesWithAllOnes {
    public int countSquares(int[][] matrix) {
        int result = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] != 1){
                    continue;
                }
                result++;
                if(j == 0){
                    matrix[i][j] = 1;
                    continue;
                }
                matrix[i][j] = matrix[i][j - 1] + 1;
                int maxlen = matrix[i][j];
                int minlen = maxlen;
                int row = i - 1;
                int curlen = 2;
                while(row >= 0 && curlen <= maxlen){
                    minlen = Math.min(matrix[row][j], minlen);
                    if(minlen < curlen){
                        break;
                    }
                    result++;
                    row--;
                    curlen++;
                }
            }
        }
        return result;
    }
}
