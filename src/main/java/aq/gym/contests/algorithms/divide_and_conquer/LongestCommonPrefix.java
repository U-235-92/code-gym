package aq.gym.contests.algorithms.divide_and_conquer;

import java.util.Arrays;

public class LongestCommonPrefix {

	public static void main(String[] args) {
		System.out.println(getLongestCommonPrefix(new String[]{"flower","flow","flight"}));
	}
	
	private static String getLongestCommonPrefix(String[] strings) {
		if (strings == null || strings.length == 0) 
			return "";
		if(strings.length == 1) 
			return strings[0];
		String left = getLongestCommonPrefix(Arrays.copyOf(strings, strings.length / 2));
		String right = getLongestCommonPrefix(Arrays.copyOfRange(strings, strings.length / 2, strings.length));
		return getPrefix(left, right);
	}
	
	private static String getPrefix(String left, String right) {
		int min = Integer.min(left.length(), right.length());
		for(int i = 0; i < min; i++) {
			if(left.charAt(i) != right.charAt(i))
				return left.substring(0, i);
		}
		return left.substring(0, min);
	}
}
