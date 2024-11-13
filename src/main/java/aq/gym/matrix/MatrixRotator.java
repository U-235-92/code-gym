package aq.gym.matrix;

import java.util.Arrays;

public class MatrixRotator {

	public static void main(String[] args) {
		System.out.println("Before rotate:");
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		rotate(matrix, "left");
	}

	public static void rotate(int[][] matrix, String direction) {
		int[][] rotate = new int[matrix.length][matrix[0].length];
		switch (direction) {
		case "left":
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix[i].length; j++) {
					int k = matrix.length - 1 - j;
					int m = i;
					rotate[k][m] = matrix[i][j];
				}
			}
			System.out.println("After "+ direction + " rotate:");
			for(int[] row : rotate) {
				System.out.println(Arrays.toString(row));
			}
			break;
		case "right":
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix[i].length; j++) {
					int k = j;
					int m = matrix.length - 1 - i;
					rotate[k][m] = matrix[i][j];
				}
			}
			System.out.println("After "+ direction + " rotate:");
			for(int[] row : rotate) {
				System.out.println(Arrays.toString(row));
			}
			break;
		default:
			System.out.println("Wrong direction");
		}
	}
}
