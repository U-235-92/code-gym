package aq.gym.numbers_numbers_numbers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class LuckyNumber {

	public static void printFiltredLuckyNumbersFrom(Integer[] numbers) {
		Set<Integer> guessesInfinityCalculate = new HashSet<>();
		System.out.print("Lucky numbers: ");
		for(int number : numbers) {
			if(number == 0) {
				continue;
			}
			int tmp = number;
			int luckyCheck = 0;
			boolean isContinueCalculate = false;
			while(luckyCheck != 1 || luckyCheck != number) {
				if(tmp <= 9 && tmp > 0) {
					luckyCheck = luckyCheck + (tmp * tmp);
					isContinueCalculate = guessesInfinityCalculate.add(luckyCheck);
					if(!isContinueCalculate) {
						break;
					}
					tmp = luckyCheck;
					luckyCheck = 0;
				} else {
					luckyCheck = luckyCheck + ((tmp % 10) * (tmp % 10));
					tmp = tmp / 10;
					if(tmp <= 9 && tmp > 0) {
						luckyCheck = luckyCheck + (tmp * tmp);
						isContinueCalculate = guessesInfinityCalculate.add(luckyCheck);
						if(!isContinueCalculate) {
							break;
						}
						if(luckyCheck == 1 || luckyCheck == number) 
							break;
						tmp = luckyCheck;
						luckyCheck = 0;
					} 
				}
			}
			if(luckyCheck == 1) {
				System.out.print(number + " ");
			}
		}
		System.out.println();
	}
	
	public static void printGeneratedLuckyNumbers(int limit) {
		Integer[] numbers = Stream.iterate(1, n -> n = n + 1).limit(limit).toArray(Integer[]::new);
		int NOT_LUCKY = -1;
		System.out.print("Generated lucky numbers: ");
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] == 1) {
				System.out.print(numbers[i] + " ");
			} else if(numbers[i] == 2) {
				int step = 2;
				for(int j = i; j < numbers.length; j += step) {
					numbers[j] = NOT_LUCKY;
				}
			} else {
				if(numbers[i] != NOT_LUCKY) {
					System.out.print(numbers[i] + " ");
					int step = numbers[i];
					for(int j = i - 1; j < numbers.length; j += step) {
						numbers[j] = NOT_LUCKY;
					}
				}
			}
		}
		System.out.println();
	}
}
