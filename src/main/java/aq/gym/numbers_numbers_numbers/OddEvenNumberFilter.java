package aq.gym.numbers_numbers_numbers;

public class OddEvenNumberFilter {

	public static void main(String[] args) {
		Integer[] numbers = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 555, 0, 999, 81, 92, 888, 129, 5, 73, 4, 56, 5, 4224, 4223, 5, 5885, 123, 345, 222, 8, 85, 59, 99, 127};
		printEvenNumbers(numbers);
		printOddNumbers(numbers);
	}
	
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
