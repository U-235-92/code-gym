package aq.gym.contests.array.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetMatrixZeroes {

	public static void main(String[] args) {
		int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
		new SetMatrixZeroes().setZeroes(matrix);
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}

    public void setZeroes(int[][] matrix) {
    	List<int[]> toZeroPairs = new ArrayList<>();
    	for(int i = 0; i < matrix.length; i++) {
    		for(int j = 0; j < matrix[i].length; j++) {
    			if(matrix[i][j] == 0) {
    				toZeroPairs.add(new int[] {i, j});
    			}
    		}
    	}
    	for(int[] pair : toZeroPairs) {
    		int row = pair[0];
    		int col = pair[1];
    		for(int i = 0; i < matrix[row].length; i++) {
    			matrix[row][i] = 0;
    		}
    		for(int i = 0; i < matrix.length; i++) {
    			matrix[i][col] = 0;
    		}
    	}
    }
}
