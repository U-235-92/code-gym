package aq.gym.algorithms_and_structures.cipher.cezar;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CezarCipher {

	private final static List<String> ALPHABET = Stream.iterate(97, n -> n + 1)
			.limit(26)
			.map(n -> Character.toString(n))
			.collect(Collectors.toList());
	
	public static void main(String[] args) {
		String str = "abcz 11 ab";
		String result = cipher(str, 2);
		System.out.println(result);
	}
	
	private static String cipher(String str, int key) {
		String[] letters = str.split("");
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < letters.length; i++) {
			String letter = letters[i];
			int idxReplaceLetter = ALPHABET.indexOf(letter); 
			if(idxReplaceLetter != -1) {
				idxReplaceLetter = (idxReplaceLetter + key) % ALPHABET.size();
				result.append(ALPHABET.get(idxReplaceLetter));
			} else {
				result.append(letter);
			}
		}
		return result.toString();
	}
}
