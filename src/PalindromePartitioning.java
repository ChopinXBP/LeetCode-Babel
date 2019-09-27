import java.util.*;

/**
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 *
 */

public class PalindromePartitioning {
    //回溯法
    List<List<String>> result = new LinkedList<>();
    public List<List<String>> partition(String s) {
        LinkedList<String> list = new LinkedList<>();
        Solution(s, list, 0);
        return result;
    }

    public void Solution(String s, LinkedList<String> list, int idx){
        if(idx == s.length()){
            result.add(new LinkedList<>(list));
            return;
        }
        //从idx开始遍历每一位i
        for(int i = idx; i < s.length(); i++){
            //判断idx—i是否构成回文串，若构成回文串则继续递归遍历
            if(isPalindromed(s, idx, i)){
                list.add(s.substring(idx, i + 1));
                Solution(s, list, i + 1);
                //将添加的子串去除，保持回溯
                list.pollLast();
            }
        }
    }

    public boolean isPalindromed(String s, int begin, int end){
        while(begin < end){
            if(s.charAt(begin++) != s.charAt(end--)){
                return false;
            }
        }
        return true;
    }

    //动态规划
    public List<List<String>> partition2(String s) {
        int len = s.length();
        //answer[i]用于存储从0开始至i-1位所有的回文子串序列
        List<List<String>>[] answer = new List[len + 1];
        answer[0] = new ArrayList<>();
        answer[0].add(new ArrayList<>());

        //formBeginToEndIsPal[begin][end]代表s从begin-end是否为回文串
        boolean[][] formBeginToEndIsPal = new boolean[len][len];
        for(int end = 0; end < len; end++){
            answer[end + 1] = new ArrayList<>();
            //对于每一个end从头遍历每一位begin
            for(int begin = 0; begin <= end; begin++){
                //如果从begin-end是回文串，将answer[begin]中的所有子串序列一次加上begin-end行成的新回文串，作为answer[end + 1]的回文子串序列
                if(s.charAt(begin) == s.charAt(end) && (end - begin <= 1 || formBeginToEndIsPal[begin + 1][end - 1])){
                    formBeginToEndIsPal[begin][end] = true;
                    //begin-end行成的新回文串
                    String str = s.substring(begin, end + 1);
                    for(List<String> list : answer[begin]){
                        List<String> newList = new ArrayList<>(list);
                        newList.add(str);
                        answer[end + 1].add(newList);
                    }
                }
            }
        }
        /*
        //输出结果按每个字符串的字典序排序
        Collections.sort(answer[len], new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                int o1Size = o1.size();
                int o2Size = o2.size();
                int count = o1Size < o2Size ? o1Size : o2Size;
                for (int i = 0; i < count; i++) {
                    if (o1.get(i).compareTo(o2.get(i)) != 0) {
                        return o1.get(i).compareTo(o2.get(i));
                    }
                }
                return Integer.compare(o1Size, o2Size);
            }
        });
        */
        return answer[len];
    }
}
