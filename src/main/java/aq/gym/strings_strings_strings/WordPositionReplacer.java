package aq.gym.strings_strings_strings;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordPositionReplacer {

	public static void main(String[] args) {
		String text = "Gfg, iueriot jdsfk hu \"jdsfk\"!! Srtm, ioip. Trew dhr er.";  
		System.out.println(text);
		System.out.println(replaceWordInSentence(text));
	}

	private static String replaceWordInSentence(String text) {
		StringBuilder result = new StringBuilder();
		Pattern sentencePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9,:'\"\\s]*[;.!?]+\\s*", Pattern.MULTILINE);
		Matcher sentenceMatcher = sentencePattern.matcher(text);
		while (sentenceMatcher.find()) {
			String sentence = sentenceMatcher.group();
			String[] words = Arrays.stream(sentence.split("\\s+")).toArray(String[]::new);
			String firstWord = words[0];
			String lastWord = words[words.length - 1];
			if(isPunctEnding(firstWord)) {
				int firstWordFirstEndingPunctIndex = getFirstPunctIndex(firstWord);
				String firsWordEndingPunct = firstWord.substring(firstWordFirstEndingPunctIndex);
				int lastWordFirstEndingPunctIndex = getFirstPunctIndex(lastWord);
				String lastWordEndingPunct = lastWord.substring(lastWordFirstEndingPunctIndex);
				firstWord = firstWord.substring(0, firstWordFirstEndingPunctIndex) + lastWordEndingPunct;
				lastWord = lastWord.substring(0, lastWordFirstEndingPunctIndex) + firsWordEndingPunct;
			} else {
				int lastWordFirstEndingPunctIndex = getFirstPunctIndex(lastWord);
				String lastWordEndingPunct = lastWord.substring(lastWordFirstEndingPunctIndex);
				firstWord = firstWord + lastWordEndingPunct;
				lastWord = lastWord.substring(0, lastWordFirstEndingPunctIndex);
			}
			words[0] = lastWord;
			words[words.length - 1] = firstWord;
			result.append(Arrays.stream(words).reduce((s1, s2) -> String.join(" ", s1, s2)).get());
			result.append(" ");
		}
		return result.toString();
	}

	private static boolean isPunctEnding(String word) {
		Pattern pattern = Pattern.compile("[\\w']+[,.:!?]+$");
		Matcher matcher = pattern.matcher(word);
		return matcher.find();
	}

	private static int getFirstPunctIndex(String word) {
		Pattern pattern = Pattern.compile("[,.:!?]+");
		Matcher matcher = pattern.matcher(word);
		matcher.find();
		return matcher.start();
	}
}
