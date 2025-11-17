package aq.gym.contests.string;

import java.util.Arrays;

public class ValidAnagram {

//	https://leetcode.com/problems/valid-anagram/description/
	public static void main(String[] args) {
		String s = "rat", t = "car";
		System.out.println(new ValidAnagram().isAnagram(s, t));
	}

    public boolean isAnagram(String s, String t) {
    	char[] sChrs = s.toCharArray();
    	char[] tChrs = t.toCharArray();
    	Arrays.sort(sChrs);
    	Arrays.sort(tChrs);
    	return Arrays.equals(sChrs, tChrs);
    }
}
