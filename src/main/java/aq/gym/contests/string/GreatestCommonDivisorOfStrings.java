package aq.gym.contests.string;

public class GreatestCommonDivisorOfStrings {

//	https://leetcode.com/problems/greatest-common-divisor-of-strings
	public static void main(String[] args) {
//		String str1 = "TAUXXTAUXXTAUXXTAUXXTAUXX", str2 = "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX";
//		String str1 = "OBCNOOBCNOOBCNOOBCNOOBCNOOBCNOOBCNOOBCNO", str2 = "OBCNOOBCNOOBCNOOBCNOOBCNOOBCNOOBCNOOBCNOOBCNOOBCNOOBCNOOBCNOOBCNO";
//		String str1 = "ABCABC", str2 = "ABC";
//		String str1 = "ABABAB", str2 = "ABAB";
//		String str1 = "LEET", str2 = "CODE";
		String str1 = "AAA", str2 = "AAAA";
//		String str1 = "ABAB", str2 = "AB";
//		String str1 = "ABCABC", str2 = "ABAB";
//		String str1 = "ABCDEF", str2 = "ABC";
//		String str1 = "ABABABAB", str2 = "ABAB";
		System.out.println(new GreatestCommonDivisorOfStrings().gcdOfStrings(str1, str2));
	}

    public String gcdOfStrings(String str1, String str2) {
    	if(!(str1 + str2).equals(str2 + str1)) {
    		return "";
    	}
    	String greater, lesser;
    	if(str1.length() >= str2.length()) {
    		greater = str1;
    		lesser = str2;
    	} else {
    		greater = str2;
    		lesser = str1;
    	} 
    	int greatestCommonSubstringLength = getGreatestCommonSubstringtLength(greater.length(), lesser.length());
    	StringBuilder greatestCommonSubstring = new StringBuilder("");
    	greatestCommonSubstring.append(lesser.substring(0, greatestCommonSubstringLength));
    	return greatestCommonSubstring.toString();
    }
    
    private int getGreatestCommonSubstringtLength(int a, int b) {
    	if(b == 0) {
    		return a;
    	}
    	return getGreatestCommonSubstringtLength(b, a % b);
    }
}
