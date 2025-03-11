package aq.gym.contests.easy;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Polyglot {

	public static void main(String[] args) {
		app();
	}
	
	@SuppressWarnings("unused")
	private static void app() {
		Set<Set<String>> pupils = new HashSet<>();
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		if(n < 1 || n > 1000) {
			scanner.close();
			throw new IllegalArgumentException();
		}
		while(n-- > 0) {
			int m = scanner.nextInt();
			if(m < 1 || m > 500) {
				scanner.close();
				throw new IllegalArgumentException();
			}
			Set<String> languages = new HashSet<>();
			while(m-- > 0) {
				String language = scanner.next();
				if(language.length() > 1000) {
					scanner.close();
					throw new IllegalArgumentException();
				}
				languages.add(language);
			}
			pupils.add(languages);
		}
		polyglot(pupils);
		scanner.close();
	}
	
	private static void polyglot(Set<Set<String>> pupils) {
		Set<String> languagesIntersection = new HashSet<>();
		Set<String> languagesUnion = new HashSet<>();
		calculateLanguagesStatistics(pupils, languagesIntersection, languagesUnion);
		printLanguagesReport(languagesIntersection);
		printLanguagesReport(languagesUnion);
	}
	
	private static void calculateLanguagesStatistics(Set<Set<String>> pupils, Set<String> intersection, Set<String> union) {
		for(Set<String> languages : pupils) {
			if(intersection.isEmpty()) {
				intersection.addAll(languages);
			} else {
				intersection.retainAll(languages);
			}
			union.addAll(languages);
		}
	}
	
	private static void printLanguagesReport(Set<String> languages) {
		System.out.println(languages.size());
		languages.forEach(System.out::println);
	}
	
	@SuppressWarnings("unused")
	private static void app2() {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Set<String> languagesIntersection = new HashSet<>();
		Set<String> languagesUnion = new HashSet<>();
		for(int i = 0; i < n; i++) {
			int m = scanner.nextInt();
			Set<String> pupilLanguages = new HashSet<>();
			for(int j = 0; j < m; j++) {
				String language = scanner.next();
				pupilLanguages.add(language);
			}
			if(i == 0) {
				languagesIntersection.addAll(pupilLanguages);
			} else {
				languagesIntersection.retainAll(pupilLanguages);
			}
			languagesUnion.addAll(pupilLanguages);
		}
		System.out.println(languagesIntersection.size());
		languagesIntersection.forEach(System.out::println);
		System.out.println(languagesUnion.size());
		languagesUnion.forEach(System.out::println);
		scanner.close();
	}
}
