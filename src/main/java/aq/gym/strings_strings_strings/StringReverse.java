package aq.gym.strings_strings_strings;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringReverse {

	public static void main(String[] args) {
		System.out.println(reverseByStream("The quick brown fox jumps over the lazy dog"));
		System.out.println(reverseByRecursion(0, "forest"));
		System.out.println(reverseByStringBuilder("Hello, world"));
	}

	private static String reverseByStringBuilder(String str) {
		return new StringBuilder(str).reverse().toString();
	}
	
	private static String reverseByRecursion(int index, String str) {
		String result = "";
		if(index != str.length() - 1) {
			result = reverseByRecursion(index + 1, str);
		}
		result = result + str.charAt(index);
		return result;
	}
	
	private static String reverseByStream(String str) {
		List<String> words = Stream.of(str.split(" "))
				.map(word -> new StringBuilder(word))
				.map(sb -> sb.reverse().toString())
				.collect(Collectors.toList());
		String result = "";		
		for(int i = words.size() - 1; i >= 0; i--) {
			result += words.get(i) + " ";
		}
		return result.trim();
	}
}
