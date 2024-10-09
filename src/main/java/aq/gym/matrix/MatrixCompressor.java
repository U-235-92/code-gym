package aq.gym.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixCompressor {

	public static void main(String[] args) {
		int[][] matrix = { { 0, 2, 3, 4 }, { 0, 0, 0, 0 }, { 0, 8, 0, 9 }, { 0, 0, 0, 0 } };
		deleteAllRowsAndColumnsContainsZero(matrix);
	}
	
	private static void deleteAllRowsAndColumnsContainsZero(int[][] matrix) {
		List<Integer> numbers = Arrays.stream(matrix).flatMap(array -> Arrays.stream(array).boxed()).collect(Collectors.toList());
		
		
//		List<Integer> columnsToDelete = new ArrayList<Integer>();
//		List<Integer> rowsToDelete = new ArrayList<Integer>();
//		int step = matrix.length;
//		int[] zeroRowController = new int[matrix.length];
//		int zeroColumnController = 0;
//		for(int i = 0; i < matrix.length; i++) {
//			if(Arrays.compare(zeroRowController, matrix[i]) == 0) {
//				int rowToDelete = i;
//				rowsToDelete.add(rowToDelete);
//			}
//			int j = 0;
//			for(j = 0; j < numbers.size(); j += step) {
//				if(numbers.get(j) == 0) {
//					zeroColumnController++;
//				}
//			}
//			if(zeroColumnController == matrix.length) {				
//				int columnToDelete = j % matrix.length;
//				columnsToDelete.add(columnToDelete);
//			}
//		}
//		int row = 0, column = 0;
//		if(rowsToDelete.size() > 0)
//			row = matrix.length - rowsToDelete.size();
//		if(columnsToDelete.size() > 0)
//			column = matrix[0].length - columnsToDelete.size();
//		int[][] result = new int[row][column];
//		for(int i = 0; i < matrix.length; i++) {
//			for(int k = 0; k < rowsToDelete.size(); k++) {
//				if(rowsToDelete.get(k) == i) {	
//					break;
//				} 
//			}
//			for(int j = 0; j < matrix[i].length; j++) {
//				for(int m = 0; m < columnsToDelete.size(); m++) {
//					if(columnsToDelete.get(m) == j) {
//						continue;
//					} else {
//						System.out.print(matrix[i][j] + " ");
//					}
//				}
//			}
//			System.out.println();
//		}
	}

}
