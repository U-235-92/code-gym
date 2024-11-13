package aq.gym.strings_strings_strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnagramDetector {

	public static void main(String[] args) {
		System.out.println(isAnagramSortWay("form", "from"));
		System.out.println(isAnagramMapWay("form", "from"));
	}

	private static boolean isAnagramMapWay(String left, String right) {
		Map<Character, Integer> leftMap = letterStatistics(left);
		Map<Character, Integer> rightMap = letterStatistics(right);
		return leftMap.equals(rightMap);
	}
	
	private static Map<Character, Integer> letterStatistics(String word) {
		Map<Character, Integer> statistics = new HashMap<Character, Integer>();
		for(char ch : word.toCharArray()) {
			statistics.compute(ch, (k, v) -> {
				if(v == null) {
					return v;
				} else {
					v = v + 1;
					return v;
				}
			});
		}
		return statistics;
	}
	
	private static boolean isAnagramSortWay(String left, String right) {
		if(left.length() != right.length()) {
			return false;
		}
		char[] leftLetters = left.toCharArray();
		char[] rightLetters = right.toCharArray();
		Arrays.sort(leftLetters);
		Arrays.sort(rightLetters);
		return Arrays.equals(leftLetters, rightLetters);
	}
}
