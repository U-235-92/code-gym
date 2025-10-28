package aq.gym.contests.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {

//	https://leetcode.com/problems/basic-calculator/description/
	public static void main(String[] args) {
//		String s = "1 + 1"; // 2
//		String s = " 2-1 + 2 "; // 3
//		String s = "-(12-4)"; // -8
//		String s = "(1-(4+5-20)+3)"; // 15 
//		String s = "(-2+6+8)"; // 12
//		String s = "(1-(4+5-20)+3)-(-2+6+8)"; // 3
//		String s = "-(3-(-(4+5)))"; // -12
//		String s = "2147483647"; // 2147483647
		String s = "-2147483648";
		System.out.println(new BasicCalculator().calculate(s));
	}

    public int calculate(String s) {
    	int result = 0, sign = 1;
        Deque<Integer> operands = new ArrayDeque<>();
        Deque<Integer> signs = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++) {
        	char ch = s.charAt(i);
        	if(isDigit(ch)) {
        		int operand = Character.digit(ch, 10), j = i + 1;
        		for(j = i + 1; j < s.length(); j++) {
        			ch = s.charAt(j);
        			if(isDigit(ch)) {
        				operand = operand * 10 + Character.digit(ch, 10);
        			} else {
        				break;
        			}
        		}
        		result += operand * sign;
        		i = j - 1;
        	} else if(isSpace(ch)) {
        		continue;
        	} else if(isMinus(ch)) {
        		sign = -1;
        	} else if(isPlus(ch)) {
        		sign = 1;
        	} else if(isOpenBracket(ch)) {
        		signs.push(sign);
        		operands.push(result);
        		result = 0;
        		sign = 1;
        	} else if(isCloseBracket(ch)) {
        		int prevOperand = operands.pop();
        		int prevSign = signs.pop();
        		result = prevOperand + (prevSign * result);
        	} 
        }
        return result;
    }

	private boolean isDigit(char ch) {
    	return Character.isDigit(ch);
    }
    
    private boolean isSpace(char ch) {
    	return ch == ' ';
    }
    
    private boolean isMinus(char ch) {
    	return ch == '-';
    }
    
    private boolean isPlus(char ch) {
    	return ch == '+';
    }
    
    private boolean isOpenBracket(char ch) {
    	return ch == '(';
    }
    
    private boolean isCloseBracket(char ch) {
    	return ch == ')';
    }
}
