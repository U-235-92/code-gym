package aq.gym.contests.other;

import java.util.ArrayDeque;
import java.util.Deque;

public class InfixToPostfix {

	public static void main(String[] args) {
		System.out.println(convert("A*(B+C)"));
	}
	
	private static String convert(String infix) {
		StringBuilder postfix = new StringBuilder();
		Deque<Character> operatorStack = new ArrayDeque<>();
		for(int i = 0; i < infix.length(); i++) {
			char ch = infix.charAt(i);
			if(Character.isAlphabetic(ch)) {
				postfix.append(ch);
			} else {
				if(operatorStack.isEmpty()) {
					operatorStack.push(ch);
				} else {
					if(ch == '+' || ch == '-') {
						convert(postfix, operatorStack, ch, 1);
					} else if(ch == '/' || ch == '*') {
						convert(postfix, operatorStack, ch, 2);
					} else if(ch == '(') {
						operatorStack.push(ch);
					} else if(ch == ')') {
						convert(postfix, operatorStack);
					}
				}
			}
		}
		while(!operatorStack.isEmpty()) {
			postfix.append(operatorStack.pop());
		}
		return postfix.toString();
	}
	
	private static void convert(StringBuilder postfix, Deque<Character> operatorStack, char newOperator, int newOperatorPriority) {
		while(!operatorStack.isEmpty()) {
			char topOperator = operatorStack.pop();
			int topOperatorPriority = 0;
			if(topOperator == '(') {
				operatorStack.push(topOperator);
				break;
			}
			if(topOperator == '+' || topOperator == '-') {
				topOperatorPriority = 1;
			} else if(topOperator == '*' || topOperator == '/') {
				topOperatorPriority = 2;
			}
			if(topOperatorPriority < newOperatorPriority) {
				operatorStack.push(topOperator);
				break;
			} else {
				postfix.append(topOperator);
			}
		}
		operatorStack.push(newOperator);
	}
	
	private static void convert(StringBuilder postfix, Deque<Character> operatorStack) {
		while(!operatorStack.isEmpty()) {
			char topOperator = operatorStack.pop();
			if(topOperator == '(') {
				break;
			} else {
				postfix.append(topOperator);
			}
		}
	}
}
