package aq.gym.strings_strings_strings;

import java.util.Arrays;
import java.util.Set;

public class ConsonantVowel {

	public static void main(String[] args) {
		System.out.println(getVowelThenConsonantLettersOfWord("pneumonoultramicroscopicsilicovolcanoconiosis"));
	}

	private static String getVowelThenConsonantLettersOfWord(String word) {
		Set<String> consonants = Set.of("b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z");
		Set<String> vowels = Set.of("a", "e", "i", "o", "u");
		String consonantSubstring = Arrays.stream(word.split(""))
				.flatMap(letter -> consonants.stream().filter(consonant -> consonant.equals(letter)))
				.sorted()
				.reduce("", (a, b) -> a + b);
		String vowelSubstring = Arrays.stream(word.split(""))
				.flatMap(letter -> vowels.stream().filter(vowel -> vowel.equals(letter)))
				.sorted()
				.reduce("", (a, b) -> a + b);
		String result = vowelSubstring + consonantSubstring;
		return result;
	}
}
