/**
 * 
 * @author ChopinXBP
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 */

public class SpiralMatrix2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] result = generateMatrix(3);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

    public static int[][] generateMatrix(int n) {
		int[][] result = new int[n][n];
		if(n == 0)
			return result;
		
		int count = 1;
		int ridx = 0;
		int cidx = 0;
		int row = n;
		int col = n;
		while(count <= n * n) {
			//上
			for(int i = 0; i < col; i++) {
				result[ridx][cidx++] = count++;
			}
			ridx++; cidx--;
			
			//右
			for(int i = 0; i < row - 1; i++) {
				result[ridx++][cidx] = count++;
			}
			ridx--; cidx--;
			row--; col--;
			
			//下			
			for(int i = 0; i < col; i++) {
				result[ridx][cidx--] = count++;
			}
			ridx--; cidx++;
			
			//左
			for(int i = 0; i < row - 1; i++) {
				result[ridx--][cidx] = count++;
			}
			ridx++; cidx++;
			row--; col--;
		}
		
		return result;        
    }
}
