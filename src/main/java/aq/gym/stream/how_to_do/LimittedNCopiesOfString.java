package aq.gym.stream.how_to_do;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LimittedNCopiesOfString {

	public static void main(String[] args) {
		print("cat", 10, 5);
	}

	private static void print(String string, int numCopies, int maxStingLength) {
		if(string.length() > maxStingLength) 
			string = string.substring(0, maxStingLength);
		String result = Stream.iterate(string, nextString -> nextString).limit(numCopies).collect(Collectors.joining(", "));
		System.out.println(result);
	}
}
