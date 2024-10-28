package aq.gym.strings_strings_strings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter {

	public static void main(String[] args) throws IOException {
		Stream<String> wordsStreamCollectors = wordsStream(new File("src/main/java/aq/gym/strings_strings_strings/word-counter-text.txt"));
		Stream<String> wordsStreamMap = wordsStream(new File("src/main/java/aq/gym/strings_strings_strings/word-counter-text.txt"));
		Map<String, Long> statCollectorsWay = wordStatisticCollectorsWay(wordsStreamCollectors);
		statCollectorsWay = sort(statCollectorsWay, Map.Entry.comparingByValue());
		System.out.println(statCollectorsWay);
		Map<String, Long> statMapWay = wordStatisticMapWay(wordsStreamMap); 
		statMapWay = sort(statMapWay, Map.Entry.comparingByValue());
		System.out.println(statMapWay);
//		System.out.println(trickyWordCounter("Ежик ёжик ежик иод, йод!"));
	}
	
//	private static Map<String, Long> trickyWordCounter(String text) {
//		Map<String, Long> map = Arrays.stream(text.split("\\s+"))
//				.map(str -> str.replaceAll("\\p{Punct}", ""))
//				.filter(word -> word.matches("[�-���-�]+"))
//				.collect(Collectors.groupingBy(word -> {
//					Pattern pattern� = Pattern.compile("[�]");
//					Matcher matcher� = pattern�.matcher(word);
//					if(matcher�.find()) {
//						word = word.replaceAll("�", "�");
//					}
//					Pattern pattern� = Pattern.compile("[�]");
//					Matcher matcher� = pattern�.matcher(word);
//					if(matcher�.find()) {
//						word = word.replaceAll("�", "�");
//					}
//					Pattern pattern� = Pattern.compile("[�]");
//					Matcher matcher� = pattern�.matcher(word);
//					if(matcher�.find()) {
//						word = word.replaceAll("�", "�");
//					}
//					Pattern pattern� = Pattern.compile("[�]");
//					Matcher matcher� = pattern�.matcher(word);
//					if(matcher�.find()) {
//						word = word.replaceAll("�", "�");
//					}
//					return word;
//				}, Collectors.counting()));
//		return map;
//	}
	
	private static Stream<String> wordsStream(File file) throws IOException {
		return Files
				.lines(file.toPath())
				.flatMap(str -> Arrays.stream(str.split("\\s+")))
				.map(str -> str.replaceAll("\\p{Punct}", ""))
				.map(String::toLowerCase)
				.filter(str -> !str.equals(""));
	}
	
	private static Map<String, Long> wordStatisticCollectorsWay(Stream<String> wordStream) {
		return wordStream.collect(Collectors.groupingBy(word -> word, Collectors.counting()));
	}
	
	private static Map<String, Long> wordStatisticMapWay(Stream<String> wordStream) {
		Map<String, Long> statstic = new HashMap<String, Long>();
		wordStream.forEach(word -> {
			statstic.putIfAbsent(word, 0L);
			statstic.computeIfPresent(word, (k, v) -> v = v + 1);
		});
		return statstic;
	}

	private static Map<String, Long> sort(Map<String, Long> statistic, Comparator<Map.Entry<String, Long>> comparator) {
//		Alternative way
		/*
		 * Map<String, Long> sortedMap = new LinkedHashMap<String, Long>();
		 * List<Map.Entry<String, Long>> sortedList = statistic .entrySet() .stream()
		 * .sorted(comparator).collect(Collectors.toList());
		 * sortedList.stream().forEach(entry -> sortedMap.put(entry.getKey(),
		 * entry.getValue())); return sortedMap;
		 */
		return statistic
				.entrySet()
				.stream()
				.sorted(comparator)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (value1, value2) -> value1, LinkedHashMap::new));
	}
}
