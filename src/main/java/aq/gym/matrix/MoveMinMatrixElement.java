package aq.gym.matrix;

import java.util.Arrays;

public class MoveMinMatrixElement {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println("Before:");
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("After:");
		moveRowAndColumnOfMinElementToPosition(matrix, 2, 2);
	}

	private static void moveRowAndColumnOfMinElementToPosition(int[][] matrix, int targetRowIndex, int targetColumnIndex) {
		if(targetRowIndex >= matrix.length || targetColumnIndex >= matrix[0].length) {
			System.out.println("Wrong parameters of row or column or both of them!");
			return;
		}
		//look for min element of matrix
		//and row and column of this one
		int minRowIndex = 0, minColumnIndex = 0, minElement = Integer.MAX_VALUE;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] < minElement) {
					minElement = matrix[i][j];
					minRowIndex = i;
					minColumnIndex = j;
				}
			}
		}
		//replace row of min element to target row
		int[] minRowArray = matrix[minRowIndex];
		int[] targetRowArray = matrix[targetRowIndex];
		matrix[minRowIndex] = targetRowArray;
		matrix[targetRowIndex] = minRowArray;
		//replace column of min element to target column
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(j == minColumnIndex) {
					int minColumnElement = matrix[i][j];
					int targetColumnElement = matrix[i][targetColumnIndex];
					matrix[i][targetColumnIndex] = minColumnElement;
					matrix[i][j] = targetColumnElement;
				}
			}
		}
		//print result
		for(int[] array : matrix) {
			System.out.println(Arrays.toString(array));
		}
	}
}
