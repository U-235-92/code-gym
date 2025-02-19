package aq.gym.numbers_numbers_numbers;

import java.util.Arrays;

public class ZeroNumberShifter {

	public static void main(String[] args) {
		int[] numbers = {8, 0, 5, 0, 12};
		shiftZeroToRight(numbers);
//		shiftZeroToLeft(numbers);
		System.out.println(Arrays.toString(numbers));
	}
	
	public static void shiftZeroToRight(int[] numbers) {
		int zeroIdx = 0;
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] != 0) {
				if(zeroIdx != i) {
					numbers[zeroIdx] = numbers[i];
					numbers[i] = 0;
				}
				zeroIdx++;
			}
		}
	}
	
	public static void shiftZeroToLeft(int[] numbers) {
		int zeroIdx = numbers.length - 1;
		for(int i = numbers.length - 1; i >= 0; i--) {
			if(numbers[i] != 0) {
				if(zeroIdx != i) {
					numbers[zeroIdx] = numbers[i];
					numbers[i] = 0;
				}
				zeroIdx--;
			}
		}
	}
}
