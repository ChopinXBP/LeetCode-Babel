package Problems;

/**
 *
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 *
 */

public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length == 0){
            return new int[0];
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[] result = new int[row * col];

        //对角线端点沿着外围首行和最右列遍历，一共需要遍历times=row+col-1轮（times条对角线）
        //direction代表对角线遍历方向，左下为true，反之为false。
        boolean direction = false;
        int times = 0;
        int idx = 0;
        while(times < row + col - 1){
            //位置(x,y)代表对角线遍历的起始端点，num为遍历元素个数（行列取短）
            int x = times < col ? 0 : times - col + 1;
            int y = times < col ? times : col - 1;
            int num = times < col ? Math.min(y + 1, row) : Math.min(row - x, col);
            //遍历顺序总是从右上到左下，但当遍历方向为左下时，数组顺序存储；反之逆序存储。
            int loc = direction ? idx : idx + num - 1;
            while(num-- != 0){
                result[loc] = matrix[x++][y--];
                loc = direction ? loc + 1 : loc - 1;
                idx++;
            }
            direction = !direction;
            times++;
        }
        return result;
    }
}
