package Problems;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 */

public class LetterCombinationsOfAPhoneNumber {
    ArrayList<String> result;

    public List<String> letterCombinations(String digits) {
        result = new ArrayList<>();
        if(digits == null || digits.length() == 0)
            return result;
        String[] words = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        Solution(words, digits, 0, "");
        return result;
    }

    public void Solution(String[] words, String digits, int idx, String str){
        if(idx == digits.length()){
            result.add(str);
            return;
        }
        String word = words[digits.charAt(idx) - '2'];
        for(int i = 0; i < word.length(); i++){
            Solution(words, digits, idx + 1, str + word.charAt(i));
        }
    }
}
