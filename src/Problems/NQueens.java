package Problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen
 * and an empty space respectively.
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 */

public class NQueens {
    ////////////////////////////////////////////////N皇后I（所有解）：回溯法////////////////////////////////////////////////

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(char[] row : board){
            Arrays.fill(row, '.');
        }
        List<List<String>> result = new ArrayList<>();
        dfs(board, 0, result);
        return result;
    }

    private void dfs(char[][] board, int colIndex, List<List<String>> result){
        if(colIndex == board.length){
            result.add(construct(board));
            return;
        }
        //在当前位置能够放置皇后的情况下，向下一列递归。
        for(int rowIndex = 0; rowIndex < board.length; rowIndex++){
            if(validate(board, rowIndex, colIndex)){
                board[rowIndex][colIndex] = 'Q';
                dfs(board, colIndex + 1, result);
                board[rowIndex][colIndex] = '.';
            }
        }
    }

    //判断当前位置(rowIndex,colIndex)是否能够放置皇后
    private boolean validate(char[][] board, int rowIndex, int colIndex){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < colIndex; j++){
                if(board[i][j] == 'Q' && (colIndex - j == Math.abs(rowIndex - i) || rowIndex == i)){
                    return false;
                }
            }
        }
        return true;
    }

    private List<String> construct(char[][] board){
        List<String> result = new LinkedList<>();
        for(char[] rows : board){
            result.add(new String(rows));
        }
        return result;
    }


    ////////////////////////////////////////////////N皇后I（所有解）：记忆化回溯////////////////////////////////////////////////

    //记录某行是否有皇后摆放
    private int rows[];
    //记录某条正对角线（左上右下）是否已有皇后摆放（某条对角线对应的摆放位置为 x - y + n - 1）
    private int hills[];
    //记录某条斜对角线（左下右上）是否已有皇后摆放（某条对角线对应的摆放位置为 x + y）
    private int dales[];
    //记录当前摆放的皇后位置，下标代表行，值代表列
    int queens[];
    private int n;
    List<List<String>> output = new ArrayList();

    public List<List<String>> solveNQueens2(int n) {
        this.n = n;
        rows = new int[n];
        hills = new int[4 * n - 1];
        dales = new int[2 * n - 1];
        queens = new int[n];
        backtrack(0);
        return output;
    }

    public boolean isNotUnderAttack(int row, int col) {
        int res = rows[col] + hills[row - col + 2 * n] + dales[row + col];
        return (res == 0) ? true : false;
    }

    public void placeQueen(int row, int col) {
        queens[row] = col;
        rows[col] = 1;
        hills[row - col + 2 * n] = 1;
        dales[row + col] = 1;
    }

    public void removeQueen(int row, int col) {
        queens[row] = 0;
        rows[col] = 0;
        hills[row - col + 2 * n] = 0;
        dales[row + col] = 0;
    }

    public void addSolution() {
        List<String> solution = new ArrayList<String>();
        for (int i = 0; i < n; ++i) {
            int col = queens[i];
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < col; ++j){
                sb.append(".");
            }
            sb.append("Q");
            for(int j = 0; j < n - col - 1; ++j){
                sb.append(".");
            }
            solution.add(sb.toString());
        }
        output.add(solution);
    }

    public void backtrack(int row) {
        for (int col = 0; col < n; col++) {
            if (isNotUnderAttack(row, col)) {
                placeQueen(row, col);
                if (row + 1 == n){
                    addSolution();
                } else{
                    backtrack(row + 1);
                }
                removeQueen(row, col);
            }
        }
    }


    ////////////////////////////////////////////////N皇后II（解的个数）：回溯法////////////////////////////////////////////////
    private int result;
    public int totalNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        result = 0;
        dfs(board, 0);
        return result;
    }

    private void dfs(boolean[][] board, int colIndex){
        if(colIndex == board.length){
            result++;
            return;
        }
        //在当前位置能够放置皇后的情况下，向下一列递归。
        for(int rowIndex = 0; rowIndex < board.length; rowIndex++){
            if(validate(board, rowIndex, colIndex)){
                board[rowIndex][colIndex] = true;
                dfs(board, colIndex + 1);
                board[rowIndex][colIndex] = false;
            }
        }
    }

    private boolean validate(boolean[][] board, int rowIndex, int colIndex){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < colIndex; j++){
                if(board[i][j] && (colIndex - j == Math.abs(rowIndex - i) || rowIndex == i)){
                    return false;
                }
            }
        }
        return true;
    }

    ////////////////////////////////////////////////N皇后II（解的个数）：记忆化回溯+BitMap////////////////////////////////////////////////

    public int totalNQueens2(int n) {
        return backtrack(0, 0, 0, 0, 0, n);
    }

    public int backtrack(int row, int hills, int next_row, int dales, int count, int n) {
        /**
         row: 当前放置皇后的行号
         hills: 主对角线占据情况 [1 = 被占据，0 = 未被占据]
         next_row: 下一行被占据的情况 [1 = 被占据，0 = 未被占据]
         dales: 次对角线占据情况 [1 = 被占据，0 = 未被占据]
         count: 所有可行解的个数
         */
        // 棋盘所有的列都可放置，
        // 即，按位表示为 n 个 '1'
        // bin(cols) = 0b1111 (n = 4), bin(cols) = 0b111 (n = 3)
        // [1 = 可放置]
        int columns = (1 << n) - 1;


        if (row == n){
            count++;
        } else {
            // 当前行可用的列
            // ! 表示 0 和 1 的含义对于变量 hills, next_row and dales的含义是相反的
            // [1 = 未被占据，0 = 被占据]
            int free_columns = columns & ~(hills | next_row | dales);

            // 找到可以放置下一个皇后的列
            while (free_columns != 0) {
                // free_columns 的第一个为 '1' 的位
                // 在该列我们放置当前皇后
                int curr_column = - free_columns & free_columns;

                // 放置皇后
                // 并且排除对应的列
                free_columns ^= curr_column;

                count = backtrack(row + 1,
                        (hills | curr_column) << 1,
                        next_row | curr_column,
                        (dales | curr_column) >> 1,
                        count, n);
            }
        }

        return count;
    }

}
