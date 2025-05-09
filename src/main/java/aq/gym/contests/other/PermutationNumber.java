package aq.gym.contests.other;

import java.util.Scanner;

public class PermutationNumber {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		scanner.close();
		if(number < 1 || number > 7) {
			throw new IllegalArgumentException();
		}
		int permutationNumber = permutationNumber(number);
		System.out.println(permutationNumber);
	}
	
	private static int permutationNumber(int number) {
		if(number == 1) {
			return 1;
		}
		int result = number * permutationNumber(number - 1);
		return result;
	}

}
