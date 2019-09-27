/**
 *
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 */

public class SurroundedRegions {
    //单独标记所有边界的O，将所有非边界O改为X
    public void solve(char[][] board) {
        if(board.length == 0){
            return;
        }
        int row = board.length;
        int col = board[0].length;

        for(int i = 0; i < row; i++){
            DFS(i, 0, row, col, board);
            DFS(i, col - 1, row, col, board);
        }
        for(int j = 1; j < col-1; j++){
            DFS(0, j, row, col, board);
            DFS(row - 1, j, row, col, board);
        }
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }else if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }

    //DFS将所有边界的‘O’标记为‘#’
    public void DFS(int x, int y, int row, int col, char[][] board){
        if(x < row && x >= 0 && y < col && y >= 0 && board[x][y] == 'O'){
            board[x][y] = '#';
            DFS(x + 1, y, row, col, board);
            DFS(x - 1, y, row, col, board);
            DFS(x, y + 1, row, col, board);
            DFS(x, y - 1, row, col, board);
        }
    }
}
