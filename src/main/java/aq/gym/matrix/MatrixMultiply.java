package aq.gym.matrix;

import java.util.Arrays;

public class MatrixMultiply {

	public static void main(String[] args) {
		int[][] a = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		int[][] b = new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		int[][] c = multiply(a, b);
		for (int[] row : c) {
			System.out.println(Arrays.toString(row));
		}
	}

	private static int[][] multiply(int[][] a, int[][] b) {
		int aRows = a.length, aColumns = a[0].length;
		int bRows = b.length, bColumns = b[0].length;
		if (aColumns != bRows)
			throw new IllegalArgumentException("Incopatible parameters of matrixes");
		int[][] result = new int[aRows][bColumns];
		for (int i = 0; i < aRows; i++) {
			for (int j = 0; j < bColumns; j++) {
				for (int s = 0; s < a[i].length; s++) {
					result[i][j] += a[i][s] * b[s][j];
				}
			}
		}
		return result;
	}

}
