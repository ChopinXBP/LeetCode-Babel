/**
 *
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 */

public class ReverseVowelsOfAString {
    public String reverseVowels(String s) {
        int begin = 0;
        int end = s.length() - 1;
        char[] chars = s.toCharArray();
        while (begin < end){
            while(begin < end && !isVowel(chars[begin])){
                begin++;
            }
            while(begin < end && !isVowel(chars[end])){
                end--;
            }
            if(begin >= end){
                break;
            }
            char temp = chars[begin];
            chars[begin++] = chars[end];
            chars[end--] = temp;
        }
        return new String(chars);
    }

    private boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
