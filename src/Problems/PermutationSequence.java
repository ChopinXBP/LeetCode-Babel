package Problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given n and k, return the kth permutation sequence.
 * 给定 n 和 k，返回第 k 个排列。
 *
 */

public class PermutationSequence {
    //剪枝回溯
    public String getPermutation(int n, int k) {
        ArrayList<String> result = new ArrayList<>();
        Solution("", new boolean[n + 1], result, n, k);
        return result.get(k - 1);
    }

    public void Solution(String temp, boolean[] isVisited, ArrayList<String> result, int n, int k){
        if(temp.length() == n){
            result.add(new String(temp));
        }
        for(int i = 1; i <= n; i++){
            if(result.size() == k){
                return;
            }
            if(isVisited[i]){
                continue;
            }
            isVisited[i] = true;
            String str = temp + i;
            Solution(str, isVisited, result, n, k);
            isVisited[i] = false;
        }
    }

    //按位定位（数学）：每一位确定后，剩余排列数字的总数是可知的，因此可以从高到低确定每一位。
    public String getPermutation2(int n, int k) {
        StringBuilder sb = new StringBuilder();
        // 候选数字
        List<Integer> candidates = new ArrayList<>();
        // 分母的阶乘数
        int[] factorials = new int[n+1];
        factorials[0] = 1;
        int fact = 1;
        for(int i = 1; i <= n; ++i) {
            candidates.add(i);
            fact *= i;
            factorials[i] = fact;
        }
        k -= 1;
        for(int i = n-1; i >= 0; --i) {
            // 计算候选数字的index
            int index = k / factorials[i];
            sb.append(candidates.remove(index));
            k -= index*factorials[i];
        }
        return sb.toString();
    }

    //康托展开
    //https://baike.baidu.com/item/%E5%BA%B7%E6%89%98%E5%B1%95%E5%BC%80/7968428?fr=aladdin
    public String getPermutation3(int n, int k) {
        LinkedList<Character> nums = new LinkedList<>();
        for(int i = 1; i <= 9; i++){
            nums.add((char)(i + '0'));
        }
        ArrayList<Integer> newlist = new ArrayList<>();
		int[] factor ={1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        StringBuilder result = new StringBuilder();
        for(--k; n-- != 0; k %= factor[n]){
			int i = k / factor[n];
            result.append(nums.get(i));
            nums.remove(i);
        }
        return result.toString();
    }
}
