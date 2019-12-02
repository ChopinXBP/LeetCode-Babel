package Problems;

/**
 * 
 * @author ChopinXBP
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 * Follow up: Coud you solve it without converting the integer to a string?
 * �ж�һ�������Ƿ��ǻ���������������ָ���򣨴������ң��͵��򣨴������󣩶�����һ����������
 * ����:���ܲ�������תΪ�ַ�����������������
 */

public class PalindromeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPalindrome(121));
		System.out.println(isPalindrome(1221));
		System.out.println(isPalindrome2(121));
		System.out.println(isPalindrome2(1221));
	}

	//ת���ַ���
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
    
    //��ת���ַ���
    public static boolean isPalindrome2(int x) {
        if(x < 0) 
        	return false;
        else if(x < 10) 
        	return true;
        
        //���λ��loc
        int num = x;
        int loc = 0;
        while(num != 0) {
        	num /= 10;
        	loc++;
        }
        
        //��x����Ϊ����,��ֹ���
        num = x;
        int mid = 0;
        for(int i = (loc >> 1) - 1; i >= 0; i--) {
        	mid += (num % 10) * Math.pow(10, i);
        	num /= 10;
        }
        //�����λΪ������,�����м����,��ʱnum��mid��Ӧ���
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
