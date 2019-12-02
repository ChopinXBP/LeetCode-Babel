package Problems;

/**
 * 
 * @author ChopinXBP
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 */

public class MultiplyStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(multiply("623", "23"));
		System.out.println(multiply("623", "13"));
	}

	//模拟小学列式手算乘法公式
    public static String multiply(String num1, String num2) {
        if(num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0)
        	return "";
        
        
        //保证num1为位数较少的数字，简化计算
        if(num1.length() > num2.length()) {
        	String temp = num1;
        	num1 = num2;
        	num2 = temp;
        }
        if(num1.length() == 1 && num1.equals("0"))
        	return "0";
        
        int len1 = num1.length();
        int len2 = num2.length();        
        
        //用一个len1+len2列的矩阵存放临时运算结果
        int[] matrix = new int[len1 + len2];
        
        int flag = 0;	//每次乘积结果位移       	
        for(int i = len1 - 1; i >= 0; i--) {
        	int muln1 = num1.charAt(i) - '0';       	
        	int carrybit = 0;	//记录每次运算的进位
        	for(int j = len2 - 1; j >= 0; j--) {
        		int sum = muln1 * (num2.charAt(j) - '0') + carrybit + matrix[len2 - 1 - j + flag];
        		matrix[len2 - 1 - j + flag] = sum % 10;
        		carrybit = sum / 10;
        	}
        	matrix[len2 + flag] = carrybit;
        	flag++;
        }
        
        StringBuilder result = new StringBuilder();
        for(int i = len1 + len2 - 1; i >= 0; i--) {
        	result.append(matrix[i]);
        }
        
        //除去最高的0位
        if(result.charAt(0) == '0') {
        	result.deleteCharAt(0);
        }
        return result.toString();
    }
}
