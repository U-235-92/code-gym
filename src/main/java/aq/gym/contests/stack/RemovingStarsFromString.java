package aq.gym.contests.stack;

import java.util.ArrayDeque;

public class RemovingStarsFromString {

//	https://leetcode.com/problems/removing-stars-from-a-string/
	public static void main(String[] args) {
		String s = "leet**cod*e";
		System.out.println(new RemovingStarsFromString().removeStars(s));
	}

    public String removeStars(String s) {
        ArrayDeque<Character> letterStack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++) {
        	char letter = s.charAt(i);
        	switch (letter) {
				case '*': 
					letterStack.pop();
					break;
				default:
					letterStack.push(letter);
					break;
			}		
        }
        StringBuilder result = new StringBuilder();
        while(!letterStack.isEmpty()) {
        	result.append(letterStack.removeLast());
        }
        return result.toString();
    }
}
