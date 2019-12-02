package Problems;

/**
 *
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * You have the following 3 operations permitted on a word:
 * Insert a character
 * Delete a character
 * Replace a character
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 */

public class EditDistance {
    //动态规划
    public int minDistance(String word1, String word2) {
        //dp[i][j]代表word1的前i个字符构成的子串与word2前j个字符构成的子串的编辑距离
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        //第一行和第一列相对于空串的编辑距离就是其字符个数
        for(int i = 1; i <= word2.length(); i++){
            dp[0][i] = i;
        }
        for(int i = 1; i <= word1.length(); i++){
            dp[i][0] = i;
        }

        //对于变化至dp[i][j]的过程，dp[i][j-1]表示插入操作，dp[i-1][j]表示删除操作，dp[i-1][j-1]表示替换操作
        for(int i = 1; i <= word1.length(); i++){
            for(int j = 1; j <= word2.length(); j++){
                //当两个子串的尾字符相同时，可以看成是去掉尾字符的原子串没有进行额外操作获得，dp[i][j] = dp[i-1][j-1]
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                //两子串尾字符不同时，可以从上一子串进行插入、删除、替换（三者对应的原子串不同）任一操作得来
                else{
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
