package aq.gym.contests.binary_search;

import java.util.Arrays;

public class KeepMultiplyingFoundValuesByTwo {

//	https://leetcode.com/problems/keep-multiplying-found-values-by-two/
	public static void main(String[] args) {
		int[] nums = {2,7,9};
		int original = 4;
		System.out.println(new KeepMultiplyingFoundValuesByTwo().findFinalValue(nums, original));
	}

    public int findFinalValue(int[] nums, int original) {
    	Arrays.sort(nums);
        while(true) {
        	if(Arrays.binarySearch(nums, original) >= 0) {
        		original *= 2;
        	} else {
        		break;
        	}
        }
        return original;
    }
}
