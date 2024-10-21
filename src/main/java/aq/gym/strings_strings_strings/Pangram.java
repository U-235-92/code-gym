package aq.gym.strings_strings_strings;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Pangram {

	public static void main(String[] args) {
		String text = "The quick brown fox jumps over the lazy dog";
		final int NUMBER_OF_LETTERS = 26;
		Set<String> setLettersOfText = Arrays.stream(text.split(""))
				.filter(symbol -> !symbol.equals(" "))
				.map(String::toLowerCase)
				.collect(Collectors.toSet());
		if(setLettersOfText.size() == NUMBER_OF_LETTERS) 
			System.out.println("The text: " + "[" + text + "] " + "is pangram"); 
		else 
			System.out.println("The text: " + "[" + text + "] " + "isn't pangram"); 
	}

}
