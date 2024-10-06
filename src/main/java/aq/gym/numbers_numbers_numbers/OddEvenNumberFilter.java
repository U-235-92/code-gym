package aq.gym.numbers_numbers_numbers;

public class OddEvenNumberFilter {

	public static void printEvenNumbers(Integer[] numbers) {
		System.out.print("Even numbers: ");
		for(int number : numbers) {
			if(number % 2 == 0) {
				System.out.print(number + " ");
			}
		}
		System.out.println();
	}

	public static void printOddNumbers(Integer[] numbers) {
		System.out.print("Odd numbers: ");
		for(int number : numbers) {
			if(number % 2 != 0) {
				System.out.print(number + " ");
			}
		}
		System.out.println();
	}
}
