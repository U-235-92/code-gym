package aq.gym.contests.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
			int minimalLength = getMinimalSubstringLengthKodikHugSlideWindow(text, chars);
			System.out.println(minimalLength);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int getMinimalSubstringLengthKodikHugSlideWindow(String text, String chars) {
		if(chars.length() == 0) {
			return 0;
		} else if(text.length() < chars.length()) {
			return 0;
		} else {
			int minimalSubstringLength = Integer.MAX_VALUE;
			int left = -1, right = 0;
			List<String> letters = Arrays.stream(text.split("")).collect(Collectors.toCollection(ArrayList::new));
			Set<String> alphabet = Arrays.stream(chars.split("")).collect(Collectors.toCollection(HashSet::new));
			while(right < letters.size()) {
				if(alphabet.contains(letters.get(right))) {
					if(left == -1) {
						left = right;
					}
				}
				if(left != -1) {					
					if(isValidSubstring(text.substring(left, right + 1), alphabet)) {
						minimalSubstringLength = right + 1 - left;
						break;
					}
				}
				right++;
			}
			if(left == -1) {
				return 0;
			}
			if(right == letters.size() - 1) {
				while(letters.size() - left >= left) {
					if(isValidSubstring(text.substring(left, right + 1), alphabet)) {
						int currentMinimalSubstringLength = right + 1 - left;
						if(isMinimalSubstring(minimalSubstringLength, currentMinimalSubstringLength)) {
							minimalSubstringLength = currentMinimalSubstringLength;
						}
					}
					left++;
				}
			} else {
				for(; right < letters.size(); right++) {
					if(isValidSubstring(text.substring(left, right + 1), alphabet)) {
						int currentMinimalSubstringLength = right + 1 - left;
						if(isMinimalSubstring(minimalSubstringLength, currentMinimalSubstringLength)) {
							minimalSubstringLength = currentMinimalSubstringLength;
							left++;
						}
					} 
				}
				while(letters.size() - left >= left) {
					if(isValidSubstring(text.substring(left, right), alphabet)) {
						int currentMinimalSubstringLength = right- left;
						if(isMinimalSubstring(minimalSubstringLength, currentMinimalSubstringLength)) {							
							minimalSubstringLength = currentMinimalSubstringLength;
						}
					}
					left++;
				}
			}
			if(minimalSubstringLength == Integer.MAX_VALUE) {
				minimalSubstringLength = 0;
			}
			return minimalSubstringLength;
		}
	}
	
	private static boolean isValidSubstring(String substring, Set<String> alphabet) {
		return Arrays.stream(substring.split("")).collect(Collectors.toCollection(HashSet::new)).containsAll(alphabet);
	}
	
	private static boolean isMinimalSubstring(int minimalSubstringLength, int currentMinimalSubstringLength) {
		return currentMinimalSubstringLength < minimalSubstringLength;
	}
	
	@SuppressWarnings("unused")
	private static int getMinimalSubstringLengthKodikHugMap(String text, String chars) {
		if(chars.length() == 0) {
			return 0;
		} else if(text.length() < chars.length()) {
			return 0;
		} else {			
			int minimalSubstringLength = Integer.MAX_VALUE;
			List<String> letters = Arrays.stream(text.split("")).collect(Collectors.toCollection(ArrayList::new));
			Map<String, Long> alphabetMap = Arrays.stream(chars.split("")).collect(Collectors.groupingBy(k -> k, Collectors.counting()));
			Map<String, Long> substringMap = new HashMap<>();
			for(int i = 0, j = 0; i < letters.size(); i++) {
				while(j < letters.size() && !isSubstringFound(alphabetMap, substringMap)) {
					substringMap.compute(letters.get(j), (k, v) -> (v == null) ? 1L : v + 1L);
					j++;
				}
				if(isSubstringFound(alphabetMap, substringMap) && j - i < minimalSubstringLength) {
					minimalSubstringLength = j - i;
				}
				substringMap.compute(letters.get(i), (k, v) -> v - 1L);
			}
			if(minimalSubstringLength == Integer.MAX_VALUE) {
				minimalSubstringLength = 0;
			}
			return minimalSubstringLength;
		}
	}
	
	private static boolean isSubstringFound(Map<String, Long> alphabetMap, Map<String, Long> substringMap) {
		for(String letter : alphabetMap.keySet()) {
			if(substringMap.getOrDefault(letter, 0L) < alphabetMap.get(letter)) {
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings("unused")
	private static int getMinimalSubstringLengthKodikHugPointers(String text, String chars) {
		if(chars.length() == 0) {
			return 0;
		} else if(text.length() < chars.length()) {
			return 0;
		} else {			
			int minimalSubstringLength = Integer.MAX_VALUE;
			List<String> letters = Arrays.stream(text.split("")).collect(Collectors.toCollection(ArrayList::new));
			Set<String> alphabet = Arrays.stream(chars.split("")).collect(Collectors.toCollection(HashSet::new));
			Set<String> alphabetCopy = new HashSet<>();
			for(int i = 0, j = 0; i < letters.size(); i++) {
				while(j < letters.size() && alphabetCopy.size() != alphabet.size()) {
					if(alphabet.contains(letters.get(j))) {
						alphabetCopy.add(letters.get(j));
					}
					j++;
				}
				if(alphabetCopy.size() == alphabet.size() && j - i < minimalSubstringLength) {
					minimalSubstringLength = j - i;
				}
				j = i + 1;
				alphabetCopy.removeAll(alphabetCopy);
			}
			if(minimalSubstringLength == Integer.MAX_VALUE) {
				minimalSubstringLength = 0;
			}
			return minimalSubstringLength;
		}
	}
	
	@SuppressWarnings("unused")
	private static int getMinimalSubstringLengthKodikHugNoPointers(String text, String chars) {
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
//				 System.out.println(text.substring(start, end));
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