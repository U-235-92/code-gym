package aq.gym.contests.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CombinationSum {

//	https://leetcode.com/problems/combination-sum/description/
	public static void main(String[] args) {
		int[] candidates = {2,3,6,7};
		int target = 7;
//		int[] candidates = {2,3,5};
//		int target = 8;
//		int[] candidates = {2};
//		int target = 1;
		List<List<Integer>> combinations = new CombinationSum().combinationSum(candidates, target);
		combinations.forEach(System.out::println);
	}

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	Set<List<Integer>> combinations = new HashSet<>();
    	makeCombinations(combinations, new ArrayList<Integer>(), candidates, target, 0);
    	return combinations.stream().collect(Collectors.toList());
    }
    
    private void makeCombinations(Set<List<Integer>> combinations, List<Integer> combination, int[] candidates, int target, int sum) {
    	if(sum == target) {
    		List<Integer> tmp = new ArrayList<Integer>(combination);
    		tmp.sort(Integer::compareTo);
    		combinations.add(tmp);
    		return;
    	}
    	if(sum > target) {
    		return;
    	}
    	for(int i = 0; i < candidates.length; i++) {
    		int candidate = candidates[i];
    		combination.add(candidate);
    		makeCombinations(combinations, combination, candidates, target, sum + candidate);
    		combination.removeLast();
    	}
    }
}
