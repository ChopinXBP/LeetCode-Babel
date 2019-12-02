package Problems;

import java.util.Arrays;

/**
 *
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 */

public class SetMatrixZeroes {

    //行扫描并记录出现0的列。空间复杂度o(n)，时间复杂度o(n^2)
    public void setZeroes(int[][] matrix) {
        int col = matrix[0].length;
        boolean[] zeroCol = new boolean[col];
        for(int i = 0; i < matrix.length; i++){
            boolean rowflag = false;
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    rowflag = true;
                    zeroCol[j] = true;
                }
                if(zeroCol[j]){
                    matrix[i][j] = 0;
                    continue;
                }
            }
            if(rowflag){
                Arrays.fill(matrix[i], 0);
            }
        }
        for(int j = 0; j < col; j++){
            if(zeroCol[j]){
                for(int i = 0; i < matrix.length; i++){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    //利用首行首列作为标识向量：空间复杂度o(1)
    public void setZeroes1(int[][] matrix){
        //matrix[0][0]代表首行是否存在0，isCol代表首列是否存在0
        boolean isCol = false;
        int rowLen = matrix.length;
        int colLen = matrix[0].length;

        for(int i = 0; i < rowLen; i++){
            //逐行检查首列
            if(matrix[i][0] == 0){
                isCol = true;
            }
            //从第一列开始检查，并用第一行和第一列作为记录
            for(int j = 1; j < colLen; j++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        //根据第一行和第一列的标志重置矩阵
        for(int i = 1; i < rowLen; i ++){
            for(int j = 1; j < colLen; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        //重置首行和首列
        if(matrix[0][0] == 0){
            Arrays.fill(matrix[0], 0);
        }
        if(isCol){
            for(int i = 0; i < rowLen; i++){
                matrix[i][0] = 0;
            }
        }
    }
}
