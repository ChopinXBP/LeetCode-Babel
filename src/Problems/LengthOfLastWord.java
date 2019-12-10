package Problems;

/**
 *
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * If the last word does not exist, return 0.
 * Note: A word is defined as a character sequence consists of non-space characters only.
 *
 */

public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        String[] str = s.split(" ");
        return str.length == 0 ? 0 : str[str.length - 1].length();
    }
}
