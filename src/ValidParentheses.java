/**
 * 
 * @author ChopinXBP
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 */

import java.util.LinkedList;


public class ValidParentheses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isValid("([)]"));
		System.out.println(isValid("{[]}"));
		System.out.println(isValid("()[]{}"));
	}
	
	public static char[] prts = {'(', ')', '[', ']', '{', '}'};
	
    public static boolean isValid(String s) {
        if(s == null || s == "")return true;
        
        LinkedList<Character> validstack = new LinkedList<>();
        
        int idx = 0;
        while(idx < s.length()) {
        	//左括号入栈，右括号出栈
        	char c = s.charAt(idx++);
            if(typeleft(c)) {
            	validstack.add(c);
            }
            else if(!validstack.isEmpty()){
            	char cs = validstack.getLast();
            	if((c == prts[1] && cs == prts[0]) || (c == prts[3] && cs == prts[2]) || (c == prts[5] && cs == prts[4])) {
            		validstack.pollLast();
            	}else {
            		return false;
            	}
            }
            else {
            	return false;
            }
        }
        
        if(validstack.isEmpty()) {
        	return true;
        }else {
        	return false;
        }
        
    }
    
    public static boolean typeleft(char c) {
    	if(c == prts[0] || c == prts[2] || c == prts[4]) {
    		return true;
    	}else {
    		return false;
    	}
    }
}
