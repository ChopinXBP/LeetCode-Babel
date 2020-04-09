package Problems;

/**
 *
 * Given an array of characters, compress it in-place.
 * The length after compression must always be smaller than or equal to the original array.
 * Every element of the array should be a character (not int) of length 1.
 * After you are done modifying the input array in-place, return the new length of the array.
 * 给定一组字符，使用原地算法将其压缩。
 * 压缩后的长度必须始终小于或等于原数组长度。
 * 数组的每个元素应该是长度为1 的字符（不是 int 整数类型）。
 * 在完成原地修改输入数组后，返回数组的新长度。
 *
 */

public class StringCompression {
    public int compress(char[] chars) {
        int result = chars.length;
        int idx = 1;
        while(idx < result){
            if(chars[idx] != chars[idx - 1]){
                idx++;
                continue;
            }
            int nums = 1;
            int end = idx;
            while(end < result && chars[end] == chars[end - 1]){
                end++;
                nums++;
            }
            int strNum = solution(chars, nums, idx, end, result);
            idx += strNum + 1;
            result -= nums - strNum - 1;
        }
        return result;
    }

    private int solution(char[] chars, int nums, int begin, int end, int result){
        String num = nums + "";
        for (int i = 0; i < num.length(); i++) {
            chars[begin++] = num.charAt(i);
        }
        for (int i = end; i < result; i++) {
            chars[begin++] = chars[i];
        }
        return num.length();
    }

    //双指针
    public int compress2(char[] chars) {
        int anchor = 0, write = 0;
        for (int read = 0; read < chars.length; read++) {
            if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
                chars[write++] = chars[anchor];
                if (read > anchor) {
                    for (char c: ("" + (read - anchor + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }
        return write;
    }
}
