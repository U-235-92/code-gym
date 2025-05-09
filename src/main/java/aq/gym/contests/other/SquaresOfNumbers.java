package aq.gym.contests.other;

import java.util.Arrays;

public class SquaresOfNumbers {

	public static void main(String[] args) {
		int[] arr = {-7,-3,2,3,11};
		System.out.println(Arrays.toString(new SquaresOfNumbers().sortedSquares2(arr)));
	}

	public int[] sortedSquares(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
        	nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }
	
	public int[] sortedSquares2(int[] nums) {
		if(nums.length == 1) {
			nums[0] = nums[0] * nums[0];
			return nums;
		} else {			
			int[] sorted = sort(nums);
			return sorted;
		}
    }
	
	public int[] sort(int[] nums) {
		if(nums.length < 2) {
			nums[0] = nums[0] * nums[0];
			return nums;
		}
		int[] left = sort(Arrays.copyOf(nums, nums.length / 2));
		int[] right = sort(Arrays.copyOfRange(nums, nums.length / 2, nums.length));
		return regularMerge(left, right);
	}
	
	private int[] regularMerge(int left[], int[] right) {
		int[] squares = new int[left.length + right.length];
		int idxL = 0, idxR = 0, idxS = 0;
		while(idxL < left.length && idxR < right.length) {
			if(left[idxL] < right[idxR]) {
				squares[idxS++] = left[idxL];
				idxL++;
			} else {
				squares[idxS++] = right[idxR];
				idxR++;
			}
		}
		while(idxL < left.length) {
			squares[idxS] = left[idxL];
			idxL++; idxS++;
		}
		while(idxR < right.length) {
			squares[idxS] = right[idxR];
			idxR++; idxS++;
		}
		return squares;
	}
}
