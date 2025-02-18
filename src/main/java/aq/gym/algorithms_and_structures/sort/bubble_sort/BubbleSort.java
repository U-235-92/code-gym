package aq.gym.algorithms_and_structures.sort.bubble_sort;

import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		int[] ints = {1, 5, 2, 0, -1, 10};
		int[] intsCopy = Arrays.copyOf(ints, ints.length); 
		sort(ints);
		sort2(intsCopy);
		System.out.println(Arrays.toString(ints));
		System.out.println(Arrays.toString(intsCopy));
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
	
	private static void sort2(int[] ints) {
		for(int i = 0; i < ints.length; i++) {
			boolean wasReplace = false;
			for(int j = 0; j < (ints.length - 1) - i; j++) {
				if(ints[j] > ints[j + 1]) {
					swap(j, ints);
					wasReplace = true;
				}
			}
			if(wasReplace == false) {				
				return;
			}
		}
	}
	
	private static void swap(int index, int[] ints) {
		int tmp = ints[index];
		ints[index] = ints[index + 1];
		ints[index + 1] = tmp;
	}
}
