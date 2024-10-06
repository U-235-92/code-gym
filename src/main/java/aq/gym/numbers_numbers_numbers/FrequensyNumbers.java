package aq.gym.numbers_numbers_numbers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FrequensyNumbers {

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
