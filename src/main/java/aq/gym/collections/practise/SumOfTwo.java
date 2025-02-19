package aq.gym.collections.practise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class SumOfTwo {

	public static void main(String[] args) {
		int[] nums = {-1, 2, 2, 3, 8};
		int[] indexes = getIndexesOfTwoNumbers(nums, 5);
		System.out.println(Arrays.toString(indexes));
	}
	
	public static int[] getIndexesOfTwoNumbers(int[] nums, int sum) {
		Map<Integer, Integer> numberToIndexMap = new HashMap<Integer, Integer>();
		for(int i = 0; i < nums.length; i++) {
			int remain = sum - nums[i];
			if(numberToIndexMap.containsKey(remain)) {
				return new int[] {numberToIndexMap.get(remain), i};
			} else {
				numberToIndexMap.put(nums[i], i);
			}
		}
		throw new NoSuchElementException("There is no any numbers which fit the sum!");
	}
}
