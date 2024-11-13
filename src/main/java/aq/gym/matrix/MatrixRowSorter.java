package aq.gym.matrix;

import java.util.Arrays;
import java.util.Comparator;

public class MatrixRowSorter {

	public static void main(String[] args) {
		int[][] matrix = { { 10, 2, 35 }, { 94, 58, 6 }, { 7, 85, 9 } };
		System.out.println("Before:");
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("After:");
		sortRowMatrixBySumRowElement(matrix);
	}

	private static void sortRowMatrixBySumRowElement(int[][] matrix) {
		Comparator<int[]> rowComparator = (arr1, arr2) -> {
			int x = Arrays.stream(arr1).reduce(0, (a, b) -> a + b);
			int y = Arrays.stream(arr2).reduce(0, (a, b) -> a + b);
			return Integer.compare(x, y);
		};
		Arrays.sort(matrix, rowComparator);
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}
}
