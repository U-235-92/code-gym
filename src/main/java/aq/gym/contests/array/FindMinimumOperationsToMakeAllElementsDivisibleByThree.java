package aq.gym.contests.array;

public class FindMinimumOperationsToMakeAllElementsDivisibleByThree {

//	https://leetcode.com/problems/find-minimum-operations-to-make-all-elements-divisible-by-three
	public static void main(String[] args) {
//		int[] nums = {1,2,3,4};
//		int[] nums = {3,6,9};
		int[] nums = {7};
		System.out.println(new FindMinimumOperationsToMakeAllElementsDivisibleByThree().minimumOperations(nums));
	}

    public int minimumOperations(int[] nums) {
        int numOperations = 0;
        for(int i = 0; i < nums.length; i++) {
        	if(nums[i] == 1) {
        		numOperations++;
        	} else {
        		if(nums[i] % 3 != 0) {
        			numOperations++;
        		}
        	}
        }
        return numOperations;
    }
}
