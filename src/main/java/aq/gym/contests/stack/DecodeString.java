package aq.gym.contests.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {

	public static void main(String[] args) {
		String encode = "2[abc]3[cd]ef"; //"3[a]2[bc]" "3[a2[c]]" "2[abc]3[cd]ef"
		String decode = new DecodeString().decodeString(encode);
		System.out.println(decode);
	}

	public String decodeString(String s) {
		StringBuilder decode = new StringBuilder();
		Deque<Integer> repeatsStack = new ArrayDeque<>();
		Deque<String> sequenceStack = new ArrayDeque<>();
		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if(Character.isDigit(ch)) {
				int number = 0;
				while(i < s.length() && Character.isDigit(s.charAt(i))) {
					number = (number * 10) + Character.digit(s.charAt(i), 10);
					i++;
				}
				repeatsStack.push(number);
				i--;
			} else {
				switch (ch) {
				case '[':
					sequenceStack.push(ch + "");
					break;
				case ']':
					decode(repeatsStack, sequenceStack);
					break;
				default:
					sequenceStack.push(ch + "");
					break;
				}
			}
		}
		while(!sequenceStack.isEmpty()) {
			decode.insert(0, sequenceStack.pop());
		}
		return decode.toString();
	}

	private void decode(Deque<Integer> repeatsStack, Deque<String> sequenceStack) {
		StringBuilder newSequence = new StringBuilder(), repeat = new StringBuilder();
		int repeatsCount = repeatsStack.pop();
		while(!sequenceStack.isEmpty()) {
			String currSequence = sequenceStack.pop();
			if(currSequence.equals("[")) {
				break;
			} else {
				newSequence.insert(0, currSequence);
			}
		}
		for(int i = 0; i < repeatsCount; i++) {
			repeat.append(newSequence);
		}
		sequenceStack.push(repeat.toString());
	}
}
