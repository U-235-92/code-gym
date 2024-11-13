package aq.gym.algorithms_and_structures.sort.choose_sort;

import java.util.Arrays;

public class ChooseSort {

	public static void main(String[] args) {
		int[] numbers = {1, 8, 5, 7, 3, 4, 2, 8, 9, 6};
		System.out.println("Before sort: " + Arrays.toString(numbers));
		sort(numbers);
		System.out.println("After sort:  " + Arrays.toString(numbers));
	}

	private static void sort(int[] numbers) {
		for(int i = 0; i < numbers.length; i++) {
			int smalest = numbers[i];
			int smalestIndex = i;
			for(int j = i; j < numbers.length; j++) {
				if(numbers[j] < smalest) {
					smalest = numbers[j];
					smalestIndex = j;
				}
			}
			if(smalestIndex != i) {
				int old = numbers[i];
				numbers[i] = smalest;
				numbers[smalestIndex] = old;
			}
		}
	}
}
