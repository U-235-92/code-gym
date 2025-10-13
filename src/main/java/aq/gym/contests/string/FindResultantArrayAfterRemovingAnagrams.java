package aq.gym.contests.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindResultantArrayAfterRemovingAnagrams {

//	https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/description/
	public static void main(String[] args) {
		String[] words = {"abba","baba","bbaa","cd","cd"};
//		String[] words = {"a", "b", "a"};
//		String[] words = {"a","b","c","d","e"};
//		String[] words = {"z","z","z","gsw","wsg","gsw","krptu"};
//		String[] words = {"ab","bc","cb","ab","bc"}; //?
//		String[] words = {"abbb","aaab"};
//		String[] words = {"az","azz"};
		System.out.println(new FindResultantArrayAfterRemovingAnagrams().removeAnagrams(words));
	}

    public List<String> removeAnagrams(String[] words) {
        List<String> wordList = new ArrayList<>(Arrays.asList(words));
        return removeAnagrams(wordList);
    }
    
    private List<String> removeAnagrams(List<String> words) {
    	final int size = words.size();
    	for(int i = 0; i < words.size() - 1; i++) {
    		String word1 = words.get(i);
    		String word2 = words.get(i + 1);
    		if(isAnagram(word1, word2)) {
    			words.remove(i + 1);
    		}
    	}
    	if(words.size() == size) {
    		return words;
    	}
    	return removeAnagrams(words);
    }
    
    private boolean isAnagram(String word1, String word2) {
    	char[] chrs1 = word1.toCharArray();
    	char[] chrs2 = word2.toCharArray();
    	Arrays.sort(chrs1);
    	Arrays.sort(chrs2);
    	return Arrays.equals(chrs1, chrs2);
    }
}
