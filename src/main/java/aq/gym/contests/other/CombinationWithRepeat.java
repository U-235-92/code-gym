package aq.gym.contests.other;

import java.util.Arrays;
import java.util.Scanner;

public class CombinationWithRepeat {

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
		int combination = factorial(n + k - 1) / (factorial(k) * (factorial(n - 1)));
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
