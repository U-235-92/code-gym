package aq.gym.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MatrixSaddlePoint {

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println("Before:");
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		printSaddlePoint(matrix);
	}

	private static void printSaddlePoint(int[][] matrix) {
		//look for min element of row and store it
		List<Integer> minRowElements = Arrays
				.stream(matrix)
				.map(array -> Arrays.stream(array).min().getAsInt())
				.collect(Collectors.toList());
		//look for max element of column and store it
		List<Integer> maxColumnElements = new ArrayList<>();
		int j = 0;
		while(j < matrix[0].length) {
			int max = 0;
			for(int i = 0; i < matrix.length - 1; i++) {
				max = matrix[i][j];
				int next = matrix[i + 1][j];
				if(next > max) {
					max = next;
				}
			}			
			maxColumnElements.add(max);
			j++;
		}
		//get set of saddle element
		Set<Integer> saddleSet = new HashSet<Integer>(maxColumnElements);
		saddleSet.retainAll(minRowElements);
		//print result
		System.out.println("Saddle element of matrix: " + saddleSet);
	}
}
