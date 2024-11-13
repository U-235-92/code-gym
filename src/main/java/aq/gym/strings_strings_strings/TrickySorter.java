package aq.gym.strings_strings_strings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrickySorter {

	public static void main(String[] args) {
		sortAllWordsStartsVowelAndSortByFirsConsonantLetter("acbd, ghgks ikogd");
		sortAllWordsByDescLengthAndAscVowelLetters("The quick brown fox jumps over the lazy dog");
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
		System.out.println();
	}
	
	private static void sortAllWordsByDescLengthAndAscVowelLetters(String text) {
		String[] words = text.split("\\s+|\\p{Punct}+\\s*");
		Pattern vowelPattern = Pattern.compile("[aeiouAEIOU]");
		Comparator<String> numberOfvowelComparator = (s1, s2) -> {
			int numberOfVowelsS1 = 0;
			int numberOfVowelsS2 = 0;
			Matcher vowelMatcher = vowelPattern.matcher(s1);
			while(vowelMatcher.find()) 
				numberOfVowelsS1++;
			vowelMatcher = vowelMatcher.reset(s2);
			while(vowelMatcher.find()) 
				numberOfVowelsS2++;
			return Integer.compare(numberOfVowelsS1, numberOfVowelsS2);
		};
		Arrays.stream(words)
			.sorted(Comparator
					.comparing(String::length)
					.reversed()
					.thenComparing(numberOfvowelComparator))
			.forEach(word -> System.out.print(word + " "));
		System.out.println();
	}
}
