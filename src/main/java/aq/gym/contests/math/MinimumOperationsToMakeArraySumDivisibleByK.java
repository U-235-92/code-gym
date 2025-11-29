package aq.gym.contests.math;

public class MinimumOperationsToMakeArraySumDivisibleByK {

//	https://leetcode.com/problems/minimum-operations-to-make-array-sum-divisible-by-k/
	public static void main(String[] args) {
		int[] nums = {3,9,7};
		int k = 5;
		System.out.println(new MinimumOperationsToMakeArraySumDivisibleByK().minOperations(nums, k));
	}

    public int minOperations(int[] nums, int k) {
    	int sum = 0;
    	for(int num : nums) {
    		sum += num;
    	}
    	return sum % k;
    }
}
