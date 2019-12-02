package Problems;

/**
 * 
 * @author ChopinXBP
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 * Follow up: Coud you solve it without converting the integer to a string?
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 进阶:你能不将整数转为字符串来解决这个问题吗？
 */

public class PalindromeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPalindrome(121));
		System.out.println(isPalindrome(1221));
		System.out.println(isPalindrome2(121));
		System.out.println(isPalindrome2(1221));
	}

	//转换字符串
    public static boolean isPalindrome(int x) {
        if(x < 0) 
        	return false;
        else if(x < 10) 
        	return true;
        
        String str = x + "";
        int left;
        int right;
        if((str.length() & 0x01) == 1) {
        	left = (str.length() >> 1) - 1;
        	right = left + 2;
        }else {
        	right = str.length() >> 1;
        	left = right - 1;
        }
        
        while(left >= 0) {
        	if(str.charAt(left) != str.charAt(right)) {
        		return false;
        	}
        	left--;
        	right++;
        }
        
        return true;
    }
    
    //不转换字符串
    public static boolean isPalindrome2(int x) {
        if(x < 0) 
        	return false;
        else if(x < 10) 
        	return true;
        
        //获得位数loc
        int num = x;
        int loc = 0;
        while(num != 0) {
        	num /= 10;
        	loc++;
        }
        
        //将x按分为两段,防止溢出
        num = x;
        int mid = 0;
        for(int i = (loc >> 1) - 1; i >= 0; i--) {
        	mid += (num % 10) * Math.pow(10, i);
        	num /= 10;
        }
        //如果数位为奇数次,跳过中间的数,此时num和mid理应相等
        if((loc & 0x01) == 1) {
        	num /= 10;
        }
        
        if(num == mid) {
        	return true;
        }else {
        	return false;
        }
    }
}
