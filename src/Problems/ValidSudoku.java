package Problems;

import java.util.Arrays;

/**
 *
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * A partially filled sudoku which is valid.
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 上图是一个部分填充的有效的数独。
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 */

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board){
        boolean[][] rowVisited = new boolean[9][9];
        boolean[][] colVisited = new boolean[9][9];
        boolean[][] blockVisited = new boolean[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                char c = board[i][j];
                if(c == '.'){
                    continue;
                }
                int num = c - '0' - 1;
                if(rowVisited[i][num] || colVisited[j][num] || blockVisited[i / 3 * 3 + j / 3][num]){
                    return false;
                }
                rowVisited[i][num] = true;
                colVisited[j][num] = true;
                blockVisited[i / 3 * 3 + j / 3][num] = true;
            }
        }
        return true;
    }
}
