package aq.gym.contests.hash_set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CyberSecurity {

	public static void main(String[] args) throws IOException {
		sec2();
	}
	
	@SuppressWarnings("unused")
	private static void sec1() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String instance = reader.readLine();
		Set<String> set = new HashSet<>(Set.of(instance));
		for(int i = 0; i < instance.length(); i++) {			
			for(int j = i + 1; j < instance.length(); j++) {
				if(instance.charAt(i) != instance.charAt(j)) {
					char[] chrs = instance.toCharArray();
					char tmp = chrs[i];
					chrs[i] = chrs[j];
					chrs[j] = tmp;
					set.add(new String(chrs));
					tmp = chrs[i];
					chrs[i] = chrs[j];
					chrs[j] = tmp;
				}
			}
		}
		System.out.println(set.size());
	}
	
	@SuppressWarnings("unused")
	private static void sec2() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String instance = reader.readLine();
		int lettersNumberToSwap = instance.length();
		int result = 1;
		Map<Character, Integer> characterFrequency = new HashMap<>();
		for(int i = 0; i < lettersNumberToSwap; i++) {
			char ch = instance.charAt(i);
			characterFrequency.compute(ch, (k, v) -> {
				if(v == null) {
					return 1;
				} else {
					return v + 1;
				}
			});
		}
		for(int i = 0; i < instance.length(); i++) {
			char ch = instance.charAt(i);
			int frequency = characterFrequency.get(ch);
			result += lettersNumberToSwap - frequency;
			characterFrequency.put(ch, frequency - 1);
			lettersNumberToSwap--;
		}
		System.out.println(result);
	}
}
