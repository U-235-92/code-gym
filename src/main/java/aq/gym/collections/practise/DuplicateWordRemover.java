package aq.gym.collections.practise;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class DuplicateWordRemover {

	public static void main(String[] args) {
		String text = "programmer is waiting another programmer result";
		String result = remove(text);
		System.out.println(result);
	}
	
	public static String remove(String text) {
		LinkedHashSet<String> words =  Arrays.stream(text.split(" "))
				.collect(LinkedHashSet::new, LinkedHashSet::add, LinkedHashSet::addAll);
		return words.stream().collect(Collectors.joining(" "));
	}

}
