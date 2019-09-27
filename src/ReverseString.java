/**
 *
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * You may assume all the characters consist of printable ascii characters.
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 */

public class ReverseString {
    public void reverseString(char[] s) {
        int begin = 0;
        int end = s.length - 1;
        while(begin < end){
            char temp = s[begin];
            s[begin++] = s[end];
            s[end--] = temp;
        }
    }
}
