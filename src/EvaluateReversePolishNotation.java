/**
 * 
 * @author ChopinXBP
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are+,-,*,/. Each operand may be an integer or another expression.
 * 模拟后缀表达式
 * 
 *
 */

import java.util.Stack;

public class EvaluateReversePolishNotation {

	public static String[] str = {"2", "1", "+", "3", "*"};
	public static String[] str1 = {"0", "3", "/"};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(evalRPN(str1));
	}

    public static int evalRPN(String[] tokens) {
        
    	Stack<Integer> element = new Stack<Integer>();
    	for(int i = 0; i < tokens.length; i++){
    		if(tokens[i].equals("+")){
    			int right = element.pop();
    			int left = element.pop();
    			element.push(left + right);
    		}
    		else if(tokens[i].equals("-")){
    			int right = element.pop();
    			int left = element.pop();
    			element.push(left - right);
    		}
    		else if(tokens[i].equals("*")){
    			int right = element.pop();
    			int left = element.pop();
    			element.push(left * right);
    		}
    		else if(tokens[i].equals("/")){
    			int right = element.pop();
    			int left = element.pop();
    			element.push(left / right);
    		}
    		else{
    			element.push(Integer.parseInt(tokens[i]));
    		}
    	}
    	return element.pop();
    	
    } 
}
