package aq.gym.matrix;

public class RandomWalk {

	public static void main(String[] args) {
		int n = 45, x = n / 2, y = n / 2;
		int[][] grid = new int[n][n];
		while(isInsideGrid(x, y, n)) {
			grid[x][y] = 1;
			if(isGetLost(x, y, grid)) {
				System.out.println("I've got lost!");
				return;
			} else {
				double direction = Math.random();
				if(direction < 0.25) {
					if(grid[x + 1][y] != 1) {
						x++;
					}
				} else if(direction < 0.5) {
					if(grid[x - 1][y] != 1) {
						x--;
					}
				} else if(direction < 0.75) {
					if(grid[x][y + 1] != 1) {
						y++;
					}
				} else if(direction < 1.0) {
					if(grid[x][y - 1] != 1) {
						y--;
					}
				}
			}
		}
		System.out.println("I've left the grid!");
	}
	
	private static boolean isGetLost(int x, int y, int[][] grid) {
		return grid[x - 1][y] == 1 && grid[x + 1][y] == 1 && grid[x][y + 1] == 1 && grid[x][y - 1] == 1;
	}

	private static boolean isInsideGrid(int x, int y, int n) {
		return x > 0 && x < n - 1 && y > 0 && y < n - 1;
	}
}
