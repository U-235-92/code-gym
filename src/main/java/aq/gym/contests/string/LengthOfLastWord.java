package aq.gym.contests.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LengthOfLastWord {

	public static void main(String[] args) {
		String str = "Hello world";
		System.out.println(new LengthOfLastWord().lengthOfLastWord(str));
	}
	
    public int lengthOfLastWord(String s) {
    	return way2(s);
    }
    
    @SuppressWarnings("unused")
	private int way1(String s) {
    	Pattern pattern = Pattern.compile("\\s*?[a-zA-Z]+\\s*?$");
    	Matcher matcher = pattern.matcher(s);
    	if(matcher.find()) {
    		String match = matcher.group().trim();
    		return match.length();
    	}
    	return 0;
    }
    
    private int way2(String s) {
    	s = s.trim();
    	int length = 0;
    	for(int i = s.length() - 1; i >= 0; i--) {
    		char ch = s.charAt(i);
    		if(ch == ' ') {    			
    			return length;
    		} else {    			
    			length++;
    		}
    	}
    	return length;
    }
}
