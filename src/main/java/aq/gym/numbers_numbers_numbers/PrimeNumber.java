package aq.gym.numbers_numbers_numbers;

import java.util.stream.Stream;

public class PrimeNumber {

	public static void printPrimeNumbersFrom(Integer[] numbers) {
		System.out.print("Prime numbers: ");
		for(int number : numbers) {
			boolean isPrime = true;
			if(number == 0 || number == 1) {
				continue;
			}
			if(number == 2) {
				System.out.print(number + " ");
				continue;
			}
			for(int i = 2; i < number; i++) {
				if(number % i == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) {
				System.out.print(number + " ");
			} 
			isPrime = true;
		}
		System.out.println();
	}
	
	public static void printGeneratedPrimeNumbers(int limit) {
		System.out.print("Generated prime numbers: ");
		Integer[] numbers = Stream.iterate(0, n -> n + 1).limit(limit).toArray(Integer[]::new);
		int NOT_PRIME = -1;
		for(int i = 2; i < numbers.length; i++) {
			if(numbers[i] != NOT_PRIME) {
				System.out.print(numbers[i] + " ");
				int startPositionToMarkNotPrime = i * i;
				int markStep = i;
				for(int j = startPositionToMarkNotPrime; j < numbers.length; j += markStep) {
					numbers[j] = NOT_PRIME;
				}
			}
		}
		System.out.println();
	}
}
