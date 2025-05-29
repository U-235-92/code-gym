package aq.gym.contests.recursion;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {

	public static void main(String[] args) {
		int n = 2;
		System.out.println(new ClimbingStairs().climbStairs(n));
	}
	
    public int climbStairs(int n) {
    	Map<Integer, Integer> cache = new HashMap<>();
        return getStepCombinationsNumber(n + 1, cache);
    }
    
    private int getStepCombinationsNumber(int step, Map<Integer, Integer> cache) {
    	if(cache.containsKey(step)) {
    		return cache.get(step);
    	} else {    		
    		if(step == 1 || step == 2) {
    			cache.put(step, 1);
    			return 1;
    		}
    		int oneStepsNumber = getStepCombinationsNumber(step - 1, cache);
    		int twoStepsNumber = getStepCombinationsNumber(step - 2, cache);
    		cache.put(step, oneStepsNumber + twoStepsNumber);
    		return oneStepsNumber + twoStepsNumber;
    	}
    }
}
