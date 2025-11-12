package aq.gym.contests.array;

public class IslandPerimeter {

//	https://leetcode.com/problems/island-perimeter/description/
	public static void main(String[] args) {
		int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
//		int[][] grid = {{0,1},{1,1}};
		System.out.println(new IslandPerimeter().islandPerimeter(grid));
		
	}

	public int islandPerimeter(int[][] grid) {
		int perimiter = 0;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(grid[i][j] == 0) continue;
				if(i == 0 || grid[i - 1][j] == 0) perimiter++;
				if(i == grid.length - 1 || grid[i + 1][j] == 0) perimiter++;
				if(j == 0 || grid[i][j - 1] == 0) perimiter++;
				if(j == grid[0].length - 1 || grid[i][j + 1] == 0) perimiter++;
			}
		}
		return perimiter;
	}
}
