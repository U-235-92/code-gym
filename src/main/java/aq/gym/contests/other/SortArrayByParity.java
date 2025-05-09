package aq.gym.contests.other;

import java.util.Arrays;

public class SortArrayByParity {

	public static void main(String[] args) {
		int[] nums = {3,1,5,4,5,6,7,8,2,4,5,6,7,8,9,0,1,2,3,4,5,5,4,4,3,2,4,5,6};
//		int[] nums = {3,4};
 		int[] result = new SortArrayByParity().sortArrayByParity(nums);
 		System.out.println(Arrays.toString(result));
	}

	public int[] sortArrayByParity(int[] nums) {
		if(nums.length == 1) {
			return nums;
		} else if(nums.length == 2) {
			if(nums[0] % 2 == 0 && nums[1] % 2 == 0) {
				return nums;
			} else if(nums[1] % 2 == 0 && nums[0] % 2 != 0) {
				int tmp = nums[0];
				nums[0] = nums[1];
				nums[1] = tmp;
				return nums;
			} else {
				return nums;
			}
		} else {			
			int left = 0, right = nums.length - 1;
			sort(nums, left, right);
			return nums;
		}
    }
	
	private void sort(int[] nums, int from, int to) {
		int leftIdx = from, rightIdx = to;
		while(leftIdx <= rightIdx) {
			while(nums[leftIdx] % 2 == 0) {
				leftIdx++;
				if(leftIdx >= nums.length) {
					return;
				}
			}
			while(nums[rightIdx] % 2 != 0) {
				rightIdx--;
				if(rightIdx <= 0) {
					return;
				}
			}
			if(leftIdx <= rightIdx) {
				int tmp = nums[leftIdx];
				nums[leftIdx] = nums[rightIdx];
				nums[rightIdx] = tmp;
				leftIdx++; rightIdx--;
			}
		}
	}
}
