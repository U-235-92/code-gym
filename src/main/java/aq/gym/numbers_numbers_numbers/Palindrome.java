package aq.gym.numbers_numbers_numbers;

public class Palindrome {

	public static void main(String[] args) {
		Integer[] numbers = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 555, 0, 999, 81, 92, 888, 129, 5, 73, 4, 56, 5, 4224, 4223, 5, 5885, 123, 345, 222, 8, 85, 59, 99, 127};
		printPalindromeFrom(numbers);
	}
	
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
