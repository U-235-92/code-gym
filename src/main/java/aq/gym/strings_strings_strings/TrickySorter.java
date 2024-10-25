package aq.gym.strings_strings_strings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrickySorter {

	public static void main(String[] args) {
		String text = "acbd, ghgks ikogd";
		sortAllWordsStartsVowelAndSortByFirsConsonantLetter(text);
	}

	private static void sortAllWordsStartsVowelAndSortByFirsConsonantLetter(String text) {
		String[] words = text.split("\\s+|\\p{Punct}+\\s*");
		Pattern vowelPattern = Pattern.compile("[aeiouAEIOU]");
		Arrays.stream(words)
			.map(word -> {
				Matcher vowelMatcher = vowelPattern.matcher(word);
				if(vowelMatcher.find()) {
					Pattern oddsPattern = Pattern.compile("[bcdfghjklmnpqrstvwxyz]");
					Matcher oddsMatcher = oddsPattern.matcher(word);
					if(oddsMatcher.find()) {
						int i = oddsMatcher.start();
						String firstOdd = word.charAt(i) + "";
						word = Arrays.stream(word.split(""))
								.sorted(Comparator.comparing(letter -> letter.compareTo(firstOdd)))
								.reduce(String::concat)
								.get();
					}
				}
				return word;
			})
			.forEach(word -> System.out.print(word + " "));
	}
}
