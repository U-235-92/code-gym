package aq.gym.strings_strings_strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PutCapitalLetterToBeginingOfSentence {

	public static void main(String[] args) {
		String text = "hello, world!! bye world... hello?";   
		String result = put(text); 
		System.out.println(result);
	}
	
	private static String put(String text) {
		Pattern sentencePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9,:'\"\\s]*[;.!?]+\\s*", Pattern.MULTILINE);
		Matcher sentenceMatcher = sentencePattern.matcher(text);
		StringBuilder result = new StringBuilder();
		while(sentenceMatcher.find()) {
			String sentence = sentenceMatcher.group();
			String firstLetter = sentence.substring(0, 1);
			firstLetter = firstLetter.toUpperCase();
			sentence = firstLetter + sentence.substring(1, sentence.length());
			result.append(sentence);
		}
		return result.toString();
	}

}
