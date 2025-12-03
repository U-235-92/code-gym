package aq.gym.contests.array.matrix;

public class CountNegativeNumbersInSortedMatrix {

//	https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
	public static void main(String[] args) {
		int[][] grid = {{4,3,2,-1},{3,2,1,-1},{1,1,-1,-2},{-1,-1,-2,-3}};
//		int[][] grid = {{3,2},{-3,-3},{-3,-3},{-3,-3}};
//		int[][] grid = {{3,2},{1,0}};
		System.out.println(new CountNegativeNumbersInSortedMatrix().countNegatives(grid));
	}

    public int countNegatives(int[][] grid) {
        int count = 0, jStart = grid[0].length - 1;
        for(int i = 0; i < grid.length; i++) {
        	go_to_next_row:
        	for(int j = jStart; j >= 0; ) {
        		if(j == 0 && grid[i][j] < 0) {
        			count += grid[0].length;
        			jStart = 0;
        			break;
        		}
        		if(grid[i][j] < 0) {
        			j--;
        		} else {
        			count += grid[0].length - 1 - j;
        			jStart = (jStart == grid[0].length - 1) ? grid[0].length - 1 : j + 1;
        			break go_to_next_row;
        		}
        	}
        }
        return count;
    }
}
