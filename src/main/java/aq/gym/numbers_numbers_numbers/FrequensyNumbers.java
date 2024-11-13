package aq.gym.numbers_numbers_numbers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FrequensyNumbers {
	
	public static void main(String[] args) {
		Integer[] numbers = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 555, 0, 999, 81, 92, 888, 129, 5, 73, 4, 56, 5, 4224, 4223, 5, 5885, 123, 345, 222, 8, 85, 59, 99, 127};
		printSortedNumbersByFrequensy(numbers);
	}

	public static void printSortedNumbersByFrequensy(Integer[] numbers) {
		System.out.print("Sorted numbers by frequensy: ");
		Map<Integer, Integer> frequencyMap = new LinkedHashMap<>();
		for(int number : numbers) {
			if(frequencyMap.containsKey(number)) {				
				frequencyMap.computeIfPresent(number, (key, value) -> value = value + 1);
			} else {
				frequencyMap.computeIfAbsent(number, num -> 1);
			}
		}
		List<Entry<Integer, Integer>> entryList = new ArrayList<Map.Entry<Integer, Integer>>(frequencyMap.entrySet());
		Comparator<Entry<Integer, Integer>> valueComparator = Map.Entry.comparingByValue();
		entryList.sort(valueComparator.reversed());
		entryList.forEach(entry -> {
			for(int i = 0; i < entry.getValue(); i++) {
				System.out.print(entry.getKey() + " ");
			}
		});
		System.out.println();
	}
}
