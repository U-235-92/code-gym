package aq.gym.contests.string;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LongestSubstringWithoutRepeatingCharacters {

//	https://leetcode.com/problems/longest-substring-without-repeating-characters/
	public static void main(String[] args) {
//		String s = "anviaj"; // 5
//		String s = "abcabcbb"; // 3
//		String s = "bbbbb"; // 1
//		String s = "pwwkew"; // 3
//		String s = "dvdf"; // 3
//		String s = ""; // 0
//		String s = "a"; // 1
//		String s = "a b c a b c"; // 3
//		String s = "abcdef"; // 6
//		String s = "123abc321"; // 6
//		String s = "!@#$%^&*()"; // 10
//		String s = "abababababababab"; // 2
//		String s = "abcdefghijklmnopqrstuvwxyz"; // 26
//		String s = "abababab"; // 2
//		String s = "a1b2c3!@#abc"; // 9
//		String s = "AaBbCcDd"; // 8
//		String s = "aaaaaaaabbbbbbbbcccccccc"; // 2
		Supplier<String> letterGenerator = () -> {
			char letter = (char) (32 + (int) (Math.random() * (125 - 32) + 1));
			return letter + "";
		};
		int limit = 5 * (int) Math.pow(10, 4);
		String s = Stream.generate(letterGenerator).limit(limit).collect(Collectors.joining());
		System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(s));
	}

    public int lengthOfLongestSubstring(String s) {
    	if(s.isEmpty()) {
    		return 0;
    	}
    	int maxLength = Integer.MIN_VALUE, i = 0, j = 0;
        Map<Character, Integer> letters = new HashMap<>();
        while(j < s.length() && i < s.length()) {
        	char letter = s.charAt(j);
        	if(!letters.containsKey(letter)) {
        		letters.put(letter, 1);
        		j++;
        		int guessMax = j - i;
        		maxLength = Math.max(maxLength, guessMax);
        	} else {
        		letters.remove(s.charAt(i));
        		i++;
        	}
        }
        return maxLength;
    }
}
