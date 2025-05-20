package aq.gym.contests.recursion;

@SuppressWarnings("unused")
public class IslandsNumberRecursion {

	public static void main(String[] args) {
		char[][] grid = getGrid5();
		System.out.println(new IslandsNumberRecursion().numIslands(grid));
	}
	
	private static char[][] getGrid1() {
		char[][] grid = {
				{'1','1','1','1','0'},
				{'1','1','0','1','0'},
				{'1','1','0','0','0'},
				{'0','0','0','0','0'}
				};
		return grid;
	}

	private static char[][] getGrid2() {
		char[][] grid = {
				{'1','1','0','0','0'},
				{'1','1','0','0','0'},
				{'1','0','1','0','0'},
				{'0','0','0','1','1'}
				};
		return grid;
	}
	
	private static char[][] getGrid3() {
		char[][] grid = {
				{'0', '1', '0', '1', '0', '1'}
				};
		return grid;
	}
	
	private static char[][] getGrid4() {
		char[][] grid = {
				{'0'},
				{'1'},
				{'0'},
				{'1'},
				{'0'},
				{'1'}
				};
		return grid;
	}
	
	private static char[][] getGrid5() {
		char[][] grid = {{'0'}};
		return grid;
	}
	
	private static char[][] getGrid6() {
		String[][] strs = {
				{ "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "0", "1", "1" },
				{ "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "0" },
				{ "1", "0", "1", "1", "1", "0", "0", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" },
				{ "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" },
				{ "1", "0", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" },
				{ "1", "0", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "0", "1", "1", "1", "0", "1", "1", "1" },
				{ "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "1", "1", "1", "1" },
				{ "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "0", "1", "1" },
				{ "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1" },
				{ "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" },
				{ "0", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" },
				{ "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" },
				{ "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" },
				{ "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1" },
				{ "1", "0", "1", "1", "1", "1", "1", "0", "1", "1", "1", "0", "1", "1", "1", "1", "0", "1", "1", "1" },
				{ "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "1", "0" },
				{ "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "0", "0" },
				{ "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" },
				{ "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" },
				{ "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" } 
			};
		char[][] grid = new char[strs.length][strs[0].length];
		for (int i = 0; i < strs.length; i++) {
			for (int j = 0; j < strs[i].length; j++) {
				grid[i][j] = (strs[i][j].equals("1")) ? '1' : '0';
			}
		}
		return grid;
	}
	
	public int numIslands(char[][] grid) {
		int islandsNumber = 0;
		if(grid.length == 1 && grid[0].length == 1) {
			islandsNumber = (grid[0][0] == '1') ? 1 : 0;
		} else if(grid.length > 1 && grid[0].length == 1) {
			for(int i = 0; i < grid.length; i++) {
				if(grid[i][0] == '1') {
					markNoZeroGridVertical(grid, i, 0);
					islandsNumber++;
				}
			}
		} else if(grid.length == 1 && grid[0].length > 1) {
			for(int j = 0; j < grid[0].length; j++) {
				if(grid[0][j] == '1') {					
					markNoZeroGridHorizontal(grid, 0, j);
					islandsNumber++;
				}
			}
		} else {			
			for(int i = 0; i < grid.length; i++) {
				for(int j = 0; j < grid.length; j++) {
					if(grid[i][j] == '1') {
						markNoZeroGrid(grid, i, j);
						islandsNumber++;
					}
				}
			}
		}
		return islandsNumber;
	}
	
	private void markNoZeroGridVertical(char[][] grid, int i, int j) {
		if(i >= grid.length || i < 0 || grid[i][j] == '0') {
			return;
		}
		grid[i][j] = '0';
		markNoZeroGridVertical(grid, i + 1, j);
		markNoZeroGridVertical(grid, i - 1, j);
	}
	
	private void markNoZeroGridHorizontal(char[][] grid, int i, int j) {
		if(j >= grid[i].length || j < 0 || grid[i][j] == '0') {
			return;
		}
		grid[i][j] = '0';
		markNoZeroGridHorizontal(grid, i, j + 1);
		markNoZeroGridHorizontal(grid, i, j - 1);
	}
	
	private void markNoZeroGrid(char[][] grid, int i, int j) {
		if(i >= grid.length || i < 0 || j >= grid[i].length || j < 0 || grid[i][j] == '0') {
			return;
		}
		grid[i][j] = '0';
		markNoZeroGrid(grid, i + 1, j);
		markNoZeroGrid(grid, i - 1, j);
		markNoZeroGrid(grid, i, j + 1);
		markNoZeroGrid(grid, i, j - 1);
	}
}
