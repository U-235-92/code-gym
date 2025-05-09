package aq.gym.contests.other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SynonymDictionary {

	public static void main(String[] args) {
		findSynonymByMapAndParallelStream();
	}
	
//	The fastest calculation 
	private static void findSynonymByMapAndParallelStream() {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.valueOf(scanner.nextLine());
		Map<String, String> map1 = new HashMap<>();
		while(n-- > 0) {
			String[] pairLine = scanner.nextLine().split("\\s");
			map1.put(pairLine[0], pairLine[1]);
		}
		String word = scanner.nextLine();
		String synonym = map1.get(word);
		if(synonym == null) {
			synonym = map1.entrySet().parallelStream()
					.dropWhile(e -> !e.getValue().equals(word)).map(e -> e.getKey()).findAny().get();
		}
		System.out.println(synonym);
		scanner.close();
	}
	
	@SuppressWarnings("unused")
	private static void findSynonymByArray() {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.valueOf(scanner.nextLine()), i = 0;
		String[] synonyms = new String[n];
		while(n-- > 0) {
			String[] pairLine = scanner.nextLine().split("\\s");
			synonyms[i++] = pairLine[0] + "" + pairLine[1];
		}
		String word = scanner.nextLine();
		String synonym = Arrays.stream(synonyms).parallel().dropWhile(s -> !s.contains(word)).findFirst().get();
		int idx = synonym.indexOf(word);
		if(idx == 0) {
			idx = word.length();
			synonym = synonym.substring(idx);
		} else {
			idx = 0;
			synonym = synonym.substring(idx, synonym.length() - word.length());
		}
		System.out.println(synonym);
		scanner.close();
	}
	
	@SuppressWarnings("unused")
	private static void findSynonymByMap() {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.valueOf(scanner.nextLine());
		Map<String, String> map = new HashMap<>();
		while(n-- > 0) {
			String[] pairLine = scanner.nextLine().split("\\s");
			map.put(pairLine[0], pairLine[1]);
			map.put(pairLine[1], pairLine[0]);
		}
		String word = scanner.nextLine();
		System.out.println(map.get(word));
		scanner.close();
	}
}
