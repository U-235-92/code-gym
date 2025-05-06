package aq.gym.contests.easy.array;

import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix {

	public static void main(String[] args) {
//		int[][] matrix = {{1,2,3},{4,5,6}};
//		int[][] matrix = {{1,2},{3,4},{5,6}};
//		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
//		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
//		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//		int[][] matrix = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};
//		int[][] matrix = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20}};
//		int[][] matrix = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
//		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9},{10,11,12},{13,14,15}};
//		int[][] matrix = {{1,2},{3,4}};
//		int[][] matrix = {{3},{2}};
		int[][] matrix = {
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{9, 10,11,12},
				{13,14,15,16},
				{17,18,19,20},
				{21,22,23,24}
		};
		List<Integer> spiral = new SpiralMatrix().spiralOrder(matrix);
		System.out.println(spiral);
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> spiral = new LinkedList<>();
		if(matrix.length == 1 && matrix[0].length == 1) {
			spiral.addFirst(matrix[0][0]);
		} else if(matrix.length == 1 && matrix[0].length > 1) {
			int[] line = matrix[0];
			for(int num : line) {
				spiral.addLast(num);
			}
		} else if(matrix.length > 1 && matrix[0].length == 1) {
			int i = 0;
			while(i < matrix.length) {
				spiral.addLast(matrix[i++][0]);
			}
		} else {
			Pair topLeft = new Pair(0, 0);
			Pair downRight = new Pair(matrix.length - 1, matrix[0].length - 1);
			if(isHorizontalBackfire(topLeft, downRight)) {
				for(int j = 0; j < matrix[0].length; j++) {
					spiral.addLast(matrix[0][j]);
				}
				for(int j = matrix[0].length - 1; j >= 0; j--) {
					spiral.addLast(matrix[1][j]);
				}
				return spiral;
			}
			if(isVerticalBackfire(topLeft, downRight)) {
				spiral.addLast(matrix[0][0]); spiral.addLast(matrix[0][1]);
				for(int i = 1; i < matrix.length - 1; i++) {
					spiral.addLast(matrix[i][1]);
				}
				spiral.addLast(matrix[matrix.length - 1][1]); spiral.addLast(matrix[matrix.length - 1][0]); 
				for(int i = matrix.length - 2; i >= 1; i--) {
					spiral.addLast(matrix[i][0]);
				}
				return spiral;
			}
			int rightBorder = matrix[0].length, leftBorder = 0, verticalHeight = matrix.length - 2;
			boolean isSpiraleMake = true;
			while(isSpiraleMake) {
				for(int j = topLeft.j; j < rightBorder; j++) {
					spiral.addLast(matrix[topLeft.i][j]);
				}
				for(int i = topLeft.i + 1; i <= verticalHeight; i++) {
					spiral.addLast(matrix[i][downRight.j]);
				}
				for(int j = rightBorder - 1; j >= leftBorder; j--) {
					spiral.addLast(matrix[downRight.i][j]);
				}
				for(int i = downRight.i - 1; i >= topLeft.i + 1; i--) {
					spiral.addLast(matrix[i][topLeft.j]);
				}
				downRight.i--; downRight.j--;
				topLeft.i++; topLeft.j++;
				leftBorder++; rightBorder--; verticalHeight--;
				if(isOne(topLeft, downRight)) {
					spiral.addLast(matrix[downRight.i][downRight.j]);
					isSpiraleMake = false;
				} else if(isSquare(topLeft, downRight)) {
					spiral.addLast(matrix[topLeft.i][topLeft.j]); spiral.addLast(matrix[topLeft.i][topLeft.i + 1]);
					spiral.addLast(matrix[downRight.i][topLeft.j + 1]); spiral.addLast(matrix[downRight.i][topLeft.j]);
					isSpiraleMake = false;
				} else if(isVerticalBackfire(topLeft, downRight)) {
					spiral.addLast(matrix[topLeft.i][topLeft.j]); spiral.addLast(matrix[topLeft.i][topLeft.i + 1]);
					for(int i = topLeft.i + 1; i < downRight.i; i++) {
						spiral.addLast(matrix[i][topLeft.j + 1]);
					}
					spiral.addLast(matrix[downRight.i][topLeft.j + 1]); spiral.addLast(matrix[downRight.i][topLeft.j]); 
					for(int i = downRight.i - 1; i > topLeft.i; i--) {
						spiral.addLast(matrix[i][topLeft.j]);
					}
					isSpiraleMake = false;
				} else if(isHorizontalBackfire(topLeft, downRight)) {
					for(int j = topLeft.j; j <= downRight.j; j++) {
						spiral.addLast(matrix[topLeft.i][j]);
					}
					for(int j = downRight.j; j >= topLeft.j; j--) {
						spiral.addLast(matrix[downRight.i][j]);
					}
					isSpiraleMake = false;
				} else if(isInHorizontalLine(topLeft, downRight)) {
					for(int j = topLeft.j; j <= downRight.j; j++) {
						spiral.addLast(matrix[topLeft.i][j]);
					}
					isSpiraleMake = false;
				} else if(isInVerticalLine(topLeft, downRight)) {
					for(int i = topLeft.i; i <= downRight.i; i++) {
						spiral.addLast(matrix[i][topLeft.j]);
					}
					isSpiraleMake = false;
				} else if(isOutBorder(topLeft, downRight)) {
					isSpiraleMake = false;
				}
			}
		}
		return spiral;
	}
	
	private boolean isOne(Pair topLeft, Pair downRight) {
		return topLeft.i == downRight.i && topLeft.j == downRight.j;
	}
	
	private boolean isSquare(Pair topLeft, Pair downRight) {
		return downRight.i - topLeft.i == 1 && downRight.j - topLeft.j == 1;
	}
	
	private boolean isHorizontalBackfire(Pair topLeft, Pair downRight) {
		return downRight.i - topLeft.i == 1 && topLeft.j < downRight.j;
	}
	
	private boolean isVerticalBackfire(Pair topLeft, Pair downRight) {
		return downRight.j - topLeft.j == 1 && topLeft.i < downRight.i;
	}
	
	private boolean isInHorizontalLine(Pair topLeft, Pair downRight) {
		return topLeft.i == downRight.i && downRight.j > topLeft.j;
	}
	
	private boolean isInVerticalLine(Pair topLeft, Pair downRight) {
		return topLeft.j == downRight.j && downRight.i > topLeft.i;
	}
	
	private boolean isOutBorder(Pair topLeft, Pair downRight) {
		return topLeft.j > downRight.j;
	}
	
	private class Pair {
		
		private int i;
		private int j;
		
		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
		
		@Override
		public String toString() {
			return i + " " + j;
		}
	}
}
