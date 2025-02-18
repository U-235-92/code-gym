package aq.gym.algorithms_and_structures.sort.merge_sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] ints = {1, 5, 2, 0, -1, 10};
		sort(ints);
		System.out.println(Arrays.toString(ints));
	}

	private static void sort(int[] ints) {
		sort0(ints);
	}
	
	private static void sort0(int[] ints) {
		if(ints.length == 1) {
			return;
		}
		int mid = ints.length / 2;
		int[] left = Arrays.copyOf(ints, mid);
		int[] right = Arrays.copyOfRange(ints, mid, ints.length);
		sort0(left);
		sort0(right);
		merge(left, right, ints);
	}
	
	private static void merge(int[] left, int[] right, int[] orig) {
		int leftIndx = 0, rightIndx = 0, origIndx = 0;
		while(leftIndx < left.length && rightIndx < right.length) {
			if(left[leftIndx] < right[rightIndx]) {
				orig[origIndx] = left[leftIndx];
				leftIndx++;
			} else {
				orig[origIndx] = right[rightIndx];
				rightIndx++;
			}
			origIndx++;
		}
		while(leftIndx < left.length) {
			orig[origIndx] = left[leftIndx];
			leftIndx++; origIndx++;
		}
		while(rightIndx < right.length) {
			orig[origIndx] = right[rightIndx];
			rightIndx++; origIndx++;
		}
	}
}
