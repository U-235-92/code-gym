package aq.gym.strings_strings_strings;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordConvertor {

	public static void main(String[] args) {
		String text = "Please, draw the next figures: a circle, a triangle and an ameba!";
		String result = deleteAllNextLettersOfWordEqualsFirstLetter(text);
		System.out.println(text);
		System.out.println(result);
	}
	
	private static String deleteAllNextLettersOfWordEqualsFirstLetter(String text) {
		String[] words = text.split("\\s+");
		return Arrays.stream(words)
				.map(word -> {
					if(word.length() > 1) {
						Pattern pattern = Pattern.compile("\\w+");
						Matcher matcher = pattern.matcher(word);
						if(matcher.find()) {							
							char first = word.charAt(0);
							StringBuilder result = new StringBuilder();
							for(char c : word.substring(1).toCharArray()) {
								if(c != first) {									
									result.append(c);
								}
							}
							word = first + result.toString();
						}
						
					} 
					return word;
				})
				.reduce((s1, s2) -> String.join(" ", s1, s2))
				.get();
	}

}
