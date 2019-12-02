package Problems;

/**
 * 
 * @author ChopinXBP
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * Note: All given inputs are in lowercase letters a-z.
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 说明: 所有输入只包含小写字母 a-z 。
 *
 */

public class LongestCommonPrefix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] strs = {"flower","flow","flight"};
		String[] strs2 = {"aa","aa"};
		String[] strs3 = {"a"};
		System.out.println(longestCommonPrefix(strs));
		System.out.println(longestCommonPrefix2(strs));
		System.out.println(longestCommonPrefix3(strs));
		System.out.println(longestCommonPrefix4(strs));
		System.out.println(longestCommonPrefix4(strs2));
		System.out.println(longestCommonPrefix4(strs3));
	}

	//水平扫描法：在线处理，不断调整公共前缀的长度
    public static String longestCommonPrefix(String[] strs) {
    	if (strs == null || strs.length == 0)
			return "";
    	
    	//将第一个字符串的长度定义为初始最长公共前缀，将每个新串与最长前缀比对并更新最长前缀长度
    	int maxcomidx = strs[0].length() - 1;
    	for(int i = 1; i < strs.length; i++) {
    		int idx = -1;
    		while(idx < maxcomidx && idx < strs[i].length() - 1) {
    			if(strs[0].charAt(idx + 1) == strs[i].charAt(idx + 1)) {
    				idx++;
    			}else {
    				break;
    			}
    		}
    		
    		if(idx == -1) return "";
    		maxcomidx = idx;
    	}
    	
    	return strs[0].substring(0, maxcomidx + 1);
    }
    
    //垂直扫描法：按列扫描，依次验证所有字符串的第i个元素
	public static String longestCommonPrefix2(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		for (int i = 0; i < strs[0].length(); i++) {
			char c = strs[0].charAt(i);
			for (int j = 1; j < strs.length; j++) {
				if (i == strs[j].length() || strs[j].charAt(i) != c)
					return strs[0].substring(0, i);
			}
		}
		return strs[0];
	}
	
	//分治算法，在水平扫描法的基础上改进
	public static String longestCommonPrefix3(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		
		return Solution(strs, 0, strs.length - 1);
	}
	
	//将字符串两两比较获得公共前缀
	public static String Solution(String[] strs, int begin, int end) {
		if(begin == end) {
			return strs[begin];
		}
		else {
			int mid = (begin + end) >> 1;
			String str1 = Solution(strs, begin, mid);
			String str2 = Solution(strs, mid + 1, end);
			if(str1 == "" || str2 == "") return "";
			
			int idx = -1;
			while(idx < str1.length() - 1 && idx < str2.length() - 1) {
				if(str1.charAt(idx + 1) == str2.charAt(idx + 1)) {
					idx++;
				}else {
					break;
				}
			}
			if(idx == -1) return "";
			return strs[begin].substring(0, idx + 1);
		}
	}
	
	//二分查找算法（配合startsWith方法）
	public static String longestCommonPrefix4(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		
		int begin = 1;
		int end = strs[0].length();
		int maxcomidx = -1;
		
		while(begin <= end) {
			int mid = (begin + end) >> 1;
			if(isCommonPrefix(strs, mid)) {
				begin = mid + 1;
				maxcomidx = mid;
			}else {
				end = mid - 1;
			}
		}
		
		if(maxcomidx == -1) {
			return "";
		}else {
			return strs[0].substring(0, maxcomidx);
		}
	}
	
	public static boolean isCommonPrefix(String[] strs, int idx) {
		String prefix = strs[0].substring(0, idx);
		for(int i = 1; i < strs.length; i++) {
			if(!strs[i].startsWith(prefix)) {
				return false;
			}
		}
		return true;
	}
	
	//拓展：给定一些键值字符串 S = [S1……Sn]，我们要找到字符串 q与 S的最长公共前缀。
	//可以利用前缀树/字典树（Prefix Tree）
	//https://leetcode.com/articles/implement-trie-prefix-tree/
}
