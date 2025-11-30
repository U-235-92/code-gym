package aq.gym.contests.matrix;

import java.util.Arrays;

public class RotateImage {

	public static void main(String[] args) {
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		new RotateImage().rotate(matrix);
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}

    public void rotate(int[][] matrix) {
    	reverse(matrix);
    	transpose(matrix);
    }
    
//  Reverse j-th column
    private void reverse(int[][] matrix) {
    	int n = matrix.length, j = 0;
    	while(j < n) {
    		for(int i = 0; i < n / 2; i++) {
    			int tmp = matrix[i][j];
    			matrix[i][j] = matrix[n - 1 - i][j];
    			matrix[n - 1 - i][j] = tmp;
    		}
    		j++;
    	}
    }
    
    private void transpose(int[][] matrix) {
    	int n = matrix.length;
    	for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
   }
}
