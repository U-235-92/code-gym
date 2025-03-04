package aq.gym.contests.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class TotalDifferentCounter {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder text = new StringBuilder();
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			text.append(line);
			text.append(" ");
		}
		Set<String> words = Arrays.stream(text.toString().split("\\s+")).collect(Collectors.toCollection(HashSet::new));
		System.out.println(words.size());
		scanner.close();
	}
}
