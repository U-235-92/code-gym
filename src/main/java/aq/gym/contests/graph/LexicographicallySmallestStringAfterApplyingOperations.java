package aq.gym.contests.graph;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class LexicographicallySmallestStringAfterApplyingOperations {

//	https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations/description
	public static void main(String[] args) {
		LexicographicallySmallestStringAfterApplyingOperations task = new LexicographicallySmallestStringAfterApplyingOperations();
		String s = "1627837459392748390207372189293948575674834892392020329838475466155235634674575893932984756393947858";
		int a = 9, b = 99;
//		String s = "5525";
//		int a = 9, b = 2;
//		String s = "0011";
//		int a = 4, b = 2;
		System.out.println(task.findLexSmallestString(s, a, b));
	}
	
    public String findLexSmallestString(String s, int a, int b) {
    	String smallest = initSmallest();
    	Queue<String> process = new ArrayDeque<>();
    	Set<String> visited = new HashSet<>();
    	process.offer(s);
    	while(!process.isEmpty()) {
    		String number = process.poll();
    		if(!visited.contains(number)) {
    			String sum = getSum(number, a);
    			String shift = getShift(number, b);
    			smallest = getSmallest(sum, shift, smallest);
    			processLines(sum, shift, visited, process);
    			visited.add(number);
    		}
    	}
    	return getResult(s, smallest);
    }
    
    private String initSmallest() {
    	StringBuilder init = new StringBuilder();
    	for(int i = 0; i < 100; i++) {
    		init.append(9);
    	}
    	return init.toString();
    }
    
    private String getSum(String str, int adder) {
    	char[] chrs = str.toCharArray();
    	for(int i = chrs.length - 1; i >= 0; i -= 2) {
    		int number = Integer.valueOf(String.valueOf(chrs[i]));
    		int sum = 0;
    		if((number + adder) / 10 == 0) {
    			sum = number + adder;
    			setChar(chrs, i, sum);
    		} else {
    			sum = (number + adder) % 10;
    			setChar(chrs, i, sum);
    		}
    	}
    	return new String(chrs);
    }
    
    private void setChar(char[] chrs, int idx, int sum) {
    	chrs[idx] = (char) (sum + 48);
    }
    
    private String getShift(String str, int shift) {
    	char[] chrs = str.toCharArray();
    	int i = 0, j = chrs.length - shift - 1;
    	int k = j + 1, m = chrs.length - 1;
    	int p = 0, q = chrs.length - 1;
    	shift(chrs, i, j);
    	shift(chrs, k, m);
    	shift(chrs, p, q);
    	return new String(chrs);
    }
    
    private void shift(char[] chrs, int i, int j) {
    	while(i < j) {
    		swap(chrs, i, j);
    		i++; j--;
    	}
    }
    
    private void swap(char[] chrs, int i, int j) {
    	char tmp = chrs[i];
		chrs[i] = chrs[j];
		chrs[j] = tmp;
    }
    
    private void processLines(String sum, String shift, Set<String> visited, Queue<String> process) {
    	if(!visited.contains(sum)) {
			process.add(sum);
		}
		if(!visited.contains(shift)) {
			process.add(shift);
		}
    }
    
    private String getSmallest(String sum, String shift, String smallest) {
    	BigInteger a = new BigInteger(sum);
    	BigInteger b = new BigInteger(shift);
    	BigInteger c = new BigInteger(smallest);
    	if(a.compareTo(b) < 0) {
    		if(a.compareTo(c) < 0) {
    			return a.toString(10);
    		} else {
    			return c.toString(10);
    		}
    	} else {
    		if(b.compareTo(c) < 0) {
    			return b.toString(10);
    		} else {
    			return c.toString(10);
    		}
    	}
    }
    
    private String getResult(String s, String smallest) {
    	if(smallest.length() != s.length()) {
    		int delta = s.length() - smallest.length();
    		StringBuilder result = new StringBuilder();
    		while(delta-- > 0) {
    			result.append(0);
    		}
    		result.append(smallest);
    		return result.toString();
    	} else {    		
    		return smallest.toString();
    	}
    }
}
