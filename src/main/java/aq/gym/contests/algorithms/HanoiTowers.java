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
		hanoiTowers4(numberOfDisc, 1, 4, steps);
		System.out.println(steps.size());
//		steps.stream().forEach(System.out::println);
	}

	@SuppressWarnings("unused")
	private static void hanoiTowers3(int n, int fromPeg, int toPeg, List<String> steps) {
		if(n == 1) {
			steps.add(fromPeg + " " + toPeg);
			return;
		}
		int unusedPeg = 6 - fromPeg - toPeg;
		hanoiTowers3(n - 1, fromPeg, unusedPeg, steps);
		steps.add(fromPeg + " " + toPeg);
		hanoiTowers3(n - 1, unusedPeg, toPeg, steps);
	}
	
	@SuppressWarnings("unused")
	private static void hanoiTowers4(int n, int fromPeg, int toPeg, List<String> steps) {
		int unusedPeg1 = 7 - fromPeg - toPeg;
		int unusedPeg2 = 10 - fromPeg - toPeg - unusedPeg1;
		int k = n - 2;
		if(n == 1) {
			steps.add(fromPeg + " " + toPeg);
			return;
		}
		if(n == 2) {
			steps.add(fromPeg + " " + unusedPeg1);
			steps.add(fromPeg + " " + toPeg);
			steps.add(unusedPeg1 + " " + toPeg);
			return;
		}
		hanoiTowers4(n - 2, fromPeg, unusedPeg1, steps);
		steps.add(fromPeg + " " + unusedPeg1);
		steps.add(unusedPeg1 + " " + toPeg);
		steps.add(fromPeg + " " + toPeg);
		hanoiTowers4(n - 2, unusedPeg1, toPeg, steps);
	}
}
