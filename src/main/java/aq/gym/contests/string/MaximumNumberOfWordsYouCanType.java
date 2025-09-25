package aq.gym.contests.string;

import java.util.Arrays;

public class MaximumNumberOfWordsYouCanType {

//	https://leetcode.com/problems/maximum-number-of-words-you-can-type/description
	public static void main(String[] args) {
		System.out.println(new MaximumNumberOfWordsYouCanType().canBeTypedWords("jwssg", "cyvxmtjohsbpfqaur"));
	}

    public int canBeTypedWords(String text, String brokenLetters) {
    	return way1(text, brokenLetters);
    }
    
    @SuppressWarnings("unused")
	private int way1(String text, String brokenLetters) {
    	if(brokenLetters.isEmpty()) {
    		return text.split("\\s").length;
    	}
    	String[] words = text.split("\\s");
    	String[] letters = brokenLetters.split("");
    	int output = words.length;
    	for(String word : words) {
    		for(String letter : letters) {
    			if(word.contains(letter)) {
    				output--;
    				break;
    			}
    		}
    	}
    	return Integer.max(output, 0);
    }
    
    @SuppressWarnings("unused")
	private int way2(String text, String brokenLetters) {
    	if(brokenLetters.isEmpty()) {
    		return text.split("\\s").length;
    	}
    	String[] words = text.split("\\s");
    	String[] letters = brokenLetters.split("");
    	int output = words.length;
    	for(String word : words) {    		
    		if(!ableToTypeWord(getWordChars(word), letters)) {
    			output--;
    		}
    	}
    	return Integer.max(output, 0);
    }
    
    private char[] getWordChars(String word) {
    	char[] chars = word.toCharArray();
    	Arrays.sort(chars);
    	return chars;
    }
    
    private boolean ableToTypeWord(char[] wordChars, String[] brokenLetters) {
    	for(String brokenLetter : brokenLetters) {
			if(binarySearch(wordChars, brokenLetter.charAt(0)) >= 0) {
				return false;
			}
		}
    	return true;
    }
    
    private int binarySearch(char[] arr, char value) {
    	int left = 0, right = arr.length - 1;
    	while(left <= right) {
    		int mid = left + (right - left) / 2;
    		char ch = arr[mid];
    		if(ch == value) {
    			return mid;
    		} else if(ch > value) {
    			right = mid - 1;
    		} else {
    			left = mid + 1;
    		}
    	}
    	return -1;
    }
}
