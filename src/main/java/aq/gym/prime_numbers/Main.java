package aq.gym.prime_numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		System.out.println(naiveWay(100));
		System.out.println(sieveWay(100));
		System.out.println(streamWay(100));
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
