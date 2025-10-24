package aq.gym.contests.string;

public class RegularExpressionMatching {

//	https://leetcode.com/problems/regular-expression-matching/
	public static void main(String[] args) {
//		String s = "b", p = "a*a"; // f
//		String s = "aa", p = "a"; // f
//		String s = "b", p = "a*"; // f
//		String s = "b", p = "ab"; // f
//		String s = "ab", p = "a.*"; // t
//		String s = "ab", p = ".*"; // t
//		String s = "aab", p = "c*aa*b"; // t
//		String s = "a", p = "ab*a"; // f ?
//		String s = "aab", p = "c*a*b"; // t
//		String s = "aaa", p = "ab*ac*a"; // t
//		String s = "mississippi", p = "mis*is*p*."; // f
//		String s = "aaa", p = "a*a"; // t
		String s = "ab", p = ".*c"; // f
		System.out.println(new RegularExpressionMatching().isMatch(s, p));
	}

    public boolean isMatch(String s, String p) {
    	if(p.isEmpty()) return s.equals(p);
    	boolean isFirstLetterCompareOrDot = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
    	if(p.length() >= 2 && p.charAt(1) == '*') {
    		return isMatch(s, p.substring(2)) || (isFirstLetterCompareOrDot && isMatch(s.substring(1), p));
    	} else {
    		return isFirstLetterCompareOrDot && isMatch(s.substring(1), p.substring(1));
    	}
    }
}
