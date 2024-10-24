package aq.gym.strings_strings_strings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsonantVowelLettersStatistic {

	public static void main(String[] args) throws IOException {
		String text = Files.lines(Paths.get("src/main/java/aq/gym/strings_strings_strings/word-counter-text.txt"))
			.reduce((s1, s2) -> String.join(s1, s2)).get();
		System.out.println(getStatistic(text));
	}

	private static Map<String, Long> getStatistic(String text) {
		final String CONSONAT = "Consonant";
		final String VOWEL = "Vowel";
		Map<String, Long> statistic = Arrays.stream(text.split(""))
				.filter(letter -> letter.matches("[a-zA-Z]"))
				.collect(Collectors.partitioningBy(letter -> letter.matches("[aeiouAEIOU]"), Collectors.counting()))
				.entrySet()
				.stream()
				.collect(Collectors.toMap(entry -> {
					if(entry.getKey())
						return VOWEL;
					else
						return CONSONAT;
				}, Map.Entry::getValue));
		return statistic;
	}
}
