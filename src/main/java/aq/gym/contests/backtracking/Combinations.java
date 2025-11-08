package aq.gym.contests.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Combinations {

//	https://leetcode.com/problems/combinations/description/
	public static void main(String[] args) {
//		int n = 4, k = 2;
//		int n = 1, k = 1;
		int n = 20, k = 10;
		System.out.println(new Combinations().combine(n, k));
	}

    public List<List<Integer>> combine(int n, int k) {
    	Set<List<Integer>> combinations = new HashSet<>();
    	List<Integer> numbers = Stream.iterate(1, num -> num + 1).limit(n).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    	combine(k, 0, combinations, new HashSet<Integer>(), numbers);
    	return combinations.stream().toList();
    }
    
    private void combine(final int k, int start, Set<List<Integer>> combinations, Set<Integer> combination, List<Integer> numbers) {
    	if(combination.size() == k) {
    		combinations.add(new ArrayList<Integer>(combination));
    		return;
    	}
    	for(int i = start; i < numbers.size(); i++) {
    		int number = numbers.get(i);
    		if(combination.contains(number)) {
    			continue;
    		} else {
    			combination.add(number);
        		combine(k, i + 1, combinations, combination, numbers);
        		combination.remove(number);
    		}
    	}
    }
}
