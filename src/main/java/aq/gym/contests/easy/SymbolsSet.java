package aq.gym.contests.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SymbolsSet {

	public static void main(String[] args) {
		app();
	}
	
	@SuppressWarnings("unused")
	private static void app() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String text = br.readLine();
			String chars = br.readLine();
			int minimalLength = getMinimalSubstringLengthKodicHug(text, chars);
			System.out.println(minimalLength);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int getMinimalSubstringLengthKodicHug(String text, String chars) {
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
				// System.out.println(text.substring(start, end));
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