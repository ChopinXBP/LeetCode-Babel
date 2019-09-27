/**
 * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
 * 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 * 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）
 */

public class KthSymbolInGrammar {
    //递归
    public int kthGrammar(int N, int K) {
        if (N == 1) {
            return 0;
        }
        int result = kthGrammar(N - 1, (K + 1) >> 1);
        //result为父节点，K为子结点，K的奇偶代表子树的左右
        //父0子奇=0，父0子偶=1，父1子奇=1，父1子偶=0
        return result == 1 ? ((K & 0x01) == 0 ? 0 : 1) : ((K & 0x01) == 0 ? 1 : 0);
    }

    //递归：逻辑改进
    public int kthGrammar2(int N, int K) {
        if (N == 1) {
            return 0;
        }
        return (~K & 1) ^ kthGrammar(N - 1, (K + 1) / 2);
    }

    //递归：翻转思想
    //后半部分总是与前半部分相反，也就是说：'0' 变成 '1' 而 '1' 变成 '0'
    //如果 K 在后半部分，那么我们可以将 K -= (1 << N-2) 设为前半部分，然后翻转得到最终答案。
    public int kthGrammar3(int N, int K) {
        if (N == 1){
            return 0;
        }
        if (K <= 1 << N - 2){
            return kthGrammar(N - 1, K);
        }
        return kthGrammar(N - 1, K - (1 << N - 2)) ^ 1;
    }

    //二进制计数：翻转次数等于K-1中二进制1的个数
    public int kthGrammar4(int N, int K) {
        return Integer.bitCount(K - 1) & 1;
    }
}
