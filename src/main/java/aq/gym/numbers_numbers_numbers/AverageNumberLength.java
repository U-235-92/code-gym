package aq.gym.numbers_numbers_numbers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AverageNumberLength {

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
