package aq.gym.contests.hash_table;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {

//	https://leetcode.com/problems/isomorphic-strings/description/
	public static void main(String[] args) {
//		String s = "egg", t = "add"; // t
//		String s = "foo", t = "bar"; // f
//		String s = "paper", t = "title"; // t
//		String s = "bbbaaaba", t = "aaabbbba"; // f
		String s = "12", t = "gg"; // f
		System.out.println(new IsomorphicStrings().isIsomorphic(s, t));
	}

    public boolean isIsomorphic(String s, String t) {
    	Map<Character, Character> sIsomorhs = new HashMap<>();
    	Map<Character, Character> tIsomorhs = new HashMap<>();
    	char[] sChars = s.toCharArray();
    	char[] tChars = t.toCharArray();
    	for(int i = 0; i < s.length(); i++) {
    		char tCurrChar = tChars[i];
    		char sCurrChar = sChars[i];
    		if(tIsomorhs.get(tCurrChar) == null && sIsomorhs.get(sCurrChar) == null) {
    			sIsomorhs.put(sCurrChar, tCurrChar);
    			tIsomorhs.put(tCurrChar, sCurrChar);
    		} else {
    			if(!sIsomorhs.containsKey(sCurrChar)) return false;
    			if(!tIsomorhs.containsKey(tCurrChar)) return false;
    			char tChar = sIsomorhs.get(sCurrChar);
    			char sChar = tIsomorhs.get(tCurrChar);
    			if(tChar != tCurrChar && sCurrChar != sChar) return false;
    		}
    	}
    	return true;
    }
}
