package aq.gym.contests.easy;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SymbolsSet {

	public static void main(String[] args) {
		Test test = new Test();
		test.generateAnswers();
//		app();
	}
	
	@SuppressWarnings("unused")
	private static void app() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String text = br.readLine();
			String chars = br.readLine();
			int minimalLength = getMinimalSubstringLength(text, chars);
			System.out.println(minimalLength);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int getMinimalSubstringLength(String text, String chars) {
		int minimalSubstringLength = Integer.MAX_VALUE;
		List<String> letters = Arrays.stream(text.split("")).collect(Collectors.toCollection(ArrayList::new));
		Set<String> alphabet = Arrays.stream(chars.split("")).collect(Collectors.toCollection(HashSet::new));
		Set<String> alphabetCopy = new HashSet<>(alphabet);
		int start = -1, end = 0;
		for(int i = 0; i < letters.size(); i++) {
			if(alphabetCopy.contains(letters.get(i))) {
				alphabetCopy.remove(letters.get(i));
				if(start == -1) {
					start = i;
				}
			}
			if(alphabetCopy.size() == 0) {
				end = i + 1;
				int currentMinSubstringLength = text.substring(start, end).length();
				if(currentMinSubstringLength < minimalSubstringLength) {
					minimalSubstringLength = currentMinSubstringLength;
				}
				i = start;
				start = -1;
				alphabetCopy.addAll(alphabet);
			}
		}
		if(minimalSubstringLength == Integer.MAX_VALUE) {
			minimalSubstringLength = 0;
		}
		return minimalSubstringLength;
	}
	
	private static class Test {
		
		private void generateAnswers() {
			File file = new File("src/main/java/aq/gym/contests/easy/symbols_set_generated_answers.txt");
			try(PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
				for(int i = 0; i < 100; i++) {
					List<String> alphabetLetters = generateAlphabetLetters();
					String alphabet = generateStringFrom(alphabetLetters);
					List<String> textLetters = generateTextLetters(alphabetLetters);
					String text = generateStringFrom(textLetters);
					printTestAnswers(text, alphabet, pw, i);
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		private List<String> generateAlphabetLetters() {
			int min = 97, max = 122, limit = 3;
			List<String> alphabetLetters = new ArrayList<>(IntStream
					.generate(() -> min + (int) ((Math.random() * (max - min)) + 1))
					.mapToObj(code -> (char) code)
					.map(String::valueOf)
					.limit(limit)
					.collect(Collectors.toCollection(HashSet::new)));
			return alphabetLetters;
		}
		
		private List<String> generateTextLetters(List<String> alphabetLetters) {
			int limit = (int) (Math.random() * 11);
			List<String> textLetters = Stream
					.generate(() -> alphabetLetters.get((int) (Math.random() * alphabetLetters.size())))
					.limit(limit)
					.collect(Collectors.toCollection(ArrayList::new));
			return textLetters;
		}
		
		private String generateStringFrom(List<String> letters) {
			return letters
					.stream()
					.collect(Collectors.joining(""));
		}
		
		private void printTestAnswers(String text, String alphabet, PrintWriter pw, int i) {
			pw.println("BEGINNIG OF TEST [" + (i + 1) + "]");
			pw.println("Text: [" + text + "] Alphabet: [" + alphabet + "]");
			int minSubstringLength = testMinimalSubstringLength(text, alphabet, pw);
			pw.println("Minimal substring length = " + minSubstringLength);
			pw.println("ENDING OF TEST [" + (i + 1) + "]");
			pw.println();
		}
		
		private int testMinimalSubstringLength(String text, String chars, PrintWriter pw) {
			int minimalSubstringLength = Integer.MAX_VALUE;
			List<String> letters = Arrays.stream(text.split("")).collect(Collectors.toCollection(ArrayList::new));
			Set<String> alphabet = Arrays.stream(chars.split("")).collect(Collectors.toCollection(HashSet::new));
			Set<String> alphabetCopy = new HashSet<>(alphabet);
			int start = -1, end = 0;
			for(int i = 0; i < letters.size(); i++) {
				if(alphabetCopy.contains(letters.get(i))) {
					alphabetCopy.remove(letters.get(i));
					if(start == -1) {
						start = i;
					}
				}
				if(alphabetCopy.size() == 0) {
					end = i + 1;
					int currentMinSubstringLength = text.substring(start, end).length();
					pw.println(text.substring(start, end));
					if(currentMinSubstringLength < minimalSubstringLength) {
						minimalSubstringLength = currentMinSubstringLength;
					}
					i = start;
					start = -1;
					alphabetCopy.addAll(alphabet);
				}
			}
			if(minimalSubstringLength == Integer.MAX_VALUE) {
				minimalSubstringLength = 0;
			}
			return minimalSubstringLength;
		}
	}
}
