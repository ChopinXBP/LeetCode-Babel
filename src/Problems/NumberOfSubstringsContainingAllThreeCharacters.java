package Problems;

/**
 *
 * Given a string s consisting only of characters a, b and c.
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 * 给你一个字符串 s ，它只包含三种字符 a, b 和 c 。
 * 请你返回 a，b 和 c 都 至少 出现过一次的子字符串数目。
 *
 */

public class NumberOfSubstringsContainingAllThreeCharacters {
    public int numberOfSubstrings(String s) {
        int len = s.length();
        int result = 0;
        int[] words = new int[3];
        int count = 0;
        int begin = 0;
        int end = 0;
        while(begin < len - 2){
            while(count == 3 && begin <= end - 2){
                result += len - end + 1;
                int beginLoc = s.charAt(begin++) - 'a';
                if(words[beginLoc]-- == 1){
                    count--;
                }
            }
            if(end == len){
                break;
            }
            while(end < len){
                int endLoc = s.charAt(end++) - 'a';
                if(words[endLoc]++ == 0){
                    if(++count == 3){
                        break;
                    }
                }
            }
        }
        return result;
    }
}
