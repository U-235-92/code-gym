package aq.gym.algorithms_and_structures.sort.quick_sort;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		int[] ints = {1, 5, 2, 0, -1, 10, 77, 3, 5, 0};
		sort(ints);
		System.out.println(Arrays.toString(ints));
	}

	private static void sort(int[] ints) {
		sort0(ints, 0, ints.length - 1);
	}

	private static void sort0(int[] ints, int left, int right) {
		int leftRunner = left;
		int rightRunner = right;
		int middleElementValue = ints[(right + left) / 2];
		while(leftRunner <= rightRunner) {
			while(ints[leftRunner] < middleElementValue) leftRunner++;
			while(ints[rightRunner] > middleElementValue) rightRunner--;
			if(leftRunner <= rightRunner) {
				int tmp = ints[rightRunner];
				ints[rightRunner] = ints[leftRunner];
				ints[leftRunner] = tmp;
				leftRunner++; rightRunner--;
			}
		}
		if(leftRunner < right) {
			sort0(ints, leftRunner, right);
		}
		if(rightRunner > left) {
			sort0(ints, left, rightRunner);
		}
	}
}
