package aq.gym.numbers_numbers_numbers;

public class Factors {

	public static void main(String[] args) {
		factors(3757208L);
	}

	private static void factors(long number) {
		for(long factor = 2; factor <= number / factor; factor++) {
			while(number % factor == 0) {
				number = number / factor;
				System.out.print(factor + " ");
			}
		}
		System.out.println(number);
	}
}
