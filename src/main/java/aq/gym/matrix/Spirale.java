package aq.gym.matrix;

import java.util.Arrays;

public class Spirale {

	public static void main(String[] args) {
//		int[][] spirale = new int[][] {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
//		int[][] spirale = new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
//		int[][] spirale = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//		int[][] spirale = new int[][] {{1, 2}, {3, 4}};
//		int[][] spirale = new int[][] {{1}};
		int[][] spirale = generate(10);
		print("Before:" ,spirale);
		spirale = spirale(spirale);
		print("After:", spirale);
	}
	
	private static int[][] generate(int size) {
		int[][] matrix = new int[size][size];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = (int) (Math.random() * 10);
			}
		}
		return matrix;
	}
	
	private static int[][] spirale(int[][] matrix) {
		int[][] spirale = new int[matrix.length][matrix.length];
		int[] matrixArrayView = Arrays.stream(matrix)
				.flatMap(arr -> Arrays.stream(arr).boxed())
				.mapToInt(Integer::intValue)
				.sorted()
				.toArray();
		int i = 0, j = 0, k = 0, radius = matrix.length - 1, startI = 0, startJ = 0;
		boolean toRight = true, toDown = false, toLeft = false, toUp = false, isContinueMakeSpirle = true;
		while(isContinueMakeSpirle) { //radius > 0
			while(toRight) {
				if(!isContinueMakeSpirle) 
					break;
				spirale[i][j] = matrixArrayView[k];
				if(j >= radius) {
					toRight = false;
					toDown = true;
					i++; k++;
					if(k == matrixArrayView.length) {
						isContinueMakeSpirle = false;
					}
				} else {
					j++; k++;
				}
			}
			while(toDown) {
				if(!isContinueMakeSpirle) 
					break;
				spirale[i][j] = matrixArrayView[k];
				if(i >= radius) {
					toDown = false;
					toLeft = true;
					j--; k++;
					if(k == matrixArrayView.length) {
						isContinueMakeSpirle = false;
					}
				} else {
					i++; k++;
				}
			}
			while(toLeft) {
				if(!isContinueMakeSpirle) 
					break;
				spirale[i][j] = matrixArrayView[k];
				if(j == startJ) {
					toLeft = false;
					toUp = true;
					i--; k++;
					if(k == matrixArrayView.length) {
						isContinueMakeSpirle = false;
					}
				} else {
					j--; k++;
				}
			}
			while(toUp) {
				if(!isContinueMakeSpirle) 
					break;
				spirale[i][j] = matrixArrayView[k];
				if(i == startI + 1) {
					toUp = false;
					toRight = true;
					startI++;
					startJ++;
					i = startI;
					j = startJ;
					radius--;
					k++;
					if(k == matrixArrayView.length) {
						isContinueMakeSpirle = false;
					}
				} else {
					i--; k++;
				}
			}
		}
		return spirale;
	}
	
	private static void print(String msg, int[][] matrix) {
		System.out.println(msg);
		Arrays.stream(matrix).forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
}
