package aq.gym.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RowColumnRemover {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		removeRowAndColumnByMaxElement(matrix);
	}

	private static void removeRowAndColumnByMaxElement(int[][] matrix) {
		int maxElement = max(matrix);
		int[] array = convert(matrix);
		int[][] result = remove(array, maxElement, matrix.length);
		printResult(result);
	}
	
	private static int max(int[][] matrix) {
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] > max) {
					max = matrix[i][j];
				}
			}
		}
		return max;
	}
	
	private static int[] convert(int[][] matrix) {
		int[] result = new int[matrix[0].length * matrix.length];
		for(int i = 0; i < matrix.length; i++) {
			System.arraycopy(matrix[i], 0, result, i * matrix[i].length, matrix[i].length);
		}
		return result;
	}
	
	private static final int[][] remove(int[] array, int max, int width) {
		final int MARK_TO_DELETE = -1;
		List<Coordinate> coordinates = new ArrayList<>();
		int[][] result = null;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == max) {
				int x = i / width;
				int y = i % width;
				Coordinate coordinate = new Coordinate(x, y);
				coordinates.add(coordinate);
			}
		}
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < coordinates.size(); j++) {
				int x = i / width;
				int y = i % width;
				if(x == coordinates.get(j).x || y == coordinates.get(j).y) {
					array[i] = MARK_TO_DELETE;
					break;
				}
			}
		}
		int[] numbers = Arrays.stream(array).filter(n -> n != MARK_TO_DELETE).map(n -> Integer.valueOf(n)).toArray();
		int size = (int) Math.sqrt(numbers.length);
		if(size >= 2) {
			result = new int[size][size];
			for(int i = 0; i < result.length; i++) {
				System.arraycopy(numbers, i * size, result[i], 0, size);
			}
		} else if (size == 1) {
			result = new int[1][numbers.length];
			System.arraycopy(numbers, 0, result[0], 0, size);
		} else {
			result = new int[0][0];
		}
		return result;
	}
	
	private static void printResult(int[][] matrix) {
		if(matrix.length == 0) {
			System.out.println("All rows and columns were delete!");
		} else {			
			for(int[] numbers : matrix) {
				System.out.println(Arrays.toString(numbers));
			}
		}
	}

	private static class Coordinate {
		private int x;
		private int y;

		public Coordinate(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
