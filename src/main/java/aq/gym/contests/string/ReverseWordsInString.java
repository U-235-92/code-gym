package aq.gym.contests.string;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseWordsInString {

	public static void main(String[] args) {
		String orig = "  hello world  ";
		String reverse = new ReverseWordsInString().reverseWords(orig);
		System.out.println(orig);
		System.out.println(reverse);
	}

	public String reverseWords(String s) {
        String[] words = s.split("\\s+");
        if(words.length == 1) {
        	return words[0];
        }
        int i = 0, j = words.length - 1;
        while(i < j) {
        	String tmp = words[i];
        	words[i] = words[j];
        	words[j] = tmp;
        	i++; j--;
        }
        return Arrays.stream(words).collect(Collectors.joining(" ")).trim();
    }
}
