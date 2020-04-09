package Problems;

/**
 *
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.
 * Please note that the string does not contain any non-printable characters.
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 *
 */

public class NumberOfSegmentsInAString {
    public int countSegments(String s) {
        int idx = 0;
        while(idx < s.length() && s.charAt(idx) == ' '){
            idx++;
        }
        if(idx == s.length()){
            return 0;
        }
        int end = s.length() - 1;
        while(end >= idx && s.charAt(end) == ' '){
            end--;
        }
        int result = 1;
        while(idx < end) {
            if(s.charAt(idx) == ' '){
                result++;
                while(idx < end && s.charAt(idx) == ' '){
                    idx++;
                }
                continue;
            }
            idx++;
        }
        return result;
    }
}
