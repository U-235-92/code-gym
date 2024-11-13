package aq.gym.collections.collections_things;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TryToModify {

	public static void main(String[] args) {
		youCantSortMapByEntrySet();
		youCanModifyMapByEntrySet();
		youCantModdifyListCreatedByOfMethod();
		youCanModdifyListCreatedByAsListMethod();
	}
	
	private static void youCantSortMapByEntrySet() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Orange", 10);
		map.put("Apple", 20);
		map.put("Melon", 30);
		System.out.print("Before sort: " + map);
		map.entrySet().stream().sorted(Map.Entry.comparingByKey());
		System.out.println(" After sort: " + map);
	}
	
	private static void youCanModifyMapByEntrySet() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Orange", 10);
		map.put("Apple", 20);
		map.put("Melon", 30);
		map.entrySet().removeIf(entry -> entry.getValue() == 30);
		System.out.println(map);
	}
	
	private static void youCantModdifyListCreatedByOfMethod() {
		List<String> list = List.of("A", "B", "C");
		try {			
			list.set(1, "X");
		} catch(UnsupportedOperationException e) {
			System.err.println("I can't modify this!");
		}
	}
	
	private static void youCanModdifyListCreatedByAsListMethod() {
		List<String> list = Arrays.asList("A", "B", "C");
		list.set(1, "X");
		System.out.println(list);
	}
	
}
