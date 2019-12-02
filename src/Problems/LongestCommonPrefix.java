package Problems;

/**
 * 
 * @author ChopinXBP
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * Note: All given inputs are in lowercase letters a-z.
 * ��дһ�������������ַ��������е������ǰ׺��
 * ��������ڹ���ǰ׺�����ؿ��ַ��� ""��
 * ˵��: ��������ֻ����Сд��ĸ a-z ��
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

	//ˮƽɨ�跨�����ߴ������ϵ�������ǰ׺�ĳ���
    public static String longestCommonPrefix(String[] strs) {
    	if (strs == null || strs.length == 0)
			return "";
    	
    	//����һ���ַ����ĳ��ȶ���Ϊ��ʼ�����ǰ׺����ÿ���´����ǰ׺�ȶԲ������ǰ׺����
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
    
    //��ֱɨ�跨������ɨ�裬������֤�����ַ����ĵ�i��Ԫ��
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
	
	//�����㷨����ˮƽɨ�跨�Ļ����ϸĽ�
	public static String longestCommonPrefix3(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		
		return Solution(strs, 0, strs.length - 1);
	}
	
	//���ַ��������Ƚϻ�ù���ǰ׺
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
	
	//���ֲ����㷨�����startsWith������
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
	
	//��չ������һЩ��ֵ�ַ��� S = [S1����Sn]������Ҫ�ҵ��ַ��� q�� S�������ǰ׺��
	//��������ǰ׺��/�ֵ�����Prefix Tree��
	//https://leetcode.com/articles/implement-trie-prefix-tree/
}
