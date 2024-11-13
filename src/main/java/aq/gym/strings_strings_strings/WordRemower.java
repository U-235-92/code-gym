package aq.gym.strings_strings_strings;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordRemower {

	public static void main(String[] args) {
		String text = "Hello, beautiful universe of funny cats!";
		String result = removeWordsByLength(text, 5);
		System.out.println(text);
		System.out.println(result);
	}

	private static String removeWordsByLength(String text, int wordLength) {
		String result = Arrays.stream(text.split("\\s+"))
				.filter(word -> {
					Pattern pattern = Pattern.compile("\\w+");
					Matcher matcher = pattern.matcher(word);
					if(matcher.find()) {
						if(matcher.group().length() == wordLength) 
							return false;
					}
					return true;
				})
				.reduce((s1, s2) -> String.join(" ", s1, s2)).get();
		return result;
	}
}
