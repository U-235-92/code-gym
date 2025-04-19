package aq.gym.contests.easy;

import java.util.Arrays;

public class RunningSum {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new RunningSum().runningSum(new int[]{3,1,2,10,1})));
	}

	public int[] runningSum(int[] nums) {
		int[] result = new int[nums.length];
		result[0] = nums[0];
		for(int i = 1; i < nums.length; i++) {
			result[i] = result[i - 1] + nums[i];
		}
		return result;
    }
}
