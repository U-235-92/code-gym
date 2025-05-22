package aq.gym.contests.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateReversePolishNotation {

	public static void main(String[] args) {
		String[] tokens = {"0", "0", "*"};
		System.out.println(new EvaluateReversePolishNotation().evalRPN(tokens));
	}

	public int evalRPN(String[] tokens) {
        Deque<Integer> operands = new ArrayDeque<>();
        for(int i = 0; i < tokens.length; i++) {
        	char ch = tokens[i].charAt(0);
        	if(ch == '-') {
        		if(tokens[i].length() > 1) {
        			operands.push(Integer.valueOf(tokens[i]));
        			continue;
        		} 
        	}
        	if(Character.isDigit(ch)) {
        		operands.push(Integer.valueOf(tokens[i]));
        	} else {
        		int b = operands.pop();
        		int a = operands.pop();
        		evaluate(operands, a, b, ch);
        	}
        }
        return operands.pop();
    }
	
	private void evaluate(Deque<Integer> operands, int a, int b, char operator) {
		switch (operator) {
			case '+': 
				operands.push(a + b);
				break;
			case '-':
				operands.push(a - b);
				break;
			case '/':
				operands.push(a / b);
				break;
			case '*':
				operands.push(a * b);
				break;
		}
	}
}
