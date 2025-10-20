package aq.gym.contests.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DetermineIfTwoStringsAreClose {

//	https://leetcode.com/problems/determine-if-two-strings-are-close
	public static void main(String[] args) {
		String word1 = "aaabbbbccddeeeeefffff";
		String word2 = "aaaaabbcccdddeeeeffff";
		System.out.println(new DetermineIfTwoStringsAreClose().closeStrings(word1, word2));
	}

    public boolean closeStrings(String word1, String word2) {
        if(!isEqualWordsLength(word1, word2)) return false;
        if(!isEqualSetOfLetters(word1, word2)) return false;
        if(!isEqualCountsOfLetters(word1, word2)) return false;
        return true;
    }
    
    private boolean isEqualWordsLength(String word1, String word2) {
    	return word1.length() == word2.length();
    }
    
    private boolean isEqualSetOfLetters(String word1, String word2) {
    	Set<Character> lettersSetOfWord1 = new HashSet<>();
    	Set<Character> lettersSetOfWord2 = new HashSet<>();
    	for(int i = 0; i < word1.length(); i++) {
    		lettersSetOfWord1.add(word1.charAt(i));
    		lettersSetOfWord2.add(word2.charAt(i));
    	}
    	return lettersSetOfWord1.equals(lettersSetOfWord2);
    }
    
    private boolean isEqualCountsOfLetters(String word1, String word2) {
    	Map<Character, Integer> lettersMapOfWord1 = new HashMap<>();
    	Map<Character, Integer> lettersMapOfWord2 = new HashMap<>();
    	for(int i = 0; i < word1.length(); i++) {
    		lettersMapOfWord1.compute(word1.charAt(i), (k, v) -> (v == null) ? 1 : v + 1);
    		lettersMapOfWord2.compute(word2.charAt(i), (k, v) -> (v == null) ? 1 : v + 1);
    	}
    	List<Integer> lettersListOfWord1 = new ArrayList<>(lettersMapOfWord1.values());
    	List<Integer> lettersListOfWord2 = new ArrayList<>(lettersMapOfWord2.values());
    	Collections.sort(lettersListOfWord1);
    	Collections.sort(lettersListOfWord2);
    	return lettersListOfWord1.equals(lettersListOfWord2);
    }
}
