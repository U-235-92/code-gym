package aq.gym.contests.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class BaseballGame {

//	https://leetcode.com/problems/baseball-game/description/
	public static void main(String[] args) {
		String[] ops = new String[] {"5","2","C","D","+"};
		System.out.println(new BaseballGame().calPoints(ops));
	}

    public int calPoints(String[] operations) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < operations.length; i++) {
        	String str = operations[i];
        	if(str.length() >= 2) {
        		stack.push(Integer.valueOf(str));
        	} else {
        		switch(str) {
        		case "+":
        			add(stack);
        			break;
        		case "D":
        			dwice(stack);
        			break;
        		case "C":
        			stack.pop();
        			break;
        		default:
        			stack.push(Integer.valueOf(str));
        		}
        	}
        }
        return getScore(stack);
    }
    
    private void add(Deque<Integer> stack) {
    	int a = stack.pop();
		int b = stack.pop();
		int c = a + b;
		stack.push(b);
		stack.push(a);
		stack.push(c);
    }
    
    private void dwice(Deque<Integer> stack) {
    	int a = stack.pop();
    	int b = a * 2;
		stack.push(a);
		stack.push(b);
    }
    
    private int getScore(Deque<Integer> stack) {
    	int score = 0;
    	while(!stack.isEmpty()) {
        	score += stack.pop();
        }
        return score;
    }
}
