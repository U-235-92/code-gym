package aq.gym.contests.array;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {

	public static void main(String[] args) {
		int[] nums = {1,2,3,5};
		int len = new RemoveDuplicatesFromSortedArray().removeDuplicates(nums);
		System.out.println(len);
	}

	public int removeDuplicates(int[] nums) {
		if(nums.length == 0) {
			return 0;
		}
        int length = 1, insertPos = 1;
        for(int i = 1; i < nums.length; i++) {
        	if(nums[i] != nums[i - 1]) {
        		nums[insertPos] = nums[i];
        		length++;
        		insertPos++; 
        	}
        }
        System.out.println(Arrays.toString(nums));
        return length;
    }
}
