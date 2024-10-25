package aq.gym.strings_strings_strings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SentenceWithSameWords {

	public static void main(String[] args) throws IOException {
//		String text = "Abc, abc dd, f dd fdhgf! Hjt'r jdldpnr?!  Treu rtyu, refhf refhf dfg.";
		String text = Files.lines(Paths.get("src/main/java/aq/gym/strings_strings_strings/word-counter-text.txt"))
				.reduce((s1, s2) -> String.join("\n", s1, s2))
				.get();
		System.out.println(MessageFormat.format("The text''s got {0, choice, 0#no senteces; | 1#one sentence; | 2#{0} senteces;}", getNumberOfSenteces(text)));
		System.out.println("The number of sentences with same word: " + getNumberOfSentencesWithSameWords(text) + ";");
	}
	
	private static int getNumberOfSenteces(String text) {
		int number = 0;
		Pattern sentencePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9,:'\"\\s]*[;.!?]+\\s*", Pattern.MULTILINE);
		Matcher sentenceMatcher = sentencePattern.matcher(text);
		while(sentenceMatcher.find()) {
			number++;
		}
		return number;
	}
	
	private static int getNumberOfSentencesWithSameWords(String text) {
		int countSentencesWithSameWord = 0;
		Pattern sentencePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9,:'\"\\s]*[;.!?]+\\s*", Pattern.MULTILINE);
		Matcher sentenceMatcher = sentencePattern.matcher(text);
		while(sentenceMatcher.find()) {
			String sentence = sentenceMatcher.group();
			Map<String, Long> countWordMap = wordStatistic(sentence);
			long maxNumberOfSameWordsInSentence = countWordMap
					.values()
					.stream()
					.collect(Collectors.summarizingLong(Long::longValue))
					.getMax();
			if(maxNumberOfSameWordsInSentence > 1) 
				countSentencesWithSameWord++;
		}
		return countSentencesWithSameWord;
	}
	
	private static Map<String, Long> wordStatistic(String sentence) {
		Map<String, Long> countWordMap = Arrays.stream(sentence.split("\\p{Punct}*\\s+"))
				.filter(word -> {
					Pattern wordPattern = Pattern.compile("[\\w]+");
					Matcher wordMatcher = wordPattern.matcher(word);
					if(wordMatcher.find())
						return true;
					return false;
				})
				.collect(Collectors.groupingBy(word -> word.toLowerCase(), Collectors.counting()));
		return countWordMap;
	}

}
