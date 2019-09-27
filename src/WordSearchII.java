import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 */

public class WordSearchII {
    //回溯法
    public List<String> findWords(char[][] board, String[] words) {
        ArrayList<String> result = new ArrayList<>();
        for(String str : words){
            if(exist(board, str)){
                result.add(str);
            }
        }
        return result;
    }

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

    //字典树
    class TrieNode{
        public TrieNode[] children = new TrieNode[26];
        public String word = null;
    }

    class Trie{
        public TrieNode root = new TrieNode();
        public void insert(String word){
            TrieNode node = root;
            for(int i = 0; i < word.length(); i++){
                int charNum = word.charAt(i) - 'a';
                if(node.children[charNum] == null){
                    node.children[charNum] = new TrieNode();
                }
                node = node.children[charNum];
            }
            node.word = word;
        }
    }

    public List<String> findWords2(char[][] board, String[] words) {
        Trie trie = new Trie();
        for(String str : words){
            trie.insert(str);
        }
        boolean[][] isVisited = new boolean[board.length][board[0].length];
        HashSet<String> result = new HashSet<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                search(board, isVisited, i , j, trie.root, result);
            }
        }
        return new LinkedList<>(result);
    }

    private void search(char[][] board, boolean[][] isVisited, int x, int y, TrieNode node, HashSet<String> result){
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || isVisited[x][y]){
            return;
        }
        node = node.children[board[x][y] - 'a'];
        if(node == null){
            return;
        }
        if(node.word != null){
            result.add(node.word);
        }
        isVisited[x][y] = true;
        search(board, isVisited, x - 1, y, node, result);
        search(board, isVisited, x + 1, y, node, result);
        search(board, isVisited, x, y - 1, node, result);
        search(board, isVisited, x, y + 1, node, result);
        isVisited[x][y] = false;
    }
}
