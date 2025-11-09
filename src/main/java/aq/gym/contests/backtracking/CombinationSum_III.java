package aq.gym.contests.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum_III {

//	https://leetcode.com/problems/combination-sum-iii
	public static void main(String[] args) {
		int k = 3, n = 7;
		System.out.println(new CombinationSum_III().combinationSum3(k, n));
	}

    public List<List<Integer>> combinationSum3(int k, int n) {
    	Set<List<Integer>> combinations = new HashSet<>();
    	int[] sum = {0};
    	int[] numbers = {1,2,3,4,5,6,7,8,9};
    	boolean[] usedNumberIndexes = new boolean[numbers.length];
    	backtrack(combinations, new ArrayList<Integer>(), usedNumberIndexes, numbers, sum, n, k, 0);
    	return combinations.stream().toList();
    }
    
    private void backtrack(Set<List<Integer>> combinations, List<Integer> combination, boolean[] usedNumberIndexes, int[] numbers, int[] sum, int targetSum, int combinationSize, int start) {
    	if(combination.size() == combinationSize) {
    		if(sum[0] == targetSum) {
    			List<Integer> tmp = new ArrayList<Integer>(combination);
        		tmp.sort(Integer::compareTo);
        		combinations.add(tmp);
    			return;
    		}
    	}
    	for(int i = start; i < numbers.length; i++) {
    		if(usedNumberIndexes[i]) {
    			continue;
    		}
    		combination.add(numbers[i]);
    		usedNumberIndexes[i] = true;
    		sum[0] += numbers[i];
    		backtrack(combinations, combination, usedNumberIndexes, numbers, sum, targetSum, combinationSize, start);
    		combination.remove(combination.size() - 1);
    		usedNumberIndexes[i] = false;
    		sum[0] -= numbers[i];
    	}
    }
}
