package aq.gym.contests.math;

import java.util.HashMap;
import java.util.Map;

public class ExcelSheetColumnTitle {

//	https://leetcode.com/problems/excel-sheet-column-title/
	public static void main(String[] args) {
		int code = 769175134; //"BLSDTZZ"
		System.out.println(new ExcelSheetColumnTitle().convertToTitle(code));
	}

    public String convertToTitle(int columnNumber) {
    	Map<Integer, Character> alphabet = getAlphabet();
    	StringBuilder title = new StringBuilder();
    	while(columnNumber >= 0) {
    		columnNumber--;
    		if(alphabet.get(columnNumber) == null) {    			
    			int letterCode = (columnNumber) % 26;
    			char letter = alphabet.get(letterCode);
    			title.append(letter);
    			columnNumber = columnNumber / 26;
    		} else {
    			char letter = alphabet.get(columnNumber);
    			title.append(letter);
    			break;
    		}
    	}
    	return title.reverse().toString();
    }
    
    private Map<Integer, Character> getAlphabet() {
    	Map<Integer, Character> map = new HashMap<>();
        for(int i = 0; i < 26; i++) {
        	map.put(i, (char) (65 + i));
        }
        return map;
    }
}
