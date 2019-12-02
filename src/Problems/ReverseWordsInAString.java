package Problems;

/**
 *
 * Given an input string, reverse the string word by word.
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 */

public class ReverseWordsInAString {
    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ' '){
                continue;
            }
            int end = i + 1;
            while(end < s.length() && s.charAt(end) != ' '){
                end++;
            }
            if(result.length() == 0){
                result.insert(0, s.substring(i, end));
            }else{
                result.insert(0, s.substring(i, end) + " ");
            }
            i = end;
        }
        return result.toString();
    }
}
