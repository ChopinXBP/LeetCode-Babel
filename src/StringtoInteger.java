/**
 * 
 * @author ChopinXBP
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 */

public class StringtoInteger {
	
	public static void main(String[] args) {
		System.out.println(StrToInt("+2147483647"));
		System.out.println(StrToInt("   -42"));
		System.out.println(StrToInt("4193 with words"));
		System.out.println(StrToInt("words and 987"));
		System.out.println(StrToInt(" "));
		System.out.println(StrToInt("-91283472332"));
		System.out.println(StrToInt("10000000000000000000000000000000000000000000000000000000000000000522545459"));
		System.out.println(StrToInt("00000000000000000000000000000000000000000000000000000000000000000522545459"));
	}
	
    public static int StrToInt(String str) {
    	int end = str.length();
    	if(end == 0) return 0;
    	
    	long result = 0;
    	long loc = 1;
    	int begin = 0;
    	boolean ispositive = true;
    	
    	//跳过起始的空格
    	while(begin < end && str.charAt(begin) == ' ') {
    		begin++;
    	}
    		
    	if(begin < end && str.charAt(begin) == '+') {
    		begin++;
    	}
    	else if(begin < end && str.charAt(begin) == '-') {
    		ispositive = false;
    		begin++;
    	}
    	
    	//end指向第一个非数字字符，此时begin-end之间应为纯数字
    	for(int i = begin; i < str.length(); i++) {
    		if(!isNum(str.charAt(i))){
    			end = i;
    			break;
    		}
    	}
    	
    	int idx = 0;	
    	while(begin < end) {
    		//判断是否为数字
    		if(!isNum(str.charAt(end - 1)))
    			return 0;
    		if(idx > 10) {
        		//排除过分溢出情况,超过十位之后若有非0元素则返回最大或最小值。如最后一个测试用例
        		if(str.charAt(end - 1) != '0' && ispositive) {
        			return Integer.MAX_VALUE;
        		}else if(str.charAt(end - 1) != '0' && !ispositive) {
        			return Integer.MIN_VALUE;
        		}
        		--end;
        		continue;
    		}

    		result += (str.charAt(end - 1) - '0') * loc;
    		//判断溢出
    		if(result > 0x7fffffff && ispositive) {
    			return Integer.MAX_VALUE;
    		}else if(result > 0x7fffffff && !ispositive){
    			return Integer.MIN_VALUE;
    		}
    		loc *= 10;
    		end--;
    		idx++;
    	}
    	   	
    	return ispositive ? (int)result : -(int)result;
    }
    
    public static boolean isNum(char c){
    	if(c >= '0' && c <= '9') {
    		return true;
    	}else {
    		return false;
    	}
    }

}
