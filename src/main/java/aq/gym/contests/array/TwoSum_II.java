package aq.gym.contests.array;

import java.util.Arrays;

public class TwoSum_II {

	public static void main(String[] args) {
		int[] numbers = {-1,0};
		int target = -1;
		int[] sumIndexes = new TwoSum_II().twoSum(numbers, target);
		System.out.println(Arrays.toString(sumIndexes));
	}

	public int[] twoSum(int[] numbers, int target) {
		int[] indexes = new int[2];
		int i = 0, j = numbers.length - 1;
		while(i < j) {
			if(numbers[i] + numbers[j] > target) {
				j--;
			} else if(numbers[i] + numbers[j] < target) {
				i++;
			} else {
				indexes[0] = i + 1;
				indexes[1] = j + 1;
				break;
			}
		}
		return indexes;
	}
}
