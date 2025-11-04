package aq.gym.contests.backtracking;

public class N_Queens_II {

//	https://leetcode.com/problems/n-queens-ii/
	public static void main(String[] args) {
		
	}

    public int totalNQueens(int n) {
    	char[][] desk = new char[n][n];
    	int[] accumulator = {0};
    	int column = 0, qCount = n;
    	calculateDistinctSolutionsNumber(accumulator, desk, column, qCount);
        return accumulator[0];
    }
    
    private void calculateDistinctSolutionsNumber(int[] accumulator, char[][] desk, int column, int qCount) {
    	if(column == 0 && qCount == 0) {
    		accumulator[0]++;
    	}
    	if(column == 0 && qCount > 0) {
    		return;
    	}
//    	do recursive solution
//    	make an addition method to check valid place of a queen as separate method
    }
}
