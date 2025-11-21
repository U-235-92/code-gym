package aq.gym.contests.hash_table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UniqueLength3PalindromicSubsequences {

//	https://leetcode.com/problems/unique-length-3-palindromic-subsequences
	public static void main(String[] args) {
//		String s = "aabca";
//		String s = "abc";
//		String s = "bbcbaba";
		String s = IntStream.generate(() -> 97 + (int) (Math.random() * (122 - 97) + 1))
				.limit(100000)
				.mapToObj(num -> new String((char) num + ""))
				.collect(Collectors.joining());
//		String s = IntStream.generate(() -> 97)
//				.limit(100000)
//				.mapToObj(num -> new String((char) num + ""))
//				.collect(Collectors.joining());
		System.out.println(new UniqueLength3PalindromicSubsequences().countPalindromicSubsequence(s));
	}

    public int countPalindromicSubsequence(String s) {
    	char[] chrs = s.toCharArray();
    	Set<String> palindromicSubsequencesSet = new HashSet<>();
        Map<Character, Integer> letterFrequenciesMap = new HashMap<>();
        for(char c : chrs) {
        	letterFrequenciesMap.put(c, letterFrequenciesMap.getOrDefault(c, 1) + 1);
        }
        for(Map.Entry<Character, Integer> entry : letterFrequenciesMap.entrySet()) {
        	char ch = entry.getKey();
        	int frequency = entry.getValue();
        	if(frequency > 1) {
        		int firstOccurIdx = s.indexOf(ch);
        		int lastOccurIdx = s.lastIndexOf(ch);
        		char[] palindromeChrs = {ch,'\u0000',ch};
        		for(int i = firstOccurIdx + 1; i < lastOccurIdx; i++) {
        			palindromeChrs[1] = s.charAt(i);
        			String palindromeStr = new String(palindromeChrs);
        			palindromicSubsequencesSet.add(palindromeStr);
        		}
        	}
        }
        return palindromicSubsequencesSet.size();
    }
}
