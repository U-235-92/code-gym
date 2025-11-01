package aq.gym.contests.t_bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OddEvenNumbersLine {

//	https://edu.tbank.ru/selection/76378fbd-1998-48fa-944e-eb736d321f11/practice/244/task/6
	public static void main(String[] args) {
		int[] numbers = getNumbers();
		tryToSwapNumbersInLine(numbers);
	}

	private static int[] getNumbers() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] numbers = new int[n];
		for(int i = 0; i < n; i++) {
			numbers[i] = sc.nextInt();
		}
		sc.close();
		return numbers;
	}
	
	private static void tryToSwapNumbersInLine(int[] numbers) {
		List<Integer> evenNumbersOnWrongPosition = new ArrayList<>();
		List<Integer> oddNumbersOnWrongPosition = new ArrayList<>();
		for(int i = 0; i < numbers.length; i++) {
			int number = numbers[i], position = i + 1;
			if(isEvenNumberOnWrongPosition(number, position)) {
				evenNumbersOnWrongPosition.add(number);
				continue;
			}
			if(isOddNumberOnWrongPosition(number, position)) {
				oddNumbersOnWrongPosition.add(number);
				continue;
			}
		}
		if(hasOnlyOnePair(evenNumbersOnWrongPosition, oddNumbersOnWrongPosition)) {
			System.out.printf("%d %d", oddNumbersOnWrongPosition.get(0), evenNumbersOnWrongPosition.get(0));
		} else {
			System.out.printf("%d %d", -1, -1);
		}
	}
	
	private static boolean isEvenNumberOnWrongPosition(int number, int position) {
		return number % 2 == 0 && position % 2 != 0;
	}
	
	private static boolean isOddNumberOnWrongPosition(int number, int position) {
		return number % 2 != 0 && position % 2 == 0;
	}
	
	private static boolean hasOnlyOnePair(List<Integer> evenNumbersOnWrongPosition, List<Integer> oddNumbersOnWrongPosition) {
		return evenNumbersOnWrongPosition.size() == 1 && oddNumbersOnWrongPosition.size() == 1;
	}
}
