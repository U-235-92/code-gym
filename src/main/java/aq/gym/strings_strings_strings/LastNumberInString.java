package aq.gym.strings_strings_strings;

import java.util.NoSuchElementException;
import java.util.Optional;

public class LastNumberInString {

	public static void main(String[] args) {
		String str = "Buy 1 get 2 free";
		String result = find(str).orElseThrow(() -> new NoSuchElementException("No numbers found!"));
		System.out.println("The last number is: " + result);
	}

	private static Optional<String> find(String string) {
		String[] words = string.split(" ");
		int idx = words.length - 1;
		while(idx-- > 0) {
			if(words[idx].matches("[0-9]+"))
				return Optional.of(words[idx]);
		}
		return Optional.empty();
	}
}
