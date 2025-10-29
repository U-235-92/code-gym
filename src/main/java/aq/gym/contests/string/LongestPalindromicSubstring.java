package aq.gym.contests.string;

import java.util.HashSet;
import java.util.Set;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LongestPalindromicSubstring {

//	https://leetcode.com/problems/longest-palindromic-substring/
	public static void main(String[] args) {
//		int limit = 1000;
//		IntSupplier supplier = () -> {
//			int c1 = 65 + (int) (Math.random() * (90 - 65) + 1);
//			int c2 = 48 + (int) (Math.random() * (57 - 48) + 1);
//			int c3 = 97 + (int) (Math.random() * (122 - 97) + 1);
//			int[] codes = {c1, c2, c3};
//			return codes[(int) (Math.random() * 3)];
//		};
//		String s = IntStream
//				.generate(supplier)
//				.limit(limit)
//				.mapToObj(num -> Character.valueOf((char) num) + "")
//				.collect(Collectors.joining());
//		System.out.println(s);
//		String s = "babad";
//		String s = "babcd";
//		String s = "cbbd";
//		String s = "b";
//		String s = "xabax";
//		String s = "aaaaabaaaaaa";
		String s = "bb";
		System.out.println(new LongestPalindromicSubstring().longestPalindrome(s));
//		test();
	}
	
    public String longestPalindrome(String s) {
    	return expandFromCenters(s);
    }
    
    private String expandFromCenters(String s) {
    	int[] ans = new int[] { 0, 0 };
        for (int i = 0; i < s.length(); i++) {
            int oddLength = expand(i, i, s);
            if (oddLength > ans[1] - ans[0] + 1) {
                int dist = oddLength / 2;
                ans[0] = i - dist;
                ans[1] = i + dist;
            }
            int evenLength = expand(i, i + 1, s);
            if (evenLength > ans[1] - ans[0] + 1) {
                int dist = (evenLength / 2) - 1;
                ans[0] = i - dist;
                ans[1] = i + 1 + dist;
            }
        }
        int i = ans[0];
        int j = ans[1];
        return s.substring(i, j + 1);
    }
    
    private int expand(int i, int j, String s) {
        int left = i;
        int right = j;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
    
    private String bruteForceWay(String s) {
    	if(s.length() == 1) return s;
    	Set<String> palindromeCash = new HashSet<>();
    	Set<String> noPalindromeCash = new HashSet<>();
    	String longestPalindrome = "";
    	int longestPalindromeLength = 0;
        for(int i = 0; i < s.length(); i++) {
        	for(int j = i + 1; j <= s.length(); j++) {
        		String substr = s.substring(i, j);
        		if(noPalindromeCash.contains(substr)) continue;
        		if(palindromeCash.contains(substr)) continue;
        		if(isPalindrome(substr) && substr.length() > longestPalindromeLength) {
        			palindromeCash.add(longestPalindrome);
        			longestPalindrome = substr;
        			longestPalindromeLength = longestPalindrome.length();
        		} else {
        			noPalindromeCash.add(substr);
        		}
        	}
        }
        return longestPalindrome;
    }
    
    private boolean isPalindrome(String str) {
    	StringBuilder orig = new StringBuilder(str);
    	StringBuilder copy = new StringBuilder(str);
    	return orig.toString().equals(copy.reverse().toString());
    }
}
