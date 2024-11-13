package aq.gym.strings_strings_strings;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NameChooser {

	public static void main(String[] args) {
		chooseNameStartedWith("J").forEach(System.out::println);
	}

	private static List<String> chooseNameStartedWith(String letter) {
		return Stream.of("John", "Smith", "Sarah", "Will")
				.filter(name -> name.substring(0, 1).toLowerCase().equals(letter.toLowerCase()))
				.collect(Collectors.toList());
	}
}
