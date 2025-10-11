package aq.gym.contests.array;

public class SingleNumber {

//	https://leetcode.com/problems/single-number/description/
	public static void main(String[] args) {
		int[] nums = {1,2,2,3,3};
		System.out.println(new SingleNumber().singleNumber(nums));
	}

    public int singleNumber(int[] nums) {
    	int singleton = 0;
    	for(int i = 0; i < nums.length; i++) {
    		singleton ^= nums[i];
    	}
    	return singleton;
    }
}
