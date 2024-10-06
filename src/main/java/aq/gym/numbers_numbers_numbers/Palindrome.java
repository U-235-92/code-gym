package aq.gym.numbers_numbers_numbers;

public class Palindrome {

	public static void printPalindromeFrom(Integer[] numbers) {
		System.out.print("Palndromes: ");
		for (int number : numbers) {
			if (number >= 0 && number <= 9) {
				System.out.print(number + " ");
			} else {
				int tmp = number;
				int palindrome = 0;
				while (tmp > 0) {
					palindrome = (palindrome * 10) + (tmp % 10);
					tmp /= 10;
				}
				if (number == palindrome) {
					System.out.print(number + " ");
				}
			}
		}
		System.out.println();
	}
}
