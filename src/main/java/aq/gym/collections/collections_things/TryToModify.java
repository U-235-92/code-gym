package aq.gym.collections.collections_things;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TryToModify {

	public static void main(String[] args) {
		youCanModifyKeyValuePairOfMapButItHasSomeImportantDetails();
		youCantSortMapByEntrySet();
		youCanModifyMapByEntrySet();
		youCantModdifyListCreatedByOfMethod();
		youCanModdifyListCreatedByAsListMethod();
	}
	
	private static void youCanModifyKeyValuePairOfMapButItHasSomeImportantDetails() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Orange", 10);
		map.put("Apple", 20);
		map.put("Melon", 30);
		System.out.print("Before modify: " + map);
		try {			
//			map.keySet().add("Strawberry"); // You can't do that
//			map.values().add(50); // You can't do that
			map.keySet().remove("Orange"); // But remove is valid operation - removes [k, v] pair 
			map.values().remove(20); // Is valid. It removes [k, v] pair
			System.out.println(" After modify: " + map);
		} catch(UnsupportedOperationException e) {
			System.err.println("I can't modify map's keyset!");
		}
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
