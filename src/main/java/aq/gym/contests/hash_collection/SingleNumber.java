package aq.gym.contests.hash_collection;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {

	public static void main(String[] args) {
		int[] nums = {0,4,6,0,6};
		System.out.println(new SingleNumber().singleNumber(nums));
	}

    public int singleNumber(int[] nums) {
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int num : nums) {
    		map.compute(num, (k, v) -> (v == null) ? 1 : v + 1);
    	}
    	for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
    		if(entry.getValue() == 1)
    			return entry.getKey();
    	}
    	return -1;
    }
}
