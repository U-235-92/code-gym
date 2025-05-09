package aq.gym.contests.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new TwoSum().twoSum(new int[] {3, 3}, 6)));
	}
	
	 public int[] twoSum(int[] nums, int target) {
		 int result[] = new int[2];
		 Map<Integer, Integer> numbersMap = new HashMap<>();
		 for(int i = 0; i < nums.length; i++) {
			 numbersMap.put(nums[i], i);
		 }
		 for(int i = 0; i < nums.length; i++) {
			 int num = nums[i];
			 int guess = target - num;
			 if(numbersMap.containsKey(guess) && numbersMap.get(guess) != i) {
				 result[0] = i;
				 result[1] = numbersMap.get(guess);
			 }
		 }
		 return result;
	 }
}
