package aq.gym.contests.sliding_window;

import java.util.Set;

public class MaximumNumberOfVowelsInSubstringOfGivenLength {

//	https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description
	public static void main(String[] args) {
		String s = "abciiidef";
		int k = 3;
		System.out.println(new MaximumNumberOfVowelsInSubstringOfGivenLength().maxVowels(s, k));
	}

    public int maxVowels(String s, int k) {
    	int vowelsCounter = 0, maxVovelsCount = 0; 
        Set<Character> vowels = Set.of('a','e','i','o','u');
        char[] chrs = s.toCharArray();
        for(int i = 0; i < chrs.length; i++) {
        	char curr = chrs[i];
        	if(i < k) {
	        	if(vowels.contains(curr)) {
	        		vowelsCounter++;
	        	}
        	} else {
        		char prev = chrs[i - k];
        		if(vowels.contains(prev)) {
        			vowelsCounter--;
        		}
        		if(vowels.contains(curr)) {
	        		vowelsCounter++;
	        	}
        	}
        	maxVovelsCount = Math.max(maxVovelsCount, vowelsCounter);
        }
        
        return maxVovelsCount;
    }
}
