package aq.gym.contests.array;

import java.util.Arrays;

public class MakeArrayElementsToZero {

//	https://leetcode.com/problems/make-array-elements-equal-to-zero/
	public static void main(String[] args) {
//		int[] nums = {1,0,2,0,3};
		int[] nums = {2,3,4,0,4,1,0};
		System.out.println(new MakeArrayElementsToZero().countValidSelections(nums));
	}

    public int countValidSelections(int[] nums) {
    	int noZeroNumsCount = 0;
    	int countValidSelections = 0;
        for(int num : nums) {
        	if(num != 0) noZeroNumsCount++;
        }
        for(int i = 0; i < nums.length; i++) {
        	int num = nums[i];
        	if(num == 0) {
        		if(isValid(Arrays.copyOf(nums, nums.length), i, noZeroNumsCount, 1)) countValidSelections++;
        		if(isValid(Arrays.copyOf(nums, nums.length), i, noZeroNumsCount, -1)) countValidSelections++;
        	}
        }
        return countValidSelections;
    }
    
    private boolean isValid(int[] nums, int start, int noZeroNumsCount, int direction) {
    	int idx = start;
    	while(idx < nums.length && idx >= 0) {
    		if(noZeroNumsCount == 0) {
    			break;
    		}
			if(nums[idx] == 0) {
				idx += direction;
			} else {
				nums[idx]--;
				if(nums[idx] == 0) noZeroNumsCount--;
				direction = -direction;
				idx += direction;
			}
		}
    	return noZeroNumsCount == 0;
    }
}
