package aq.gym.contests.easy.array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DiagonalTraverse {

	public static void main(String[] args) {
//		int[][] mat = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
		int[][] mat = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
//		int[][] mat = {{3}, {2}};
//		int[][] mat = {{2,5,8}, {4,0,-1}};
//		int[][] mat = {{1, 2}};
		int[] traverse = new DiagonalTraverse().findDiagonalOrder(mat);
		System.out.println(Arrays.toString(traverse));
	}

	public int[] findDiagonalOrder(int[][] mat) {
		if(mat.length == 1 && mat[0].length == 1) {			
			return new int[] {mat[0][0]};
		} else if(mat.length == 1 && mat[0].length > 1) {
			return mat[0];
		} else if(mat.length > 1 && mat[0].length == 1) {
			int[] traverse = new int[mat.length];
			int i = 0, j = 0, k = 0;
			while(i < mat.length) {
				traverse[k++] = mat[i++][j];
			}
			return traverse;
		} else {			
			int[] traverse = new int[mat.length * mat[0].length];
			int iStart = 1, i = 0, jStart = 0, j = jStart, traverseIdx = 0, diagonalCount = 0;
			while(jStart < mat[0].length) {
				if(diagonalCount == 0) {
					traverse[traverseIdx++] = mat[0][0];
				} else if(diagonalCount % 2 == 0) {
					Deque<Integer> stack = new ArrayDeque<Integer>();
					while(j < mat[0].length && j >= 0 && i < mat.length) {						
						stack.push(mat[i++][j--]);
					}
					while(!stack.isEmpty()) {
						traverse[traverseIdx++] = stack.pop();
					}
				} else {					
					while(j < mat[0].length && j >= 0 && i < mat.length) {
						traverse[traverseIdx++] = mat[i++][j--];
					}
				}
				jStart++; j = jStart; i = 0; diagonalCount++;
			}
			i = iStart; j = mat[0].length - 1;
			while(iStart < mat.length) {				
				if(diagonalCount % 2 == 0) {
					Deque<Integer> stack = new ArrayDeque<Integer>();
					while(i < mat.length && j >= 0) {						
						stack.push(mat[i++][j--]);
					}
					while(!stack.isEmpty()) {
						traverse[traverseIdx++] = stack.pop();
					}
				} else {					
					while(i < mat.length && j >= 0) {
						traverse[traverseIdx++] = mat[i++][j--];
					}
				}
				j = mat[0].length - 1; iStart++; i = iStart; diagonalCount++;
			}
			return traverse;
		}
	}
}
