package aq.gym.contests.hash_table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueNumberOfOccurrences {

//	https://leetcode.com/problems/unique-number-of-occurrences/description
	public static void main(String[] args) {
		int[] arr = {-3,0,1,-3,1,1,1,-3,10,0};
		System.out.println(new UniqueNumberOfOccurrences().uniqueOccurrences(arr));
	}

    public boolean uniqueOccurrences(int[] arr) {
    	Map<Integer, Integer> occurences = new HashMap<>();
    	for(int number : arr) {
    		occurences.compute(number, (k, v) -> (v == null) ? 1 : v + 1);
    	}
    	Set<Integer> occurrencesDuplicates = new HashSet<>();
    	for(int number : occurences.values()) {
    		if(!occurrencesDuplicates.add(number)) {
    			return false;
    		}
    	}
    	return true;
    }
}
