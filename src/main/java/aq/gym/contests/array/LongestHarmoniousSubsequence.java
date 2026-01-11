package aq.gym.contests.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class LongestHarmoniousSubsequence {

//	https://leetcode.com/problems/longest-harmonious-subsequence/
	public static void main(String[] args) {
//		int[] nums = {1,3,2,2,5,2,3,7};
//		int[] nums = {1,2,3,4};
//		int[] nums = {2,1,1,1};
//		int[] nums = {1,1,1};
//		int[] nums = {4,3,2,1};
//		System.out.println(new LongestHarmoniousSubsequence().findLHS(nums));
		test();
	}

	private static void test() {
		LongestHarmoniousSubsequence lhs = new LongestHarmoniousSubsequence();
		while(true) {
			int min = (int) Math.pow(-10, 9), max = (int) Math.pow(10, 9), limit = 2 * (int) Math.pow(10, 4);
			int[] nums = IntStream.generate(() -> min + (int) (Math.random() * (max - min) + 1)).limit(limit).toArray();
			int bf = lhs.bruteforce(nums);
			int mp = lhs.map(nums);
			if(bf == mp) {
				System.out.println("OK");
			} else {
				System.out.println("W/A");
				System.out.println(Arrays.toString(nums));
				System.out.println("BF = " + bf + " MP = " + mp);
				break;
			}
		}
	}
	
    public int findLHS(int[] nums) {
        return map(nums);
    }
    
    private int bruteforce(int[] nums) {
    	PriorityQueue<List<Integer>> pq = new PriorityQueue<>((l1, l2) -> Integer.compare(l2.size(), l1.size()));
    	for(int i = 0; i < nums.length; i++) {
    		int ith = nums[i];
    		int great = ith + 1, low = ith - 1;
    		List<Integer> lowerSuccessors = new ArrayList<>();
    		List<Integer> greaterSuccessors = new ArrayList<>();
    		for(int j = 0; j < nums.length; j++) {
    			if(i != j) {
    				int jth = nums[j];
    				if(jth == great) {
    					if(greaterSuccessors.isEmpty()) {
    						greaterSuccessors.add(ith);
    						greaterSuccessors.add(jth);
    					} else {
    						greaterSuccessors.add(jth);
    					}
    				}
    				if(jth == low) {
    					if(lowerSuccessors.isEmpty()) {
    						lowerSuccessors.add(ith);
    						lowerSuccessors.add(jth);
    					} else {
    						lowerSuccessors.add(jth);
    					}
    				}
    				if(jth == ith) {
    					if(!lowerSuccessors.isEmpty()) lowerSuccessors.add(jth);
    					if(!greaterSuccessors.isEmpty()) greaterSuccessors.add(jth);
    				}
    			}
    		}
    		pq.offer(lowerSuccessors);
    		pq.offer(greaterSuccessors);
    	}
    	return pq.poll().size();
    }
    
    @SuppressWarnings("unused")
	private int map(int[] nums) {
    	Map<Integer, Integer> map = new TreeMap<>();
    	for(int i = 0; i < nums.length; i++) {
    		int cNum = nums[i];
    		map.compute(cNum, (k, v) -> {
    			if(v == null) return 1;
    			else return v + 1;
    		});
    	}
    	if(map.size() == 1) return 0;
    	int lhs = 0;
    	List<Map.Entry<Integer, Integer>> eList = new ArrayList<>(map.entrySet());
    	for(int i = 0; i < eList.size() - 1; i++) {
    		if(eList.get(i + 1).getKey() - eList.get(i).getKey() == 1) {    			
    			int currFreq = eList.get(i).getValue();
    			int nextFreq = eList.get(i + 1).getValue();
    			if(currFreq + nextFreq > lhs) {
    				lhs = currFreq + nextFreq;
    			}
    		}
    	}
    	return lhs;
    }
}
