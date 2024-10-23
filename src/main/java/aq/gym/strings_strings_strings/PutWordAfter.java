package aq.gym.strings_strings_strings;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PutWordAfter {

	public static void main(String[] args) {
		String text = "Hello, world of bees!";
		String result = putWordAfterOtherEqualsSubstring(text, "lo", "beautiful");
		System.out.println(result);
	}

	private static String putWordAfterOtherEqualsSubstring(String text, String ending, String addition) {
		String[] words = Arrays.stream(text.split("\\s+")).map(String::trim).toArray(String[]::new);
		StringBuilder result = new StringBuilder();
		for(String word : words) {
			Pattern wordPattern = Pattern.compile("[a-zA-Z]+");
			Matcher wordMatcher = wordPattern.matcher(word);
			if(wordMatcher.find()) {
				String pureWord = wordMatcher.group();
				if(pureWord.endsWith(ending)) {
					addAdditionWord(result, word, addition);
				} else {
					addWord(result, word);
				}
			}
		}
		return result.toString().trim();
	}
	
	private static void addAdditionWord(StringBuilder result, String word, String addition) {
		result.append(word);
		result.append(" ");
		result.append(addition);
		result.append(" ");
	}
	
	private static void addWord(StringBuilder result, String word) {
		result.append(word);
		result.append(" ");
	}
}
