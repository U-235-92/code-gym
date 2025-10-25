package aq.gym.contests.string;

public class ZigzagConversion {

//	https://leetcode.com/problems/zigzag-conversion/
	public static void main(String[] args) {
//		String s = "PAYPALISHIRING";
//		int numRows = 4;
//		System.out.println(new ZigzagConversion().convert(s, numRows).equals("PINALSIGYAHRPI"));
		String s = "Apalindromeisaword,phrase,number.";
		int numRows = 3;
		System.out.println(new ZigzagConversion().convert(s, numRows));
	}
	
    public String convert(String s, int numRows) {
        if(numRows == 1) return s;
        if(numRows >= s.length()) return s;
        else return getZigzag(s, numRows);
    }
    
    private String getZigzag(String s, final int numRows) {
    	int sIdx = 0, columnIdx = 0, rowIdx = 0;
    	int rows = numRows, cols = s.length();
    	char[][] table = new char[rows][cols];
    	boolean zig = true, zag = false;
    	while(sIdx < s.length()) {
    		if(zig) {
    			if(columnIdx < cols) {    				
    				for(; rowIdx < rows; rowIdx++) {
    					table[rowIdx][columnIdx] = s.charAt(sIdx);
    					if(++sIdx >= s.length()) break;
    				}
    				columnIdx++;
    				rowIdx = 0;
    			} else {
    				if(sIdx < s.length()) {
    					for(; rowIdx < rows; rowIdx++) {
        					table[rowIdx][columnIdx - 1] = s.charAt(sIdx);
        					if(++sIdx >= s.length()) break;
        				}
        				columnIdx++;
        				rowIdx = 0;
    				}
    			}
    			zig = false; zag = true;
    			continue;
    		}
    		if(zag) {
    			if(columnIdx < cols) {
    				if(rows - 2 == 0) {
    					table[rowIdx][columnIdx] = s.charAt(sIdx);
    					rowIdx++;
    					if(++sIdx >= s.length()) break;
    				} else {    					
						for(rowIdx = rows - 2; rowIdx >= 1; rowIdx--) {
    						table[rowIdx][columnIdx] = s.charAt(sIdx);
    						if(++sIdx >= s.length()) break;
    					}
						rowIdx = 0;
						columnIdx++;
    				}
    			} else {
    				if(sIdx < s.length()) {
    					if(rows - 2 == 0) {
        					table[rowIdx][columnIdx] = s.charAt(sIdx);
        					rowIdx++;
        					if(++sIdx >= s.length()) break;
        				} else {    					
							for(rowIdx = rows - 2; rowIdx >= 1; rowIdx--) {
        						table[rowIdx][columnIdx] = s.charAt(sIdx);
        						if(++sIdx >= s.length()) break;
        					}
							rowIdx = 0;
							columnIdx++;
        				}
    				}
    			}
    			zig = true; zag = false;
    			continue;
    		}
    	}
    	return getResult(table);
    }
    
    private String getResult(char[][] table) {
    	StringBuilder result = new StringBuilder();
    	final char defaultTableChar = '\u0000';
    	for(char[] line : table) {
    		for(char ch : line) {
    			if(ch != defaultTableChar) {
    				result.append(ch);
    			}
    		}
    	}
    	return result.toString();
    }
}
