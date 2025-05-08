package aq.gym.contests.string;

import java.util.Arrays;

public class ReverseString {

	public static void main(String[] args) {
		char[] s = "hello".toCharArray();
		System.out.println(Arrays.toString(s));
		new ReverseString().reverseString(s);
		System.out.println(Arrays.toString(s));
	}

	public void reverseString(char[] s) {
		int i = 0, j = s.length - 1;
		while(i < j) {
			swap(s, i, j);
			i++; j--;
		}
	}
	
	private void swap(char[] s, int i, int j) {
		char tmp = s[i];
		s[i] = s[j];
		s[j] = tmp;
	}
}
