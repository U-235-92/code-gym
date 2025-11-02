package aq.gym.contests.realization;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CountUnguardedCellsInTheGrid {

//	https://leetcode.com/problems/count-unguarded-cells-in-the-grid/description/
	public static void main(String[] args) {
		int m = 4, n = 6;
		int[][] guards = {{0,0},{1,1},{2,3}};
		int[][] walls = {{0,1},{2,2},{1,4}};
		System.out.println(new CountUnguardedCellsInTheGrid().countUnguarded(m, n, guards, walls));
	}

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
    	char[][] grid = new char[m][n];
        Set<Cell> freeCells = new HashSet<>();
        initializeGuards(grid, guards);
        initializeWalls(grid, walls);
        initializeFreeCells(grid, freeCells);
        return getUnguardedCellsCount(grid, guards, freeCells);
    }
    
    private void initializeGuards(char[][] grid, int[][] guards) {
    	for(int g = 0; g < guards.length; g++) {
    		int[] coordinates = guards[g];
    		int guardRow = coordinates[0], guardColumn = coordinates[1];
    		grid[guardRow][guardColumn] = 'G';
    	}
    }
    
    private void initializeWalls(char[][] grid, int[][] walls) {
    	for(int w = 0; w < walls.length; w++) {
    		int[] coordinates = walls[w];
    		int wallRow = coordinates[0], wallColumn = coordinates[1];
    		grid[wallRow][wallColumn] = 'W';
    	}
    }
    
    private void initializeFreeCells(char[][] grid, Set<Cell> freeCells) {
    	int m = grid.length, n = grid[0].length;
    	for(int i = 0; i < m; i++) {
    		for(int j = 0; j < n; j++) {
    			if(grid[i][j] == '\u0000') {    				
    				grid[i][j] = '.';
    				freeCells.add(new Cell(i, j));
    			}
    		}
    	}
    }
    
    private int getUnguardedCellsCount(char[][] grid, int[][] guards, Set<Cell> freeCells) {
    	for(int g = 0; g < guards.length; g++) {    		
    		int[] coordinates = guards[g];
    		int row = coordinates[0], column = coordinates[1];
    		checkGridRow(row, column, grid, freeCells);
    		checkGridColumn(row, column, grid, freeCells);
    	}
    	return freeCells.size();
    }
    
    private void checkGridRow(int guardRow, int guardColumn, char[][] grid, Set<Cell> freeCells) {
    	for(int j = guardColumn + 1; j < grid[guardRow].length; j++) {
			char value = grid[guardRow][j];
			if(value == 'G' || value == 'W') {
				break;
			} else {
				freeCells.remove(new Cell(guardRow, j));
			}
		}
		for(int j = guardColumn - 1; j >= 0; j--) {
			char value = grid[guardRow][j];
			if(value == 'G' || value == 'W') {
				break;
			} else {
				freeCells.remove(new Cell(guardRow, j));
			}
		}
    }
    
    private void checkGridColumn(int guardRow, int guardColumn, char[][] grid, Set<Cell> freeCells) {
    	for(int i = guardRow + 1; i < grid.length; i++) {
    		char value = grid[i][guardColumn];
    		if(value == 'G' || value == 'W') {
				break;
			} else {
				freeCells.remove(new Cell(i, guardColumn));
			}
    	}
    	for(int i = guardRow - 1; i >= 0; i--) {
    		char value = grid[i][guardColumn];
    		if(value == 'G' || value == 'W') {
				break;
			} else {
				freeCells.remove(new Cell(i, guardColumn));
			}
    	}
    }
    
    private static class Cell {
    	
    	private int x;
    	private int y;
    	
    	Cell(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    	
    	@Override
    	public boolean equals(Object obj) {
    		if(obj == null || obj.getClass() != getClass()) {
    			return false;
    		}
    		if(obj == this) {
    			return true;
    		}
    		Cell cell = (Cell) obj;
    		return this.x == cell.x && this.y == cell.y;
    	}
    	
    	@Override
    	public int hashCode() {
    		return Objects.hash(x, y);
    	}
    	
    	@Override
    	public String toString() {
    		return String.format("[%d, %d]", x, y);
    	}
    }
}
