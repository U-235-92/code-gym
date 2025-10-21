package aq.gym.contests.math;

import java.util.HashMap;
import java.util.Map;

public class ExcelSheetColumnNumber {

//	https://leetcode.com/problems/excel-sheet-column-number/description/
	public static void main(String[] args) {
		System.out.println(new ExcelSheetColumnNumber().titleToNumber("ZY"));
	}

    public int titleToNumber(String columnTitle) {
    	Map<Character, Integer> alphabet = getAlphabet();
    	char[] chrs = columnTitle.toCharArray();
    	int number = 0;
    	for(int i = 0, pow = chrs.length - 1; i < chrs.length; i++, pow--) {
    		int a = alphabet.get(chrs[i]);
    		int b = (int) Math.pow(26, pow);
    		number += (a * b);
    	}
    	return number;
    }
    
    private Map<Character, Integer> getAlphabet() {
    	Map<Character, Integer> map = new HashMap<>();
    	int j = 1;
        for(char i = 'A'; i <= 'Z'; i++, j++) {
        	map.put(i, j);
        }
        return map;
    }
}
