package aq.gym.contests.easy;

import java.util.Map;
import java.util.stream.Collectors;

public class RansomNote {

	public static void main(String[] args) {
		System.out.println(new RansomNote().canConstruct("fihjjjjei", "hjibagacbhadfaefdjaeaebgi"));
	}

	public boolean canConstruct(String ransomNote, String magazine) {
		if(ransomNote.length() > magazine.length()) {
			return false;			
		}
        Map<Character, Long> magazineMap = magazine.chars()
        		.mapToObj(i -> (char) i)
        		.collect(Collectors.groupingBy(k -> k, Collectors.counting()));
        for(char ch : ransomNote.toCharArray()) {
        	if(!magazineMap.containsKey(ch)) {        		
        		return false;
        	} else {
        		if(magazineMap.get(ch) == 1) {
        			magazineMap.remove(ch);
        		} else {
        			long remain = magazineMap.get(ch);
        			magazineMap.put(ch, --remain);
        		}
        	}
        }
        return true;
    }
}
