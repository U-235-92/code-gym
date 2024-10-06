package aq.gym.numbers_numbers_numbers;

import java.util.Arrays;
import static aq.gym.numbers_numbers_numbers.OddEvenNumberFilter.printEvenNumbers;
import static aq.gym.numbers_numbers_numbers.OddEvenNumberFilter.printOddNumbers;
import static aq.gym.numbers_numbers_numbers.MinMaxNumber.printMax;
import static aq.gym.numbers_numbers_numbers.MinMaxNumber.printMin;
import static aq.gym.numbers_numbers_numbers.NotSameNumbersInNumber.printNotSameNumber;
import static aq.gym.numbers_numbers_numbers.PrimeNumber.printGeneratedPrimeNumbers;
import static aq.gym.numbers_numbers_numbers.PrimeNumber.printPrimeNumbersFrom;
import static aq.gym.numbers_numbers_numbers.LuckyNumber.printGeneratedLuckyNumbers;
import static aq.gym.numbers_numbers_numbers.LuckyNumber.printFiltredLuckyNumbersFrom;

public class Main {

	public static void main(String[] args) {
		//1 2 3 4 5 7 8 9 555 0 999 81 92 888 129 73 4 56 4224 4223 5885 123 345 222 85 59 99 127 (args)
		Integer[] numbers = Arrays.stream(args).map(str -> Integer.valueOf(str).intValue()).toArray(Integer[]::new);
		System.out.println("Orig: " + Arrays.toString(numbers));
		printEvenNumbers(numbers);
		printOddNumbers(numbers);
		printMin(numbers);
		printMax(numbers);
		printNotSameNumber(numbers);
		printPrimeNumbersFrom(numbers);
		printGeneratedPrimeNumbers(20);
		printFiltredLuckyNumbersFrom(numbers);
		printGeneratedLuckyNumbers(20);
	}
}
