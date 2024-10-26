package aq.gym.numbers_numbers_numbers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberAnalizer {

	public static void main(String[] args) {
		Integer[] numbers = new Integer[]{1234, 222, 45, 987};
		printNumberWhichHasMinimumNumberOfDifferentNumbers(numbers);
		printNumberHasSameNumberOfOddAndEvenNumbers(numbers);
		printNumbersWhichHaveNumberInAscOrder(numbers);
		printNumbersWhichHaveDifferentNumbers(numbers);
	}
	
	public static void printNumbersWhichHaveDifferentNumbers(Integer[] numbers) {
		System.out.print("Numbers have different numbers: ");
		for(int number : numbers) {
			boolean isDifferent = true;
			int tmp = number;
			Set<Integer> diffs = new HashSet<Integer>();
			while(true) {
				int sub = tmp % 10;
				isDifferent = diffs.add(sub);
				if(!isDifferent) {
					break;
				}
				tmp /= 10;
				if(tmp > 0 && tmp <= 9) {
					isDifferent = diffs.add(tmp);
					break;
				}
			}
			if(isDifferent) {
				System.out.print(number + " ");
			}
		}
		System.out.println();
	}
	
	public static void printNumbersWhichHaveNumberInAscOrder(Integer[] numbers) {
		System.out.print("Numbers in asc order: ");
		for(int number : numbers) {
			boolean isAsc = true;
			int tmp = number;
			List<Integer> ascNums = new ArrayList<Integer>();
			while(true) {
				int sub = tmp % 10;
				ascNums.add(sub);
				tmp /= 10;
				if(tmp > 0 && tmp <= 9) {
					ascNums.add(tmp);
					break;
				}
			}
			for(int i = 0; i < ascNums.size() - 1; i++) {
				if(ascNums.get(i) < ascNums.get(i + 1)) {
					isAsc = false;
					break;
				}
			}
			if(isAsc) {
				System.out.print(number + " ");
				isAsc = true;
			}
		}
		System.out.println();
	}
	
	public static void printNumberHasSameNumberOfOddAndEvenNumbers(Integer[] numbers) {
		System.out.print("Numbers which have same number of odd and even numbers: ");
		for(int number : numbers) {
			List<Integer> evens = new ArrayList<Integer>();
			List<Integer> odds = new ArrayList<Integer>();
			int tmp = number;
			while(tmp > 0) {
				int sub = tmp % 10;
				if(sub % 2 == 0) {
					evens.add(sub);
				} else {
					odds.add(sub);
				}
				tmp /= 10;
				if(tmp > 0 && tmp <= 9) {
					if(tmp % 2 == 0) {
						evens.add(tmp);
					} else {
						odds.add(tmp);
					}
					break;
				}
			}
			if(evens.size() == odds.size()) {
				System.out.print(number + " ");
			}
		}
		System.out.println();
	}
	
	public static void printNumberWhichHasMinimumNumberOfDifferentNumbers(Integer[] numbers) {
		int minNumberOfNumbers = Integer.MAX_VALUE;
		int minNumber = 0;
		for(int number : numbers) {
			Set<Integer> diffs = new HashSet<>();
			int tmp = number;
			int min = 0;
			while(tmp > 0) {
				int sub = tmp % 10;
				boolean isDifferentNumber = diffs.add(sub);
				if(diffs.size() > 0 && isDifferentNumber) 
					min++;
				tmp = tmp / 10;
				if(tmp >= 0 && tmp <= 9) {
					isDifferentNumber = diffs.add(tmp);
					if(diffs.size() > 0 && isDifferentNumber) 
						min++;
					break;
				}
			}
			if(min < minNumberOfNumbers) {
				minNumber = number;
				minNumberOfNumbers = min;
			}
		}
		System.out.println("Number which has minimum number of differnt numbers: " + minNumber);
	}
}
