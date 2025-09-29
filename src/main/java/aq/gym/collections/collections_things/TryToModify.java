package aq.gym.collections.collections_things;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TryToModify {

	public static void main(String[] args) {
		collectionViewOperations();
		youCanModifyKeyValuePairOfMapButItHasSomeImportantDetails();
		youCantSortMapByEntrySet();
		youCanModifyMapByEntrySet();
		youCantModdifyListCreatedByOfMethod();
		youCanModdifyListCreatedByAsListMethod();
	}
	
	private static void collectionViewOperations() {
		List<Integer> numbers = List.of(5, 8, 26); // Creation of unmodified collection which size is final;
//		Collections.sort(numbers); // Error: you can't modify!
//		Collections.shuffle(numbers); // Error: you can't modify!
		List<Integer> sub = numbers.subList(0, 1);
//		sub.add(3); //Error! Try to modify unmodified list
		System.out.println(sub);
//		numbers.add(55); // Error: you can't add or remove element from view;
//		numbers.set(1, 8); // Error: you can't modify element of view;
		List<Integer> numbers2 = new ArrayList<>(List.of(1, 3, 2));
		List<Integer> sublist = numbers2.subList(1, numbers2.size());
		System.out.println(sublist);
		Collections.sort(sublist);
		System.out.println(sublist);
		System.out.println(numbers2);
		sublist.add(22);
		System.out.println(sublist);
		System.out.println(numbers2);
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
