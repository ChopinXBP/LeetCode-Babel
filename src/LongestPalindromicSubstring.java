/**
 * 
 * @author ChopinXBP Given a string s, find the longest palindromic substring in
 *         s. You may assume that the maximum length of s is 1000. Input:
 *         "babad" Output: "bab" Note: "aba" is also a valid answer. 给定一个字符串
 *         s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 输入: "babad" 输出: "bab" 注意: "aba"
 *         也是一个有效答案。
 *
 */

public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(longestPalindrome("cabad"));
		System.out.println(longestPalindrome("accbcd"));
		System.out.println(longestPalindrome("eababag"));
		System.out.println(longestPalindrome("abcdag"));
		System.out.println(longestPalindrome("babadada"));
	}

	////////////////////// 中心拓展法//////////////////////

	// 中心拓展法1:时间复杂度：O(n^2),空间复杂度：O(1)
	// 回文中心的两侧互为镜像,因此可以从它的中心展开，并且只有2n−1个这样的中心(分对称轴为单个元素和对称轴为两个元素两种情况)。
	public static int left, maxlen;

	public static String longestPalindrome(String s) {
		if (s == null || s.length() < 2)
			return s;

		left = 0;
		maxlen = 1;
		// 分对称轴为单个元素和对称轴为两个元素两种情况
		for (int i = 0; i < s.length(); i++) {
			findMaxPalindrome(s, i, i);
			findMaxPalindrome(s, i, i + 1);
		}

		return s.substring(left, left + maxlen);
	}

	public static void findMaxPalindrome(String s, int latter, int former) {
		// 从中心元素开始依次检查对称关系是否成立
		while (latter >= 0 && former < s.length() && s.charAt(latter) == s.charAt(former)) {
			latter--;
			former++;
		}
		// 回文子串长度超过当前最大长度，则更新起止坐标
		if (former - latter - 1 > maxlen) {
			left = latter + 1;
			maxlen = former - latter - 1;
		}
	}

	// 中心拓展法2（无全局变量）
	public static String longestPalindrome1(String s) {
		if (s == null || s.length() < 1)
			return "";
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private static int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}

	//////////////////////// Manacher算法/////////////////////////
	// https://www.jianshu.com/p/116aa58b7d81
	// 字符串转换：包含头尾的所有字符间隔插入‘#’
	public static char[] manacherString(String str) {
		StringBuilder newstr = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			newstr.append("#");
			newstr.append(str.charAt(i));
		}
		newstr.append("#");
		return newstr.toString().toCharArray();
	}

	public static int longestPalindrome2(String str) {
		if (str == null || str.length() < 1) {
			return 0;
		}

		char[] charArr = manacherString(str);
		int[] radius = new int[charArr.length];
		int R = -1;
		int C = -1;
		int max = Integer.MIN_VALUE;
		for (int p = 0; p < radius.length; p++) {
			int p1 = 2 * C - p;
			radius[p] = R > p ? Math.min(radius[p1], R - p + 1) : 1;
			while (p + radius[p] < charArr.length && p - radius[p] > -1) {
				if (charArr[p - radius[p]] == charArr[p + radius[p]]) {
					radius[p]++;
				} else {
					break;
				}
			}
			if (p + radius[p] > R) {
				R = p + radius[p] - 1;
				C = p;
			}
			max = Math.max(max, radius[p]);
		}
		return max - 1;
	}
}
