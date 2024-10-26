package aq.gym.algorithms_and_structures.sort.bubble_sort;

import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		int[] ints = {1, 5, 2, 0, -1, 10};
		sort(ints);
		System.out.println(Arrays.toString(ints));
	}
	
	private static void sort(int[] ints) {
		for(int i = 1; i < ints.length; i++) {
			for(int j = ints.length - 1; j >= i; j--) {
				if(ints[j] < ints[j - 1]) {
					int tmp = ints[j];
					ints[j] = ints[j - 1];
					ints[j - 1] = tmp;
				}
			}
		}
	}
}
