package Problems;

/**
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */

public class ImplementstrStr {

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        return haystack.indexOf(needle);
    }


    //返回子串：KMP算法
    //https://www.cnblogs.com/yjiyjige/p/3263858.html
    public class Solution {
        public int[] getNext(String needle) {
            int j = -1;
            int i = 0;
            int[] next = new int[needle.length()];
            next[i] = j;
            while (i < needle.length() - 1) {
                if (j == -1 || needle.charAt(i) == needle.charAt(j)) {
                    i++;
                    j++;
                    if (needle.charAt(i) != needle.charAt(j)) {
                        next[i] = j;
                    } else {
                        next[i] = next[j];
                    }

                } else {
                    j = next[j];
                }
            }
            return next;
        }

        public String strStr(String haystack, String needle) {
            if (haystack == null || needle == null || needle.length() == 0) {
                return haystack;
            }
            if (needle.length() > haystack.length()) {
                return null;
            }
            int[] next = getNext(needle);
            int i = 0;
            int j = 0;
            while (i < haystack.length() && j < needle.length()) {
                if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                    i++;
                    j++;
                } else {
                    j = next[j];
                }

            }
            if (j >= needle.length()) {
                return haystack.substring(i - needle.length());
            } else {
                return null;
            }

        }
    }
}
