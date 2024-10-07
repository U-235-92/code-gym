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
import static aq.gym.numbers_numbers_numbers.Palindrome.printPalindromeFrom;
import static aq.gym.numbers_numbers_numbers.FrequensyNumbers.printSortedNumbersByFrequensy;
import static aq.gym.numbers_numbers_numbers.AverageNumberLength.printNumbersWhichMinAverageLength;
import static aq.gym.numbers_numbers_numbers.NumberAnalizer.printNumberWhichHasMinimumNumberOfDifferentNumbers;
import static aq.gym.numbers_numbers_numbers.NumberAnalizer.printNumberHasSameNumberOfOddAndEvenNumbers;
import static aq.gym.numbers_numbers_numbers.NumberAnalizer.printNumbersWhichHaveNumberInAscOrder;
import static aq.gym.numbers_numbers_numbers.NumberAnalizer.printNumbersWhichHaveDifferentNumbers;

public class Main {

	public static void main(String[] args) {
		//1 2 3 4 5 6 7 8 9 555 0 999 81 92 888 129 5 73 4 56 5 4224 4223 5 5885 123 345 222 8 85 59 99 127 (args[])
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
		printPalindromeFrom(numbers);
		printSortedNumbersByFrequensy(numbers);
		printNumbersWhichMinAverageLength(numbers);
		printNumberWhichHasMinimumNumberOfDifferentNumbers(numbers);
		printNumberHasSameNumberOfOddAndEvenNumbers(numbers);
		printNumbersWhichHaveNumberInAscOrder(numbers);
		printNumbersWhichHaveDifferentNumbers(numbers);
	}
}
