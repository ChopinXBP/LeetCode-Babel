/**
 * 
 * @author ChopinXBP
 * Given a 32-bit signed integer, reverse digits of an integer.
 * ����һ�� 32 λ���з�������������Ҫ�����������ÿλ�ϵ����ֽ��з�ת��
 * 
 */


public class ReverseInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(reverse(-123));
		System.out.println(reverse(1534236469));
	}
	
    public static int reverse(int x) {
    	
        boolean ispositive = true;
        if(x < 0) {
        	x = -x;
        	ispositive = false;
        }
        
        int num = x;        
        long result = 0;	//Ҫ�ж��Ƿ����������long�洢
        int loc = 0;
        while(num > 0) {
        	num /= 10;
        	loc++;
        }
        
        while(x > 0) {
        	result += (x % 10) * Math.pow(10, --loc);
        	if(result > 0x7fffffff)return 0;
        	x /= 10;
        }
        	
        if(ispositive) {
        	return (int)result;
        }else {
        	return -(int)result;
        }
    }
}
