package Problems;
/**
 * 
 * @author ChopinXBP
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * ����һ������ m x n ��Ԫ�صľ���m ��, n �У����밴��˳ʱ������˳�򣬷��ؾ����е�����Ԫ�ء�
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
			//��
			for(int i = 0; i < col; i++) {
				result.add(matrix[ridx][cidx++]);
				count++;
			}
			ridx++; cidx--;
			
			//�ж��Ƿ��ӡ��ȫ
			if(count == num)
				break;
			
			//��
			for(int i = 0; i < row - 1; i++) {
				result.add(matrix[ridx++][cidx]);
				count++;
			}
			ridx--; cidx--;
			row--; col--;
			
			//��			
			for(int i = 0; i < col; i++) {
				result.add(matrix[ridx][cidx--]);
				count++;
			}
			ridx--; cidx++;
			
			//�ж��Ƿ��ӡ��ȫ
			if(count == num)
				break;
			
			//��
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
