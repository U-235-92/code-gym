package aq.gym.numbers_numbers_numbers;

public class NotSameNumbersInNumber {

	public static void main(String[] args) {
		Integer[] numbers = new Integer[]{1234, 222, 45, 987};
		printNotSameNumber(numbers);
	}
	
	public static void printNotSameNumber(Integer[] numbers) {
		System.out.print("Not same numbers [xxx] format: ");
		for(int number : numbers) {
			if(number >= 100 && number <= 999) {
				int tmp = number;
				int a = tmp % 10;
				tmp = tmp / 10;
				int b = tmp % 10;
				tmp = tmp / 10;
				int c = tmp;
				if(a != b && b != c && a != c) {
					System.out.print(number + " ");
				}
			}
		}
		System.out.println();
	}
}
