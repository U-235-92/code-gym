package aq.gym.numbers_numbers_numbers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AverageNumberLength {

	public static void main(String[] args) {
		Integer[] numbers = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 555, 0, 999, 81, 92, 888, 129, 5, 73, 4, 56, 5, 4224, 4223, 5, 5885, 123, 345, 222, 8, 85, 59, 99, 127};
		printNumbersWhichMinAverageLength(numbers);
	}
	
	public static void printNumbersWhichMinAverageLength(Integer[] numbers) {
		System.out.print("Numbers its length < ");
		Map<Integer, Integer> numberLengthMap = new HashMap<Integer, Integer>();
		for(int number : numbers) {
			numberLengthMap.put(number, ("" + number).length());
		}
		int avrageNumberLength = numberLengthMap
				.values()
				.stream()
				.reduce(0, (a, b) -> a + b) / numberLengthMap.size();
		System.out.print(avrageNumberLength + ": ");
		Arrays
			.stream(numbers)
			.filter(number -> ("" + number).length() < avrageNumberLength)
			.forEach(number -> System.out.print(number + " "));
		System.out.println();
	}
}
