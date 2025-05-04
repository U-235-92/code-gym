package aq.gym.contests.easy.array;

public class LargestNumberAtLeastTwiceOfOthers {

	public static void main(String[] args) {
		int[] nums = {1,2,4};
		int dominantIndex = new LargestNumberAtLeastTwiceOfOthers().dominantIndex(nums);
		System.out.println(dominantIndex);
	}

	public int dominantIndex(int[] nums) {
		int maxElement = Integer.MIN_VALUE;
		int maxElementIdx = -1;
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] > maxElement) {
				maxElementIdx = i;
				maxElement = nums[i];
			}
		}
		for(int i = nums.length - 1; i >= 0; i--) {
			if(nums[i] * 2 > maxElement && i != maxElementIdx) {
				return -1;
			} 
		}
		return maxElementIdx;
	}
}
