package aq.gym.contests.recursion;

import java.util.Arrays;

public class ReverseString {

	public static void main(String[] args) {
		char[] orig = "abcde".toCharArray();
		System.out.println(Arrays.toString(orig));
		new ReverseString().reverseString(orig);
		System.out.println(Arrays.toString(orig));
	}
	
	public void reverseString(char[] s) {
		reverseString(0, s.length - 1, s);
	}
	
	private void reverseString(int i, int j, char[] s) {
		if(i >= s.length && j < 0)
			return;
		reverseString(i + 1, j - 1, s);
		if(i <= j)
			return;
		char tmp = s[i];
		s[i] = s[j];
		s[j] = tmp;
	}
}
