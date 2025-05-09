package aq.gym.contests.array;

public class MinimumSizeSubarraySum {

	public static void main(String[] args) {
		int tareget = 7;
		int[] nums = {2,3,1,2,4,3};
		int length = new MinimumSizeSubarraySum().minSubArrayLen(tareget, nums);
		System.out.println(length);
	}

	public int minSubArrayLen(int target, int[] nums) {
		int length = Integer.MAX_VALUE;
		int left = 0, right = 0, sumWindow = 0, windowLength = 0;
		for(; right < nums.length; right++) {
			sumWindow += nums[right];
			while(sumWindow >= target) {
				windowLength = right - left + 1;
				length = Integer.min(length, windowLength);
				sumWindow -= nums[left++];
			}
		}
		return (length == Integer.MAX_VALUE) ? 0 : length;
	}
}
