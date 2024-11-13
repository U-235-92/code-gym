package aq.gym.strings_strings_strings;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReplaceLetterNumberValueOfAlphabet {

	public static void main(String[] args) {
		String text = "The quick brown fox jumps over a lazy dog!";
		String replced = replace(text);
		print(text, replced);
	}

	private static void print(String text, String replced) {
		Arrays.stream(text.split("")).forEach(letter -> System.out.printf("%-2s", letter));
		System.out.println();
		Arrays.stream(replced.split("")).forEach(letter -> System.out.printf("%-2s", letter));
	}

	private static String replace(String text) {
		String replacedStringByNumberFromlAlphabet = Arrays.stream(text.split(""))
				.map(letter -> {
					if(isAlphabetLetter(letter)) 
						return numberValueOfLetter(letter);
					else
						return letter;
				}).reduce("", String::concat);
		return replacedStringByNumberFromlAlphabet;
	}
	
	private static boolean isAlphabetLetter(String letter) {
		return alphabet().get(letter.toLowerCase()) != null;
	}
	
	private static String numberValueOfLetter(String letter) {
		return alphabet().get(letter.toLowerCase()) + "";
	}
	
	private static Map<String, Integer> alphabet() {
		final int MIN_LOWER_CASE_CHAR_CODE = 97;
		final int MAX_LOWER_CASE_CHAR_CODE = 122;
		Map<String, Integer> alphabet = IntStream
				.rangeClosed(MIN_LOWER_CASE_CHAR_CODE, MAX_LOWER_CASE_CHAR_CODE)
				.boxed()
				.collect(Collectors.toMap(number -> (char) number.intValue() + "", number -> number.intValue() - 96));
		return alphabet;
	}
}
