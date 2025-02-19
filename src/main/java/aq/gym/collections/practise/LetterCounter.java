package aq.gym.collections.practise;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class LetterCounter {

	public static void main(String[] args) {
		String str = "The quick brown fox jumps over the lazy dog";
		Map<String, Long> statistic = count(str);
		statistic.entrySet().stream().forEach(System.out::println);
	}

	public static Map<String, Long> count(String str) {
		return Arrays.stream(str.split(""))
				.map(String::toLowerCase)
				.filter(letter -> letter.matches("[a-z]"))
				.collect(Collectors.groupingBy(letter -> letter, Collectors.counting()));
	}
}
