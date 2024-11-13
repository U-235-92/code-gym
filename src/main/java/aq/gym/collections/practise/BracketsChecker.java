package aq.gym.collections.practise;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class BracketsChecker {

	public static void main(String[] args) {
		String stringToCheck = "((.()x)()y))z";
		System.out.println("Is correct order of brackets? " + (isCorrect(stringToCheck) == true ? "Yes" : "No"));
	}
	
	private static boolean isCorrect(String string) {
		final String openBracket = "(";
		final String closeBracket = ")";
		Deque<String> brackets = Arrays
				.stream(string.split(""))
				.filter(str -> str.matches("[()]"))
				.collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
		int bracketCounter = 0;
		while(brackets.size() > 0) {
			String bracket = brackets.pop();
			if(openBracket.equals(bracket))
				bracketCounter++;
			if(closeBracket.equals(bracket)) 
				bracketCounter--;
		}
		return bracketCounter == 0;
	}
}
