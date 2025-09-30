package aq.gym.contests.array;

public class ValidPalindrome {

//	https://leetcode.com/problems/valid-palindrome/description/
	public static void main(String[] args) {
		String s = "A man, a plan, a canal: Panama";
		System.out.println(new ValidPalindrome().isPalindrome(s));
	}
	
    public boolean isPalindrome(String s) {
        StringBuilder palindrome = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
        	char ch = s.charAt(i);
        	if(Character.isAlphabetic(ch) || Character.isDigit(ch)) {
        		palindrome.append(ch);
        	}	
        }
        return palindrome.toString().toLowerCase().equals(palindrome.reverse().toString().toLowerCase());
    }
}
