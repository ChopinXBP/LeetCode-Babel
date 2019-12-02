package Problems;

/**
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 */

public class SearchA2DMatrixII {

    //从左下角开始遍历,若数字比target小，则行+1；若数字比target大，则列-1
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return false;
        }

        int x = matrix.length - 1;
        int y = 0;
        while(x >= 0 && y < matrix[0].length){
            if(matrix[x][y] > target){
                x--;
            }
            else if(matrix[x][y] < target){
                y++;
            }
            else{
                return true;
            }
        }
        return false;
    }

    //对每一行二分查找
    public boolean searchMatrix2(int[][] matrix, int target) {
        for(int i = 0; i < matrix.length; i++){
            int begin = 0;
            int end = matrix[i].length;
            while(begin < end){
                int mid = (begin + end) >> 1;
                if(matrix[i][mid] < target){
                    begin = mid + 1;
                }
                else if(matrix[i][mid] > target){
                    end = mid;
                }
                else {
                    return true;
                }
            }
        }
        return false;
    }
}
