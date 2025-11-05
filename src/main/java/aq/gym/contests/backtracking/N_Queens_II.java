package aq.gym.contests.backtracking;

import java.util.Arrays;

public class N_Queens_II {

//	https://leetcode.com/problems/n-queens-ii/
	public static void main(String[] args) {
		int n = 5;
		System.out.println(new N_Queens_II().totalNQueens(n));
	}

    public int totalNQueens(int n) {
    	char[][] desk = new char[n][n];
    	for(char[] line : desk) {
    		Arrays.fill(line, '.');
    	}
    	int[] accumulator = {0};
    	int column = 0, qCount = n;
    	calculateDistinctSolutionsNumber(accumulator, desk, column, qCount);
        return accumulator[0];
    }
    
    private void calculateDistinctSolutionsNumber(int[] accumulator, char[][] desk, int row, int qCount) {
    	if(row == desk.length && qCount == 0) {
    		accumulator[0]++;
    	}
    	if(row == desk.length && qCount > 0) {
    		return;
    	}
    	for(int column = 0; column < desk.length; column++) {
    		if(isAbleToPutQueen(row, column, desk)) {
    			desk[row][column] = 'Q';
    			calculateDistinctSolutionsNumber(accumulator, desk, row + 1, qCount - 1);
    			desk[row][column] = '.';
    		}
    	}
    }
    
    private boolean isAbleToPutQueen(int row, int column, char[][] desk) {
    	for(int i = row - 1; i >= 0; i--) {
    		if(desk[i][column] == 'Q') {
    			return false;
    		} 
    	}
    	for(int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
    		if(desk[i][j] == 'Q') {
    			return false;
    		} 
    	}
    	for(int i = row - 1, j = column + 1; i >= 0 && j < desk.length; i--, j++) {
    		if(desk[i][j] == 'Q') {
    			return false;
    		} 
    	}
    	return true;
    }
}
