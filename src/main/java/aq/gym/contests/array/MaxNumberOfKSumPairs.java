package aq.gym.contests.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class MaxNumberOfKSumPairs {

//	https://leetcode.com/problems/max-number-of-k-sum-pairs/description
	public static void main(String[] args) {
//		int[] nums = {3,1,3,4,3};
//		int k = 6;
//		int[] nums = {29,26,81,70,75,4,48,38,22,10,51,62,17,50,7,7,24,61,54,44,30,29,66,83,6,45,24,49,42,31,10,6,88,48,34,10,54,56,80,41,19};
//		int k = 12;
//		int[] nums = {4,5,7,2,8,20,3,5,20};
//		int k = 5;
//		int[] nums = {1,2,3,4};
//		int k = 5;
//		System.out.println(new MaxNumberOfKSumPairs().maxOperations(nums, k));
		test();
	}
	
    public int maxOperations(int[] nums, int k) {
    	return bruteForceWay(nums, k);
    }
    
    private static void test() {
    	while(true) {
    		int k = 5, limit = (int) Math.pow(10, 4);
    		int[] nums = IntStream.generate(() -> new Random().nextInt(100)).limit(limit).toArray();
        	MaxNumberOfKSumPairs task = new MaxNumberOfKSumPairs();
        	int bruteForce = task.bruteForceWay(nums, k);
        	int hashMap = task.hashMapWay(nums, k);
        	int twoPointers = task.twoPointersWay(nums, k);
        	if(bruteForce != hashMap || bruteForce != twoPointers) {
        		System.out.println("W/A");
        		System.out.printf("BF: %d, HM: %d, TP: %d%n", bruteForce, hashMap, twoPointers);
        		System.out.println(Arrays.toString(nums));
        		break;
        	} else {
        		System.out.println("OK");
        	}
    	}
    }
    
    @SuppressWarnings("unused")
	private int hashMapWay(int[] nums, int k) {
    	int count = 0;
    	Map<Integer, Integer> freqs = new HashMap<>();
    	for(int num : nums) {
    		freqs.compute(num, (key, value) -> (value == null) ? 1 : value + 1);
    	}
    	for(int i = 0; i < nums.length; i++) {
    		int num = nums[i];
    		int substract = k - num;
    		if(freqs.containsKey(substract)) {
    			if(freqs.get(substract) == 1) {
    				freqs.remove(substract);
    			} else {
    				freqs.put(substract, freqs.get(substract) - 1);
    			}
    			if(freqs.containsKey(num)) {    				
    				count++;
    				if(freqs.get(num) == 1) {
    					freqs.remove(num);
    				} else {
    					freqs.put(num, freqs.get(num) - 1);
    				}
    			}
    		}
    	}
    	return count;
    }
    
    private int bruteForceWay(int[] numbers, int k) {
    	boolean[] deleted = new boolean[numbers.length];
    	int count = 0;
    	for(int i = 0; i < numbers.length; i++) {
    		int a = numbers[i];
    		for(int j = i + 1; j < numbers.length; j++) {
    			int b = numbers[j];
    			if(a + b == k) {
    				if(deleted[i] == false && deleted[j] == false) {
    					count++;
    					deleted[i] = true; deleted[j] = true;
    				}
    			}
    		}
    	}
    	return count;
    }
    
    private int twoPointersWay(int[] nums, int k) {
    	int i = 0, j = nums.length - 1, count = 0;
    	Arrays.sort(nums);
    	while(i < j) {
    		int sum = nums[i] + nums[j];
    		if(sum == k) {
    			i++; j--;
    			count++; 
    		} else if(sum > k){
    			j--;
    		} else {
    			i++;
    		}
    	}
    	return count;
    }
}
