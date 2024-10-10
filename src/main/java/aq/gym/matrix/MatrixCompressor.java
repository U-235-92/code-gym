package aq.gym.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixCompressor {

	public static void main(String[] args) {
		int[][] matrix = { { 0, 2, 3, 4 }, { 0, 5, 6, 7 }, { 0, 8, 0, 9 }, { 0, 0, 0, 0 } };
		deleteAllRowsAndColumnsContainsZero(matrix);
	}
	
	private static void deleteAllRowsAndColumnsContainsZero(int[][] matrix) {
		final int[] filter = new int[matrix[0].length];
		List<Integer> zeroColumnIndexes = new ArrayList<>();
		List<Integer> matriWithoutZeroRowsAndColumns = new ArrayList<>();
		//filter all zero rows
		List<Integer> matrixWithoutZeroRows = Arrays
				.stream(matrix)
				.filter(array -> Arrays.compare(array, filter) != 0)
				.flatMap(array -> Arrays.stream(array).boxed())
				.collect(Collectors.toList());
		//get and store zero column indexes
		for(int i = 0; i < matrix[0].length; i++) {			
			int zeroColumnController = 0;
			int zeroColumControllerLimit = (int) Math.sqrt(matrixWithoutZeroRows.size());
			int step = matrix[i].length;
			for(int j = i; j < matrixWithoutZeroRows.size(); j += step) {
				if(matrixWithoutZeroRows.get(j) == 0) 
					zeroColumnController++;
				else 
					zeroColumnController = 0;
			}
			if(zeroColumnController == zeroColumControllerLimit) {
				int zeroColumnIndex = i;
				zeroColumnIndexes.add(zeroColumnIndex);
			}
		}
		//filter all zero columns
		for(int i = 0; i < matrixWithoutZeroRows.size(); i++) {
			for(int j = 0; j < zeroColumnIndexes.size(); j++) {
				int zeroColumnIndex = i % matrix[0].length;
				if(zeroColumnIndex != zeroColumnIndexes.get(j)) {
					matriWithoutZeroRowsAndColumns.add(matrixWithoutZeroRows.get(i));
					break;
				}
			}
		}
		//build and print matrix without zero rows and columns
		int rows = (int) Math.sqrt(matriWithoutZeroRowsAndColumns.size());
		int columns = (int) Math.ceil(Math.sqrt(matriWithoutZeroRowsAndColumns.size()));
		if(columns > 0) {
			int[] numbers = matriWithoutZeroRowsAndColumns.stream().mapToInt(number -> number.intValue()).toArray();
			int[][] result = null;
			if(columns >= 2) {
				result = new int[rows][columns];
				for(int i = 0; i < result.length; i++) {
					System.arraycopy(numbers, i * columns, result[i], 0, columns);
				}
			} else {
				result = new int[1][columns];
				System.arraycopy(numbers, 0, result[0], 0, columns);
			}
			for(int[] array : result) {
				System.out.println(Arrays.toString(array));
			}
		} else {
			System.out.println("There is no result!");
		}
	}

}
