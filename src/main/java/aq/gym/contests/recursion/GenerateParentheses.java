package aq.gym.contests.recursion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateParentheses {

//	https://leetcode.com/problems/generate-parentheses/
	public static void main(String[] args) {
		int n = 2;
		System.out.println(new GenerateParentheses().generateParenthesis(n).stream().collect(Collectors.joining("\s")));	
	}

    public List<String> generateParenthesis(int n) {
    	List<String> accumulator = new ArrayList<String>();
    	generate(n, n, "", accumulator);
    	return accumulator;
    }
    
    private void generate(int nOpen, int nClose, String combination, List<String> accumulator) {
    	if(nOpen == 0 && nClose == 0) {
    		if(isValidParenthesisCombination(combination)) {
    			accumulator.add(combination);
    			return;
    		} else {
    			return;
    		}
    	}
    	if(nOpen > 0) {
    		generate(nOpen - 1, nClose, combination + "(", accumulator);
    	}
    	if(nClose > 0) {
    		generate(nOpen, nClose - 1, combination + ")", accumulator);    		
    	}	
    }
    
    private boolean isValidParenthesisCombination(String combination) {
    	if(combination.length() % 2 != 0) {
    		return false;
    	}
    	int countValidPairs = 0;
    	Deque<String> stack = new ArrayDeque<String>();
    	for(int i = 0; i < combination.length(); i++) {
    		String incommingParenthesis = combination.charAt(i) + "";
    		if(incommingParenthesis.equals("(")) {
    			stack.push(incommingParenthesis);
    		} else {
    			if(stack.isEmpty()) {
    				return false;
    			}
    			String popedParenhesis = stack.pop();
    			if(!popedParenhesis.equals("(")) {
    				return false;
    			} else {
    				countValidPairs++;
    			}
    		}
    	}
    	return countValidPairs == combination.length() / 2;
    }
}
