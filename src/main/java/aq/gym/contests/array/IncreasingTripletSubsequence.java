package aq.gym.contests.array;

import java.util.Random;
import java.util.stream.IntStream;

public class IncreasingTripletSubsequence {

//	https://leetcode.com/problems/increasing-triplet-subsequence
	public static void main(String[] args) {
		int limit = 5 * (int) Math.pow(10, 5);
		int[] nums = IntStream.generate(() -> new Random().nextInt(1000)).limit(limit).toArray();
//		int[] nums = {1,2,3,4,5,6,7,8,9}; // T
//		int[] nums = {5,4,3,2,1}; // F
//		int[] nums = {2,1,5,0,4,6}; // T
//		int[] nums = {7,9,4,8,5,7,6}; // T
//		int[] nums = {7,9,4,3,5,2,8}; // T 
//		int[] nums = {2,2,2,8,3,5,2,8}; // T  
//		int[] nums = {7,9,9,3,2,5,2,8}; // T
//		int[] nums = {1,2,3}; // T
//		int[] nums = {3,2,1}; // F
//		int[] nums = {1,1,1}; // F
//		int[] nums = {1,1}; // F
//		int[] nums = {1}; // F
//		int[] nums = {-1,0,1,2}; // T
//		int[] nums = {1,1,2,1}; // F
//		int[] nums = {20,100,10,12,5,13}; // T 
//		int[] nums = {0,4,2,1,0,-1,-3}; // F
//		int[] nums = {1,5,0,4,1,3}; // T
//		int[] nums = {9,10,5,11,10,9,8}; // T 
//		int[] nums = {1,0,0,10,0,0,100000000}; // T
		System.out.println(new IncreasingTripletSubsequence().increasingTriplet(nums));
	}

	public boolean increasingTriplet(int[] nums) {
		if(nums.length < 3) {
    		return false;
    	}
    	if(nums.length == 3) {
    		return nums[0] < nums[1] && nums[1] < nums[2] && nums[0] < nums[2];
    	}
    	int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE, c = Integer.MAX_VALUE;
        boolean isTriplet = false;
        for(int p = 0; p < nums.length; p++) {
        	if(nums[p] <= a) {
        		a = nums[p];
			} else if (nums[p] <= b) {
        		b = nums[p];
        	} else if(nums[p] > a && nums[p] > b) {
        		c = nums[p];
        		System.out.printf("{%d<%d<%d}%n", a, b, c);
        		isTriplet = true;
        		break;
        	}
        }
        return isTriplet;
	}
}
