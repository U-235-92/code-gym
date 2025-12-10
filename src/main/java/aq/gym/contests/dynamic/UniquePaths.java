package aq.gym.contests.dynamic;

public class UniquePaths {

//	https://leetcode.com/problems/unique-paths/
	public static void main(String[] args) {
		int m = 3, n = 7;
		System.out.println(new UniquePaths().uniquePaths(m, n));
	}

    public int uniquePaths(int m, int n) {
    	int[][] grid = new int[m][n];
    	initBaseRow(grid);
    	initBaseColumn(grid);
    	for(int i = 1; i < grid.length; i++) {
    		for(int j = 1; j < grid[i].length; j++) {
    			grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
    		}
    	}
        return grid[m - 1][n - 1];
    }
    
    private void initBaseRow(int[][] grid) {
    	for(int i = 0; i < grid[0].length; i++) {
    		grid[0][i] = 1;
    	}
    }
    
    private void initBaseColumn(int[][] grid) {
    	for(int i = 0; i < grid.length; i++) {
    		grid[i][0] = 1;
    	}
    }
}
