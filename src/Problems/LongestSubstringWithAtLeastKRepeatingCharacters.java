package Problems;

/**
 *
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
 *
 */

public class LongestSubstringWithAtLeastKRepeatingCharacters {
    //递归分割子串
    public int longestSubstring(String s, int k) {
        if(s == null || s.length() == 0){
            return 0;
        }

        //统计所有字母出现的频次
        int[] chars = new int[26];
        for(int i = 0; i < s.length(); i++){
            chars[s.charAt(i) - 'a']++;
        }

        //判断整个字符串是否满足最长子串要求，如果满足就直接返回字符串长度
        boolean flag = true;
        for(int nums : chars){
            if(nums > 0 && nums < k){
                flag = false;
            }
        }
        if(flag){
            return s.length();
        }

        //将出现频次小于k的字符作为分割点，递归判断所有被分割的子串
        int result = 0;
        int start = 0;
        int cur = 0;
        while(cur < s.length()){
            //如果当前字母频次小于k，则一定不可能出现在当前子串中，将其分割。
            //递归判断cur之前start-cur的子串是否满足，再将start从cur之后开始
            if(chars[s.charAt(cur) - 'a'] < k){
                result = Math.max(result, longestSubstring(s.substring(start, cur), k));
                start = cur + 1;
            }
            cur++;
        }
        result = Math.max(result, longestSubstring(s.substring(start), k));

        return result;
    }

    //动态规划+滑动窗口法
    public int longestSubstring2(String s, int k) {
        int result = 0;
        for(int i = 1; i < 26; i++){
            result = Math.max(result, Solution(s, k, i));
        }
        return result;
    }

    //求在numUniqueTarget个不同字母至少重复出现k次的情况下，最长满足子串的长度
    private int Solution(String s, int k, int numUniqueTarget){
        int[] words = new int[26];
        int numUnique = 0;
        int numNoLessThanK = 0;

        int begin = 0;
        int end = 0;
        int result = 0;
        while(end < s.length()){
            //窗口右侧end向前，统计出现过的字母个数numUnique（可能大于1次）和出现至少k次的字母个数numNoLessThanK（用==避免重复计算）
            if(words[s.charAt(end) - 'a']++ == 0){
                numUnique++;
            }
            if(words[s.charAt(end++) - 'a'] == k){
                numNoLessThanK++;
            }
            //当出现过的字母个数numUnique超过目标字母个数numUniqueTarget时，压缩窗口直到两者相同
            while(numUnique > numUniqueTarget){
                if(words[s.charAt(begin) - 'a']-- == k){
                    numNoLessThanK--;
                }
                if(words[s.charAt(begin++) - 'a'] == 0){
                    numUnique--;
                }
            }
            //当窗口内出现过的字母个数numUnique、目标字母个数numUniqueTarget、出现至少k次的字母个数numNoLessThanK相等时，说明窗口子串满足条件
            if(numUnique == numUniqueTarget && numUnique == numNoLessThanK){
                result = Math.max(end - begin, result);
            }
        }

        return result;
    }
}
