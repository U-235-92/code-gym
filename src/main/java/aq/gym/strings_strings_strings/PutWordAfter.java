package aq.gym.strings_strings_strings;

import java.util.Arrays;

public class PutWordAfter {

	public static void main(String[] args) {
		String text = ",Hello, world of bees!";
		String result = putWordAfterOtherEqualsSubstring(text, "lo", "beautiful");
		System.out.println(result);
	}

	private static String putWordAfterOtherEqualsSubstring(String text, String substring, String addition) {
		String[] words = Arrays.stream(text.split("\\s+")).map(String::trim).toArray(String[]::new);
		StringBuilder result = new StringBuilder();
		for(String word : words) {
			
		}
		return result.toString().trim();
	}
}
