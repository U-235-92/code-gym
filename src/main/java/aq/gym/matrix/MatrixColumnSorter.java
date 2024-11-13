package aq.gym.matrix;

import java.util.Arrays;
import java.util.Comparator;

public class MatrixColumnSorter {

	public static void main(String[] args) {
		int[][] matrix = { { 10, 2, 35 }, { 94, 58, 6 }, { 7, 85, 9 } };
		System.out.println("Before:");
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("After:");
		sortColumnsBySumAsc(matrix);
	}
	
	private static void sortColumnsBySumAsc(int[][] matrix) {
		matrix = turnRightMatrix(matrix);
		sortMatrix(matrix);
		matrix = turnLeftMatrix(matrix);
		printResult(matrix);
	}

	private static int[][] turnRightMatrix(int[][] matrix) {
		int[][] rotate = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				int k = j;
				int m = matrix.length - 1 - i;
				rotate[k][m] = matrix[i][j];
			}
		}
		return rotate;
	}

	private static void sortMatrix(int[][] matrix) {
		Comparator<int[]> rowComparator = (arr1, arr2) -> {
			int x = Arrays.stream(arr1).reduce(0, (a, b) -> a + b);
			int y = Arrays.stream(arr2).reduce(0, (a, b) -> a + b);
			return Integer.compare(x, y);
		};
		Arrays.sort(matrix, rowComparator);
	}

	private static int[][] turnLeftMatrix(int[][] matrix) {
		int[][] rotate = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				int k = matrix.length - 1 - j;
				int m = i;
				rotate[k][m] = matrix[i][j];
			}
		}
		return rotate;
	}

	private static void printResult(int[][] matrix) {
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}
}
