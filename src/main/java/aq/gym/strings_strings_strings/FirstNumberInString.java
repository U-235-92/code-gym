package aq.gym.strings_strings_strings;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

public class FirstNumberInString {

	public static void main(String[] args) {
		String str = "Buy 1 get 2 free";
		String result = find(str).orElseThrow(() -> new NoSuchElementException("No numbers found!"));
		System.out.println("The first number is: " + result);
	}
	
	private static Optional<String> find(String string) {
		return Arrays.stream(string.split(" "))
				.filter(letter -> letter.matches("[0-9]+.*"))
				.map(letter -> letter.split("[a-zA-Z,:;!?]+")[0])
				.findFirst();
	}
}
