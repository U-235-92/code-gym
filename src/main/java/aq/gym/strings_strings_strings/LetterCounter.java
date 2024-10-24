package aq.gym.strings_strings_strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LetterCounter {

	public static void main(String[] args) {
		System.out.println(getLetterStatisticStreamWay("Hello, world!"));
		System.out.println(getLetterStatisticNoStreamWay("Hello, world!"));
	}

	private static Map<String, Long> getLetterStatisticStreamWay(String text) {
		Map<String, Long> statistic = Arrays.stream(text.split(""))
				.filter(letter -> letter.matches("[a-zA-Z]+"))
				.collect(Collectors.groupingBy(letter -> letter, Collectors.counting()));
		return statistic;
	}
	
	private static Map<String, Long> getLetterStatisticNoStreamWay(String text) {
		Map<String, Long> statistic = new HashMap<String, Long>();
		for(String letter : text.split("")) {
			if(letter.matches("[a-zA-Z]+")) {
				if(statistic.get(letter) == null) {					
					statistic.put(letter, 1L);
				} else {					
					long count = statistic.get(letter);
					count++;
					statistic.put(letter, count);
				}
			}
		}
		return statistic;
	}
}
