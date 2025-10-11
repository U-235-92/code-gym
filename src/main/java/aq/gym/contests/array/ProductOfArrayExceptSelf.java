package aq.gym.contests.array;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ProductOfArrayExceptSelf {

//	https://leetcode.com/problems/product-of-array-except-self
	public static void main(String[] args) {
//		int[] nums = IntStream.generate(() -> new Random().nextInt(10)).limit(100000).toArray();
//		System.out.println(Arrays.toString(nums));
//		int[] nums = {2,3,5,0};
//		System.out.println(Arrays.toString(new ProductOfArrayExceptSelf().productExceptSelf(nums)));
//		System.out.println(Arrays.toString(new ProductOfArrayExceptSelf().bruteForce(nums)));
		test();
	}

	public static void test() {
		while(true) {
			int[] nums = IntStream.generate(() -> new Random().nextInt(100000)).limit(10).toArray();
			int[] on1Case = new ProductOfArrayExceptSelf().productExceptSelf(nums);
			int[] on2Case = new ProductOfArrayExceptSelf().bruteForce(nums);
			if(!Arrays.equals(on1Case, on2Case)) {
				System.out.println("W/A:");
				System.out.println("O(N^2) = " + Arrays.toString(on2Case));
				System.out.println("O(N) = " + Arrays.toString(on1Case));
				break;
			} else {
				System.out.println("OK");
			}
		}
		
	}
	
	public int[] bruteForce(int[] nums) {
		int[] answer = new int[nums.length];
		for(int i = 0; i < nums.length; i++) {
			int product = 1;
			for(int j = 0; j < nums.length; j++) {
				if(i != j) {
					product *= nums[j];
				}
			}
			answer[i] = product;
		}
		return answer;
	}
	
    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        int[] prefixes = new int[nums.length];
        int[] suffixes = new int[nums.length];
        int prefix = 1;
        for(int i = 0; i < nums.length; i++) {
        	if(i == 0) {        		
        		prefixes[i] = 1; 
        	} else {
        		int num = nums[i - 1];
        		prefixes[i] = prefix * num; 
        		prefix = prefixes[i];
        	}
        }
        int suffix = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
        	if(i == nums.length - 1) {
        		suffixes[i] = 1;
        	} else {
        		int num = nums[i + 1];
        		suffixes[i] = suffix * num;
        		suffix = suffixes[i];
        	}
        }
        for(int i = 0; i < nums.length; i++) {
        	answer[i] = suffixes[i] * prefixes[i];
        }
        return answer;
    }
}
