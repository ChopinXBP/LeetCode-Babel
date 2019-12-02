package Problems;

/**
 * 
 * @author ChopinXBP
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * �����������ַ�����ʽ��ʾ�ķǸ����� num1 �� num2������ num1 �� num2 �ĳ˻������ǵĳ˻�Ҳ��ʾΪ�ַ�����ʽ��
 * num1 �� num2 �ĳ���С��110��
 * num1 �� num2 ֻ�������� 0-9��
 * num1 �� num2 �������㿪ͷ������������ 0 ����
 * ����ʹ���κα�׼��Ĵ������ͣ����� BigInteger����ֱ�ӽ�����ת��Ϊ����������
 *
 */

public class MultiplyStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(multiply("623", "23"));
		System.out.println(multiply("623", "13"));
	}

	//ģ��Сѧ��ʽ����˷���ʽ
    public static String multiply(String num1, String num2) {
        if(num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0)
        	return "";
        
        
        //��֤num1Ϊλ�����ٵ����֣��򻯼���
        if(num1.length() > num2.length()) {
        	String temp = num1;
        	num1 = num2;
        	num2 = temp;
        }
        if(num1.length() == 1 && num1.equals("0"))
        	return "0";
        
        int len1 = num1.length();
        int len2 = num2.length();        
        
        //��һ��len1+len2�еľ�������ʱ������
        int[] matrix = new int[len1 + len2];
        
        int flag = 0;	//ÿ�γ˻����λ��       	
        for(int i = len1 - 1; i >= 0; i--) {
        	int muln1 = num1.charAt(i) - '0';       	
        	int carrybit = 0;	//��¼ÿ������Ľ�λ
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
        
        //��ȥ��ߵ�0λ
        if(result.charAt(0) == '0') {
        	result.deleteCharAt(0);
        }
        return result.toString();
    }
}
