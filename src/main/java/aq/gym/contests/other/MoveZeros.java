package aq.gym.contests.other;

import java.util.Arrays;

public class MoveZeros {

	public static void main(String[] args) {
		int[] nums = {0};
		new MoveZeros().moveZeroes(nums);
	}
	
	public void moveZeroes(int[] nums) {
		int window = 0;
        for(int i = 0; i < nums.length; i++) {
        	if(nums[i] == 0) {
        		window++;
        	} else {
        		int tmp = nums[i];
        		nums[i] = 0;
        		nums[i - window] = tmp;
        	}
        }
        System.out.println(Arrays.toString(nums));
    }
}
