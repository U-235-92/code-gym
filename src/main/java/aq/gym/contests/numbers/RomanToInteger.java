package aq.gym.contests.numbers;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

	public static void main(String[] args) {
		int number = new RomanToInteger().romanToInt("MCDLXXVI");
		System.out.println(number);
	}
	
    public int romanToInt(String s) {
    	Map<Character, Integer> map = getRomanNumbersMap();
    	char[] chars = s.toCharArray();
    	if(chars.length == 1) {
    		return map.get(chars[0]);
    	} 
    	int result = 0;
    	for(int i = chars.length - 1; i > 0; i--) {
    		char a = chars[i - 1];
    		char b = chars[i];
    		if(map.get(a) >= map.get(b)) {
    			if(result == 0) {    				
    				result += map.get(a) + map.get(b);
    			} else {
    				result += map.get(a);
    			}
    		} else {
    			if(result == 0) {    				
    				result += map.get(b) - map.get(a);
    			} else {
    				result -= map.get(a);
    			}
    		}
    	}
    	return result;
    }
    
    private Map<Character, Integer> getRomanNumbersMap() {
    	Map<Character, Integer> map = new HashMap<>();
    	map.put('I', 1);
    	map.put('V', 5);
    	map.put('X', 10);
    	map.put('L', 50);
    	map.put('C', 100);
    	map.put('D', 500);
    	map.put('M', 1000);
    	return map;
    }
}
