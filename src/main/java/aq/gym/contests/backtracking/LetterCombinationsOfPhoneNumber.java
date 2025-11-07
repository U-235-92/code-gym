package aq.gym.contests.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfPhoneNumber {

//	https://leetcode.com/problems/letter-combinations-of-a-phone-number/
	public static void main(String[] args) {
//		String digits = "2"; // "a","b","c"
//		String digits = "23"; // "ad","ae","af","bd","be","bf","cd","ce","cf"
//		String digits = "234"; 
//		String digits = "67"; // mp, mq, mr, ms, np, nq, nr, ns, op, oq, or, os
//		String digits = "78"; // pt, pu, pv, qt, qu, qv, rt, ru, rv, st, su, sv
//		String digits = "789";
//		String digits = "678";
//		String digits = "6789";
		String digits = "9999";
		System.out.println(new LetterCombinationsOfPhoneNumber().letterCombinations(digits).size());
	}
	
    public List<String> letterCombinations(String digits) {
    	Map<Integer, List<String>> keyboard = getKeyboard();
        List<String> combinatons = new ArrayList<>();
        List<Integer> listDigits = Arrays.stream(digits.split("")).map(Integer::valueOf).toList();
        makeLetterCombinations(listDigits, keyboard, combinatons, "", 0);
        return combinatons;
    }
    
    private Map<Integer, List<String>> getKeyboard() {
    	Map<Integer, List<String>> keyboard = new HashMap<>();
    	keyboard.put(2, List.of("a", "b", "c"));
    	keyboard.put(3, List.of("d", "e", "f"));
    	keyboard.put(4, List.of("g", "h", "i"));
    	keyboard.put(5, List.of("j", "k", "l"));
    	keyboard.put(6, List.of("m", "n", "o"));
    	keyboard.put(7, List.of("p", "q", "r", "s"));
    	keyboard.put(8, List.of("t", "u", "v"));
    	keyboard.put(9, List.of("w", "x", "y", "z"));
    	return keyboard;
    }
    
    private void makeLetterCombinations(List<Integer> digits, Map<Integer, List<String>> keyboard, List<String> combinatons, String combination, int digitIdx) {
    	if(combination.length() >= digits.size()) {
    		combinatons.add(combination);
			return;
    	}
    	int digit = digits.get(digitIdx);
    	List<String> letters = keyboard.get(digit);
    	for(int l = 0; l < letters.size(); l++) {
			String letter = letters.get(l);
			makeLetterCombinations(digits, keyboard, combinatons, combination + letter, digitIdx + 1);
		}
    }
}
