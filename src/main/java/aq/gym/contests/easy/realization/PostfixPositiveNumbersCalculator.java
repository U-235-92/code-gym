package aq.gym.contests.easy.realization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class PostfixPositiveNumbersCalculator {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line = br.readLine();
			int result = calculate(line);
			System.out.println(result);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static int calculate(String line) {
		Deque<Integer> values = new ArrayDeque<>();
		Deque<Character> operators = new ArrayDeque<>();
		for(int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			if(ch == ' ') {
				continue;
			}
			if(Character.isDigit(ch)) {
				int number = 0;
				while(i < line.length() && Character.isDigit(line.charAt(i))) {
					number = (number * 10) + (line.charAt(i) - '0');
					i++;
				}
				values.push(number);
				i--;
			} else {
				if(operators.isEmpty()) {
					operators.push(ch);
				} else {
					if(ch == '(') {
						operators.push(ch);
					} else if(ch == ')') {
						while(!operators.isEmpty() && operators.peek() != '(') {
							int c = doOperation(values.pop(), values.pop(), operators.pop());
							values.push(c);
						}
						operators.pop();
					} else if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {
						if(getPriority(ch) <= getPriority(operators.peek())) {
							int c = doOperation(values.pop(), values.pop(), operators.pop());
							values.push(c);
						}
						operators.push(ch);
					}
				}
			}
		}
		while(!operators.isEmpty()) {
			int c = doOperation(values.pop(), values.pop(), operators.pop());
			values.push(c);
		}
		return values.pop();
	}
	
	private static int getPriority(char operator) {
		if (operator == '+' || operator == '-')
			return 1;
		if (operator == '*' || operator == '/')
			return 2;
		return 0;
	}
	
	private static int doOperation(int a, int b, char operator) {
		switch(operator) {
			case '-':
				return a - b;
			case '+':
				return a + b;
			case '*':
				return a * b;
			case '/':
				return a / b;
		}
		return 0;
	}
}
