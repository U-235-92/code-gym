package aq.gym.contests.backtrecking;

import java.util.ArrayList;
import java.util.List;

public class N_Queens {

//	https://leetcode.com/problems/n-queens
	public static void main(String[] args) {
		List<List<String>> result = new N_Queens().solveNQueens(5);
		for(List<String> line : result) {
			System.out.println(line);
		}
	}

    public List<List<String>> solveNQueens(int n) {
    	List<List<String>> result = new ArrayList<>();
        char[][] desk = new char[n][n];
        for(int i = 0; i < desk.length; i++) {
        	for(int j = 0; j < desk.length; j++) {
        		desk[i][j] = '.';
        	}
        }
        solve(0, result, desk);
        return result;
    }
    
    private void solve(int row, List<List<String>> result, char[][] desk) {
    	if(row == desk.length) {
    		List<String> lines = new ArrayList<>();
    		for(int i = row - 1; i >= 0; i--) {
    			String line = new String(desk[i]);
    			lines.add(line);
    		}
    		result.add(lines);
    		return;
    	}
    	for(int column = 0; column < desk.length; column++) {
    		if(isAbleToPutQueen(row, column, desk)) {
    			desk[row][column] = 'Q';
    			solve(row + 1, result, desk);
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
