package aq.gym.strings_strings_strings;

import java.util.regex.Pattern;

public class DeletePartOfTextBetweenSymbols {

	public static void main(String[] args) {
		String text = "Hello **It's required * , me either *to* delete* world!";
		String result = delete(text, '*', '*');
		System.out.println(result);
	}

	private static String delete(String text, char begin, char end) {
		String result = Pattern.compile("\\" + begin + ".*\\" + end)
				.splitAsStream(text)
				.map(String::trim)
				.reduce((s1, s2) -> String.join(" ", s1, s2))
				.get();
		return result;
	}
}
