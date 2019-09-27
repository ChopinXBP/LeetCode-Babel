/**
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
 *
 */

public class MinimumWindowSubstring {
    public static void main(String[] args){
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public static String minWindow(String s, String t) {

        //将模板存在元素及其个数装入桶template中
        int[] template = new int[58];
        for(int i = 0; i < t.length(); i++){
            ++template[t.charAt(i) - 'A'];
        }

        int begin = 0;
        int end = 0;
        int[] match = new int[58];
        int num = 0;
        int resultBegin = 0;
        int resultEnd = Integer.MAX_VALUE;

        while(end < s.length()){
            //若当前元素i不为模板元素，或当前元素出现次数已经超过模板次数，记录match，窗口右侧向前滑动
            int loc = s.charAt(end) - 'A';
            ++end;
            if(++match[loc] <= template[loc]){
                //对元素总数进行计数，检查个数是否与模板个数相等
                if(++num == t.length()){
                    //若元素总数满足，将滑动窗口左侧前移（跳过所有非模板元素和过多的模板元素），直到窗口为最小满足窗口
                    int leftloc = s.charAt(begin) - 'A';
                    while(begin < end && template[leftloc] < match[leftloc]){
                        --match[leftloc];
                        leftloc = s.charAt(++begin) - 'A';
                    }
                    //更新最小窗口，，更新完后窗口左侧前移一位，继续进行窗口滑动
                    if(end - begin < resultEnd - resultBegin){
                        resultBegin = begin;
                        resultEnd = end;
                    }
                    ++begin;
                    --match[leftloc];
                    --num;
                }
            }
        }
        return resultEnd == Integer.MAX_VALUE ? "" : s.substring(resultBegin, resultEnd);
    }

}
