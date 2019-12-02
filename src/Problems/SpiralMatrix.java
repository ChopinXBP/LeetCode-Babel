package Problems;
/**
 * 
 * @author ChopinXBP
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 
 */

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return result;
		
		int count = 0;
		int ridx = 0;
		int cidx = 0;
		int row = matrix.length;
		int col = matrix[0].length;
		int num = row * col;
		while(count < num) {
			//上
			for(int i = 0; i < col; i++) {
				result.add(matrix[ridx][cidx++]);
				count++;
			}
			ridx++; cidx--;
			
			//判断是否打印完全
			if(count == num)
				break;
			
			//右
			for(int i = 0; i < row - 1; i++) {
				result.add(matrix[ridx++][cidx]);
				count++;
			}
			ridx--; cidx--;
			row--; col--;
			
			//下			
			for(int i = 0; i < col; i++) {
				result.add(matrix[ridx][cidx--]);
				count++;
			}
			ridx--; cidx++;
			
			//判断是否打印完全
			if(count == num)
				break;
			
			//左
			for(int i = 0; i < row - 1; i++) {
				result.add(matrix[ridx--][cidx]);
				count++;
			}
			ridx++; cidx++;
			row--; col--;
		}
		
		return result;
	}
}
