package aq.gym.matrix;

import java.util.Arrays;

public class MatrixSum {

	public static void main(String[] args) {
		int[][] a = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		int[][] b = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		int[][] c = sum(a, b);
		for (int[] row : c) {
			System.out.println(Arrays.toString(row));
		}
	}

	private static int[][] sum(int[][] a, int[][] b) {
		int aRows = a.length, aColumns = a[0].length;
		int bRows = b.length, bColumns = b[0].length;
		if (aRows != bRows || aColumns != bColumns)
			throw new IllegalArgumentException("Incopatible parameters of matrixes");
		int[][] result = new int[aRows][bColumns];
		for (int i = 0; i < aRows; i++) {
			for (int j = 0; j < aColumns; j++) {
				result[i][j] += a[i][j] + b[i][j];
			}
		}
		return result;
	}
}
