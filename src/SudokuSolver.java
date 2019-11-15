/**
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * A sudoku solution must satisfy all of the following rules:
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * 一个数独的解法需遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 */

public class SudokuSolver {
    private final int n = 9;
    private boolean[][] rows = new boolean[n][n + 1];
    private boolean[][] cols = new boolean[n][n + 1];
    private boolean[][] blocks = new boolean[n][n + 1];

    public void solveSudoku(char[][] board) {
        //初始化约束数组
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] != '.'){
                    int num = board[i][j] - '0';
                    rows[i][num] = true;
                    cols[j][num] = true;
                    blocks[(i / 3) * 3 + (j / 3)][num] = true;
                }
            }
        }

        //回溯法填数字
        backtrack(board, 0, 0);
        return;
    }

    private boolean result = false;
    public void backtrack(char[][] board, int x, int y){
        if(y == n){
            if(x != n - 1){
                backtrack(board, x + 1, 0);
            }else{
                result = true;
            }
            return;
        }
        while(y < n && board[x][y] != '.'){
            y++;
        }
        if(y == n){
            backtrack(board, x, y);
            return;
        }
        for(int num = 1; num < 10; num++){
            if(canPut(num, x, y)){
                rows[x][num] = true;
                cols[y][num] = true;
                blocks[(x / 3) * 3 + (y / 3)][num] = true;
                board[x][y] = (char)(num + '0');
                backtrack(board, x, y + 1);
                if(result){
                    return;
                }
                rows[x][num] = false;
                cols[y][num] = false;
                blocks[(x / 3) * 3 + (y / 3)][num] = false;
                board[x][y] = '.';
            }
        }
    }

    private boolean canPut(int num, int x, int y){
        return !(rows[x][num] || cols[y][num] || blocks[(x / 3) * 3 + (y / 3)][num]);
    }
}
