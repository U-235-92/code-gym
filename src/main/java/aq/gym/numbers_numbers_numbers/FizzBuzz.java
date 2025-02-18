package aq.gym.numbers_numbers_numbers;

import java.util.stream.IntStream;

public class FizzBuzz {

	public static void main(String[] args) {
		fizzBuzz();
	}

	public static void fizzBuzz() {
		IntStream.rangeClosed(1, 100).forEach(num -> {
			if(num % 3 == 0 && num % 5 == 0)
				System.out.print("[FizzBuzz]");
			else if(num % 3 == 0)
				System.out.print("[Fizz]");
			else if(num % 5 == 0)
				System.out.print("[Buzz]");
		});
	}
}
