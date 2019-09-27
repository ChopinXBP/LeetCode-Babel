/**
 *
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 */

public class ReverseWordsInAStringIII {

    public String reverseWords(String s) {
        String[] strs = s.split(" ");
        StringBuilder newstr = new StringBuilder();
        for(String string : strs){
            newstr.append(new StringBuilder(string).reverse() + " ");
        }
        return newstr.deleteCharAt(newstr.length() - 1).toString();
    }
}
