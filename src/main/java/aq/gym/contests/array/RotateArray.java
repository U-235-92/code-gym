package aq.gym.contests.array;

import java.util.Arrays;

public class RotateArray {

	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5};
		int k = 8;
		System.out.println(Arrays.toString(nums));
		new RotateArray().rotate(nums, k);
		System.out.println(Arrays.toString(nums));
	}

	public void rotate(int[] nums, int k) {
		k = k % nums.length;
        swap(nums, 0, nums.length - 1 - k);
        swap(nums, nums.length - k, nums.length - 1);
        swap(nums, 0, nums.length - 1);
    }
	
	private void swap(int[] nums, int from, int to) {
		int left = from, right = to;
		while(left < right) {
			int tmp = nums[left];
			nums[left] = nums[right];
			nums[right] = tmp;
			left++; right--;
		}
	}
}
