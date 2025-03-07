package aq.gym.contests.easy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TrickyWordCounter {

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new File("src/main/java/aq/gym/contests/easy/ticky_word_counter_input.txt"));
			StringBuilder sb = new StringBuilder();
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				sb.append(line);
				sb.append(" ");
			}
			count(sb.toString());
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void count(String text) {
		StringBuilder result = new StringBuilder();
		Map<String, Integer> wordCounterMap = new HashMap<>();
		String[] words = Arrays.stream(text.split("\\s+")).toArray(String[]::new);
		for(String word : words) {
			if(wordCounterMap.get(word) == null) {				
				wordCounterMap.put(word, 0);
				result.append(0);
				result.append(" ");
			} else {
				int count = wordCounterMap.get(word) + 1;
				wordCounterMap.put(word, count);
				result.append(count);
				result.append(" ");
			}
		}
		System.out.println(result.toString().trim());
	}
}
