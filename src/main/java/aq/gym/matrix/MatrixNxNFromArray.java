package aq.gym.matrix;

import java.util.Arrays;

public class MatrixNxNFromArray {

	public static void main(String[] args) {
		int[] array = { 6, 7, 8, 9, 10, 11, 12 };
		printMatrixFromLeftToRight(array);
		printMatrixFromUpToDown(array);
	}

	private static void printMatrixFromLeftToRight(int[] array) {
		int size = calculateMatrixSize(array);
		int[][] matrix = new int[size][size];
		int arrayIndex = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if(arrayIndex < array.length) {	
					matrix[i][j] = array[arrayIndex++];
				} else {
					matrix[i][j] = 0;
				}
			}
		}
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
	}

	private static void printMatrixFromUpToDown(int[] array) {
		int size = calculateMatrixSize(array);
		int[][] matrix = new int[size][size];
		int fillIndex = 0, j = 0;
		int stopIndex = matrix[0].length * matrix.length;
		while(fillIndex < stopIndex) {
			for(int i = 0; i < matrix.length; i++) {
				if(fillIndex < array.length) {					
					matrix[i][j] = array[fillIndex];	
				} else {
					matrix[i][j] = 0;
				}
				fillIndex++;
			}
			j++;
		}
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}

	private static int calculateMatrixSize(int[] array) {
		double size = Math.sqrt(array.length);
		if (size % 1 != 0) {
			return (int) Math.ceil(size);
		}
		return (int) size;
	}
}
