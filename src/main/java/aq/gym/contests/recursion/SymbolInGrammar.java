package aq.gym.contests.recursion;

import java.util.HashMap;
import java.util.Map;

public class SymbolInGrammar {

	public static void main(String[] args) {
		int n = 30, k = 8;
		System.out.println(new SymbolInGrammar().kthGrammar(n, k));
	}

	public int kthGrammar(int n, int k) {
		return 1 - grammar(n, k);
	}
	
	private int grammar(int n, int k) {
		if(n == 0) 
			return 0;
		int lineLength = (int) (Math.pow(2, n - 1));
		if(k > lineLength / 2) 
			return 1 - grammar(n - 1, k - (lineLength / 2));
		else 
			return grammar(n - 1, k);
	}
	
    public int kthGrammarBruteForce(int n, int k) {
    	if(n == 1) {
    		return 0;
    	} else if(n == 2) {
    		if(k == 1) {
    			return 0;
    		} else {
    			return 1;
    		}
    	}
    	Map<Integer, StringBuilder> cache = new HashMap<>();
    	cache.put(2, new StringBuilder("01"));
    	grammarBruteForce(n, 3, cache);
    	cache.forEach((key, val) -> System.out.printf("%-2d: %s%n", key, val));
    	return cache.get(n).toString().toCharArray()[k - 1] - '0';
    }
    
    private void grammarBruteForce(int n, int cur, Map<Integer, StringBuilder> cache) {
    	if(cur > n) {
    		return;
    	}
    	char[] remain = cache.get(cur - 1).toString().toCharArray();
    	replace(remain);
    	StringBuilder sb = new StringBuilder(cache.get(cur - 1));
    	sb.append(remain);
    	cache.put(cur, sb);
		grammarBruteForce(n, cur + 1, cache);
    }
    
    private void replace(char[] remain) {
    	int i = 0, j = remain.length - 1;
    	while(i <= j) {
    		if(remain[i] == '1') {
    			remain[i] = '0';
    		} else {
    			remain[i] = '1';
    		}
    		if(remain[j] == '1') {
    			remain[j] = '0';
    		} else {
    			remain[j] = '1';
    		}
    		i++; j--;
    	}
    }
}
