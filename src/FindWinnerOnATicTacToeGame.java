/**
 *
 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid.
 * Here are the rules of Tic-Tac-Toe:
 * Players take turns placing characters into empty squares (" ").
 * The first player A always places "X" characters, while the second player B always places "O" characters.
 * "X" and "O" characters are always placed into empty squares, never on filled ones.
 * The game ends when there are 3 of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * Given an array moves where each element is another array of size 2 corresponding to the row and column of the grid where they mark their respective character in the order in which A and B play.
 * Return the winner of the game if it exists (A or B), in case the game ends in a draw return "Draw", if there are still movements to play return "Pending".
 * You can assume that moves is valid (It follows the rules of Tic-Tac-Toe), the grid is initially empty and A will play first.
 * A 和 B 在一个 3 x 3 的网格上玩井字棋。
 * 井字棋游戏的规则如下：
 * 玩家轮流将棋子放在空方格 (" ") 上。
 * 第一个玩家 A 总是用 "X" 作为棋子，而第二个玩家 B 总是用 "O" 作为棋子。
 * "X" 和 "O" 只能放在空方格中，而不能放在已经被占用的方格上。
 * 只要有 3 个相同的（非空）棋子排成一条直线（行、列、对角线）时，游戏结束。
 * 如果所有方块都放满棋子（不为空），游戏也会结束。
 * 游戏结束后，棋子无法再进行任何移动。
 * 给你一个数组 moves，其中每个元素是大小为 2 的另一个数组（元素分别对应网格的行和列），它按照 A 和 B 的行动顺序（先 A 后 B）记录了两人各自的棋子位置。
 * 如果游戏存在获胜者（A 或 B），就返回该游戏的获胜者；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 * 你可以假设 moves 都 有效（遵循井字棋规则），网格最初是空的，A 将先行动。
 *
 */

public class FindWinnerOnATicTacToeGame {
    public String tictactoe(int[][] moves) {
        int[][] map = new int[3][3];
        for(int i = 0; i < moves.length; i++) {
            int person = (i & 1) == 0 ? 1 : 2;
            map[moves[i][0]][moves[i][1]] = person;
            if(i > 1 && canWin(map, moves[i][0], moves[i][1], person)){
                return person == 1 ? "A" : "B";
            }
        }
        return moves.length == 9 ? "Draw" : "Pending";
    }

    private boolean canWin(int[][] moves, int x, int y, int p){
        if(moves[x][0] == p && moves[x][1] == p && moves[x][2] == p){
            return true;
        }
        if(moves[0][y] == p && moves[1][y] == p && moves[2][y] == p){
            return true;
        }
        if(x == y && moves[0][0] == p && moves[1][1] == p && moves[2][2] == p){
            return true;
        }
        if(moves[2][0] == p && moves[1][1] == p && moves[0][2] == p){
            return true;
        }
        return false;
    }
}
