package aq.gym.matrix;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MaxElementDiagonal {

	public static void main(String[] args) {
		System.out.println("Before rotate:");
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("After");
		putMaxElementInDdiagonalByDesc(matrix);
	}

	private static void putMaxElementInDdiagonalByDesc(int[][] matrix) {
		int diagonal = matrix.length;
		//get max elements
		List<Integer> maxValues = Arrays
				.stream(matrix)
				.flatMap(array -> Arrays.stream(array).boxed())
				.sorted(Comparator.reverseOrder())
				.limit(diagonal)
				.collect(Collectors.toList());
		//put max elements on diagonal
		for(int i = 0; i < diagonal; i++) {
			int toReplace = matrix[i][i];
			int max = maxValues.get(i);
			boolean isElementReplaced = false;
			for(int k = 0; k < matrix.length; k++) {
				for(int m = 0; m < matrix[k].length; m++) {
					if(matrix[k][m] == max) {
						matrix[i][i] = max;
						matrix[k][m] = toReplace;
						isElementReplaced = true;
						break;
					}
				}
				if(isElementReplaced) {
					isElementReplaced = false;
					break;
				}
			}
		}
		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}
}
