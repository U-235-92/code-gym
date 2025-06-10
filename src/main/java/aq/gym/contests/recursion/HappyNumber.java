package aq.gym.contests.recursion;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

	public static void main(String[] args) {
		int n = 18;
		System.out.println(new HappyNumber().isHappy(n));
	}

    public boolean isHappy(int n) {
    	try {
    		Set<Integer> set = new HashSet<>();
    		return testHappy(n, set);
    	} catch(StackOverflowError e) {
    		return false;
    	}
    }
    
    private boolean testHappy(int n, Set<Integer> duplicates) {
    	int happy = 0;
    	while(n != 0) {
    		happy += (int) Math.pow(n % 10, 2);
    		n = n / 10;
    	}
    	if(happy == 1) {
    		return true;
    	}
    	if(!duplicates.contains(happy)) {    		
    		duplicates.add(happy);
    		return testHappy(happy, duplicates);
    	} else {
    		return false;
    	}
    }
}
