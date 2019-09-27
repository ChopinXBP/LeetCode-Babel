/**
 *
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 */

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int begin = 0;
        int end = s.length() - 1;
        while(begin < end){
            char left = s.charAt(begin);
            while(begin < end && !(Character.isLetterOrDigit(left))){
                left = s.charAt(++begin);
            }
            char right = s.charAt(end);
            while(end > begin && !Character.isLetterOrDigit(right)){
                right = s.charAt(--end);
            }
            begin++;
            end--;
            left = Character.toUpperCase(left);
            right = Character.toUpperCase(right);
            if(left != right){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }

    public boolean isPalindrome3(String s) {
        char[] c = s.toCharArray();
        for (int i = 0, j = c.length - 1; i < j; ) {
            if (!Character.isLetterOrDigit(c[i])) i++;
            else if (!Character.isLetterOrDigit(c[j])) j--;
            else if (Character.toLowerCase(c[i++]) != Character.toLowerCase(c[j--]))
                return false;
        }
        return true;
    }
}
