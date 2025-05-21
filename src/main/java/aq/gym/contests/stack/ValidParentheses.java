package aq.gym.contests.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses {

	public static void main(String[] args) {
		String s = "(){}}{";
		System.out.println(new ValidParentheses().isValid(s));
	}

	public boolean isValid(String s) {
		if(s.length() % 2 != 0) {
			return false;
		}
		if(s.startsWith(")") || s.startsWith("]") || s.startsWith("}")) {
			return false;
		}
		char[] chrs = s.toCharArray();
		Deque<Character> stack = new ArrayDeque<>();
		for (int i = 0; i < chrs.length; i++) {
			switch (chrs[i]) {
				case '(':
				case '[':
				case '{':
					stack.push(chrs[i]);
					break;
				case ')': {
					if(!isValid(stack, '('))
						return false;
					break;
				}
				case ']': {
					if(!isValid(stack, '['))
						return false;
					break;
				}
				case '}': {
					if(!isValid(stack, '{'))
						return false;
					break;
				}
			}
		}
		return (stack.size() == 0) ? true : false;
	}
	
	private boolean isValid(Deque<Character> stack, char parentheses) {
		if(stack.isEmpty())
			return false;
		char pop = stack.pop();
		if (pop != parentheses)
			return false;
		return true;
	}
}
