package Problems;

/**
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 */

public class WordSearch {
    //回溯法
    public boolean exist(char[][] board, String word) {
        boolean[][] isVisited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0) && Solution(board, word, 0, i, j, board.length, board[0].length, isVisited)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean Solution(char[][] board, String word, int loc, int x, int y, int rowlen, int collen, boolean[][] isVisited){
        if(loc == word.length()){
            return true;
        }
        if(x < 0 || x >= rowlen || y < 0 || y >= collen || isVisited[x][y] || board[x][y] != word.charAt(loc)){
            return false;
        }
        //小技巧，可用前后两次board[x][y] ^= 256代替isVisited功能
        isVisited[x][y] = true;
        boolean result = Solution(board, word, loc + 1, x - 1, y , rowlen, collen, isVisited)
                || Solution(board, word, loc + 1, x + 1, y , rowlen, collen, isVisited)
                || Solution(board, word, loc + 1, x, y - 1 , rowlen, collen, isVisited)
                || Solution(board, word, loc + 1, x, y + 1, rowlen, collen, isVisited);
        isVisited[x][y] = false;
        return result;
    }
}
