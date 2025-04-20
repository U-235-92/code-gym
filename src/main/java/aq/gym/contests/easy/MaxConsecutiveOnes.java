package aq.gym.contests.easy;

public class MaxConsecutiveOnes {

	public static void main(String[] args) {
		System.out.println(new MaxConsecutiveOnes().findMaxConsecutiveOnes(new int[] {1,0,1,1,0,1}));
	}

	public int findMaxConsecutiveOnes(int[] nums) {
		int maxOnes = 0;
		int currentOnes = 0;
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == 1) {
				currentOnes++;
				maxOnes = Integer.max(maxOnes, currentOnes);
			} else {
				currentOnes = 0;
			}
		}
		return maxOnes;
    }
}
