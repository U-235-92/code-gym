package aq.gym.contests.backtracking;

import java.util.ArrayList;
import java.util.List;

public class CountNumbersWithUniqueDigits {

//	https://leetcode.com/problems/count-numbers-with-unique-digits/
	public static void main(String[] args) {
		int n = 2;
		System.out.println(new CountNumbersWithUniqueDigits().countNumbersWithUniqueDigits(n));
	}

    public int countNumbersWithUniqueDigits(int n) {
    	int[] uniqueNumbersCount = {0};
        int[] numbers = {1,2,3,4,5,6,7,8,9,0};
        boolean[] inUseNumbers = new boolean[10];
        backtrack(n, 0, new ArrayList<Integer>(), numbers, uniqueNumbersCount, inUseNumbers);
        final int missedZero = 1;
        return uniqueNumbersCount[0] + missedZero;
    }
    
    private void backtrack(final int limit, int start, List<Integer> combination, int[] numbers, int[] uniqueNumbersCount, boolean[] inUseNumbers) {
    	if(!combination.isEmpty()) {    		
    		if(combination.getFirst() != 0) {
//    			System.out.println(combination);
    			uniqueNumbersCount[0]++;
    		}
    	}
    	if(combination.size() >= limit) {
    		return;
    	}
    	for(int i = start; i < numbers.length; i++) {
    		if(inUseNumbers[i]) {
    			continue;
    		}
    		combination.add(numbers[i]);
    		inUseNumbers[i] = true;
    		backtrack(limit, start, combination, numbers, uniqueNumbersCount, inUseNumbers);
    		combination.removeLast();
    		inUseNumbers[i] = false;
    	}
    }
}
