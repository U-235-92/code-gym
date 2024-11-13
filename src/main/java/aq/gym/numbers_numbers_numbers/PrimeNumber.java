package aq.gym.numbers_numbers_numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimeNumber {

	public static void main(String[] args) {
		//1 2 3 4 5 6 7 8 9 555 0 999 81 92 888 129 5 73 4 56 5 4224 4223 5 5885 123 345 222 8 85 59 99 127 (args[])
		Integer[] numbers = Arrays.stream(args).map(str -> Integer.valueOf(str).intValue()).toArray(Integer[]::new);
		System.out.println(naiveWay(100));
		System.out.println(sieveWay(100));
		System.out.println(streamWay(100));
		System.out.println(bruteForceWay(100));
		printPrimeNumbersFrom(numbers);
		printGeneratedPrimeNumbers(20);
	}
	
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
	
	public static ArrayList<Integer> bruteForceWay(int limit) {
		ArrayList<Integer> primes = new ArrayList<>();
		for(int i = 2; i <= limit; i++) {
			if(isPrime(i)) {
				primes.add(i);
			}
		}
		return primes;
	}
	
	private static boolean isPrime(int number) {
		for(int i = 2; i < number; i++) {
			if(number % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static ArrayList<Integer> naiveWay(int limit) {
		ArrayList<Integer> primes = new ArrayList<>();
		Integer[] numbers = Stream.iterate(2, number -> number + 1).limit(limit - 1).toArray(Integer[]::new);
		for (int i = 0; i < numbers.length; i++) {
			boolean isPrime = true;
			for (int j = 2; j < numbers[i]; j++) {
				if (numbers[i] == 2) {
					break;
				}
				if (numbers[i] % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				primes.add(numbers[i]);
				isPrime = true;
			}
		}
		return primes;
	}
	
	public static List<Integer> sieveWay(int limit) {
		final int NOT_PRIME = -1;
		Integer[] numbers = Stream.iterate(0, number -> number + 1).limit(limit).toArray(Integer[]::new);
		List<Integer> primes = new ArrayList<Integer>();
		for(int i = 2; i < numbers.length; i++) {
			int number = numbers[i];
			if(number != NOT_PRIME) {
				primes.add(number);
				int position = i * i;
				int step = i;
				for(int j = position; j < numbers.length; j += step) {
					numbers[j] = NOT_PRIME;
				}
			}
		}
		return primes;
	}
	
	public static List<Integer> streamWay(int limit) {
		return IntStream.rangeClosed(2, limit)
			.filter(num -> IntStream.range(2, num).allMatch(inner -> num % inner != 0))
			.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	}
}
