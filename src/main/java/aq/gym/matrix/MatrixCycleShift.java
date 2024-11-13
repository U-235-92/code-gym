package aq.gym.matrix;

import java.util.Arrays;

public class MatrixCycleShift {

	public static void main(String[] args) {
		shift("R", 10);
	}

	private static void shift(String direction, int shift) {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println("Before:");
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
		if(shift != 0) {
			int[] array = new int[matrix.length * matrix[0].length];
			if(shift > array.length) {
				while(shift > array.length) {
					shift = shift - array.length;
				}
			}
			switch (direction) {
			case "L":
				fillArray(array, matrix, "horizontal");
				shiftArray(array, shift, "L");
				fillMatrix(array, matrix, "horizontal");
				break;
			case "R":
				fillArray(array, matrix, "horizontal");
				shiftArray(array, shift, "R");
				fillMatrix(array, matrix, "horizontal");
				break;
			case "U":
				fillArray(array, matrix, "vertical");
				shiftArray(array, shift, "U");
				fillMatrix(array, matrix, "vertical");
				break;
			case "D":
				fillArray(array, matrix, "vertical");
				shiftArray(array, shift, "D");
				fillMatrix(array, matrix, "vertical");
				break;
			}
		}
		System.out.println("After:");
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}

	private static void fillArray(int[] array, int[][] matrix, String direction) {
		switch (direction) {
		case "horizontal":
			for (int i = 0; i < matrix.length; i++) {
				System.arraycopy(matrix[i], 0, array, i * matrix[i].length, matrix[i].length);
			}
			break;
		case "vertical":
			int k = 0, i = 0, j = 0;
			while (k < array.length) {
				for (i = 0; i < matrix.length; i++) {
					array[k++] = matrix[i][j];
				}
				j++;
				i = 0;
			}
			break;
		}
	}

	private static void shiftArray(int[] array, int shift, String direction) {
		switch (direction) {
		case "L":
		case "U":
			shiftArray0(array, 0, shift - 1);
			shiftArray0(array, shift, array.length - 1);
			shiftArray0(array, 0, array.length - 1);
			break;
		case "R":
		case "D":
			shiftArray0(array, 0, array.length - 1 - shift);
			shiftArray0(array, array.length - shift, array.length - 1);
			shiftArray0(array, 0, array.length - 1);
			break;
		}
	}
	
	private static void shiftArray0(int[] array, int left, int right) {
		int i = left, j = right;
		while(i <= j) {
			int tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
			i++; j--;
		}
	}
	
	private static void fillMatrix(int[] array, int[][] matrix, String direction) {
		switch (direction) {
		case "horizontal":
			for (int i = 0; i < matrix.length; i++) {
				System.arraycopy(array, i * matrix[i].length, matrix[i], 0, matrix[i].length);
			}
			break;
		case "vertical":
			int k = 0, i = 0, j = 0;
			while (k < array.length) {
				for (i = 0; i < matrix.length; i++) {
					matrix[i][j] = array[k++];
				}
				j++; i = 0;
			}
			break;
		}
	}
}
