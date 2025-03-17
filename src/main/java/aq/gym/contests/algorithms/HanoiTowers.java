package aq.gym.contests.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HanoiTowers {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numberOfDisc = scanner.nextInt();
		scanner.close();
		List<String> steps = new ArrayList<>();
		hanoiTowers(numberOfDisc, 1, 3, steps);
		System.out.println(steps.size());
		steps.stream().forEach(System.out::println);
	}

	@SuppressWarnings("unused")
	private static void hanoiTowers(int n, int fromPeg, int toPeg, List<String> steps) {
		if(n == 1) {
			steps.add(fromPeg + " " + toPeg);
			return;
		}
		int unusedPeg = 6 - fromPeg - toPeg;
		hanoiTowers(n - 1, fromPeg, unusedPeg, steps);
		steps.add(fromPeg + " " + toPeg);
		hanoiTowers(n - 1, unusedPeg, toPeg, steps);
	}
}
