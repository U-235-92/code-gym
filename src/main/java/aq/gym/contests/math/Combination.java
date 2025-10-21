package aq.gym.contests.math;

import java.util.Arrays;
import java.util.Scanner;

public class Combination {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] nums = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
		scanner.close();
		int n = nums[0];
		int k = nums[1];
		if(n < 1 || n > 7) {
			throw new IllegalArgumentException();
		}
		if(k < 1 || k > 7) {
			throw new IllegalArgumentException();
		}
		int combination = factorial(n) / (factorial(k) * factorial(n - k));
		System.out.println(combination);
	}

	private static int factorial(int n) {
		if(n <= 1) {
			return 1;
		}
		int next = n * factorial(n - 1);
		return next;
	}
}
