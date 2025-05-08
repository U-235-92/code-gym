package aq.gym.contests.string;

import java.util.Arrays;

public class LongestCommonPrefix {

	public String longestCommonPrefix(String[] strings) {
		if (strings == null || strings.length == 0) 
			return "";
		if(strings.length == 1) 
			return strings[0];
		String left = longestCommonPrefix(Arrays.copyOf(strings, strings.length / 2));
		String right = longestCommonPrefix(Arrays.copyOfRange(strings, strings.length / 2, strings.length));
		return getPrefix(left, right);
	}
	
	public String getPrefix(String left, String right) {
		int min = Integer.min(left.length(), right.length());
		for(int i = 0; i < min; i++) {
			if(left.charAt(i) != right.charAt(i))
				return left.substring(0, i);
		}
		return left.substring(0, min);
	}
}
