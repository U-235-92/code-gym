package aq.gym.contests.sort;

import java.util.Arrays;
import java.util.Scanner;

public class ChoseSort {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		@SuppressWarnings("unused")
		int n = Integer.valueOf(scanner.nextLine());
		long[] numbers = Arrays.stream(scanner.nextLine().split("\\s")).mapToLong(Long::valueOf).toArray();
		scanner.close();
		sort(numbers);
		Arrays.stream(numbers).forEach(num -> System.out.print(num + " "));
	}

	private static void sort(long[] numbers) {
		for(int i = 0; i < numbers.length; i++) {
			long chose = numbers[i];
			int chosePosition = i;
			for(int j = i; j < numbers.length; j++) {
				if(numbers[j] < chose) {
					chose = numbers[j];
					chosePosition = j;
				}
			}
			if(chose != numbers[i]) {
				long tmp = numbers[i];
				numbers[i] = chose;
				numbers[chosePosition] = tmp;
			}
		}
	}
}
