package aq.gym.contests.hash_table;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {

//	https://leetcode.com/problems/word-pattern/
	public static void main(String[] args) {
//		String pattern = "abba", s = "dog cat cat dog";
//		String pattern = "abba", s = "dog cat cat fish";
//		String pattern = "aba", s = "dog cat cat";
//		String pattern = "abc", s = "dog cat cat";
		String pattern = "abc", s = "dog cat fish";
		System.out.println(new WordPattern().wordPattern(pattern, s));
	}

    public boolean wordPattern(String pattern, String s) {
    	String[] words = s.split("\\s");
    	if(pattern.length() != words.length) {
    		return false;
    	} else {
    		Map<String, String> letterToWordMap = new HashMap<>();
    		Map<String, String> wordToLetterMap = new HashMap<>();
    		for(int i = 0; i < words.length; i++) {
    			String word = words[i];
    			String lettet = pattern.charAt(i) + "";
    			if(wordToLetterMap.isEmpty() && letterToWordMap.isEmpty()) {
    				wordToLetterMap.put(word, lettet);
    				letterToWordMap.put(lettet, word);
    			} else {
    				if(letterToWordMap.get(lettet) == null && wordToLetterMap.get(word) != null) {
    					return false;
    				} 
    				if(letterToWordMap.get(lettet) != null && wordToLetterMap.get(word) == null) {
    					return false;
    				}
    				if(letterToWordMap.get(lettet) != null && wordToLetterMap.get(word) != null) {
    					if(!letterToWordMap.get(lettet).equals(word) && !wordToLetterMap.get(word).equals(lettet)) {
    						return false;
    					}
    				}
    				wordToLetterMap.put(word, lettet);
    				letterToWordMap.put(lettet, word);
    			}
    		}
    		return true;
    	}
    }
}
