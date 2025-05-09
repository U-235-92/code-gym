package aq.gym.contests.array;

import java.util.Arrays;
import java.util.Scanner;

public class MutlipleAndTransposeMatrix {

	public static void main(String[] args) {
//		int[][] a = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
//		int[][] b = new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } };
//	
//		1 2 1
//		3 4
//		9
//		8
//		
//		2 1 3
//		0 
//		2 
//		1 2 8 
		Scanner scanner = new Scanner(System.in);
		int[] matrixesData = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
		int[][] a = new int[matrixesData[0]][matrixesData[1]];
		int[][] b = new int[matrixesData[1]][matrixesData[2]];
		a = fill(a, scanner);
		b = fill(b, scanner);
		int[][] m = multiply(a, b);
		int[][] t = transpose(m);
		for(int[] x : t) {
			for(int num : x) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
		scanner.close();
	}
	
	private static int[][] fill(int[][] matrix, Scanner scanner) {
		int count = matrix.length, i = 0;
		while(count-- > 0) {
			String line = scanner.nextLine();
			int[] row = Arrays.stream(line.split("\\s")).mapToInt(Integer::valueOf).toArray();
			matrix[i++] = row;
		}
		return matrix;
	}
	
	private static int[][] multiply(int[][] a, int[][] b) {
		int aRow = a.length;
		int aColumn = a[0].length;
		int bRow = b.length;
		int bColumn = b[0].length;
		int[][] result = new int[aRow][bColumn];
		for(int i = 0; i < aRow; i++) {
			for(int j = 0; j < bColumn; j++) {
				for(int k = 0; k < aColumn; k++) {
					result[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return result;
	}
	
	private static int[][] transpose(int[][] matrix) {
		int[][] result = new int[matrix[0].length][matrix.length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				result[j][i] = matrix[i][j];
			}
		}
		return result;
	}
}
