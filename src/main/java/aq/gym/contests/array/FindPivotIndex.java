package aq.gym.contests.array;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FindPivotIndex {

	public static void main(String[] args) {
		int[] nums = {1,0};
		int pivotIndex = new FindPivotIndex().pivotIndexFast(nums);
		System.out.println(pivotIndex);
	}

	public int pivotIndexSlow(int[] nums) {
		if(nums.length == 1) 
			return 0;
		for(int i = 0; i < nums.length; i++) {
			if(i == 0) {
				int[] right = Arrays.copyOfRange(nums, i + 1, nums.length);
				int leftSum = 0, rightSum = IntStream.of(right).sum();
				if(leftSum == rightSum)
					return i;
			} else if(i == nums.length - 1) {
				int[] left = Arrays.copyOfRange(nums, 0, nums.length - 1);
				int leftSum = IntStream.of(left).sum(), rightSum = 0;
				if(leftSum == rightSum)
					return i;
			} else {
				int[] right = Arrays.copyOfRange(nums, i + 1, nums.length);
				int[] left = Arrays.copyOfRange(nums, 0, i);
				int leftSum = IntStream.of(left).sum(), rightSum = IntStream.of(right).sum();
				if(leftSum == rightSum)
					return i;
			}
		}
		return -1;
	}
	
	public int pivotIndexFast(int[] nums) {
		if(nums.length == 1) 
			return 0;
		int totalSumArray = 0, leftSum = 0;
		for(int num : nums) {
			totalSumArray += num;
		}
		for(int i = 0; i < nums.length; i++) {
			int rightSum = totalSumArray - nums[i] - leftSum;
			if(leftSum == rightSum) {
				return i;
			}
			leftSum += nums[i];
		}
		return -1;
	}
}
