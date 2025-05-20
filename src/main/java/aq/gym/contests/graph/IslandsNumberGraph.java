package aq.gym.contests.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class IslandsNumberGraph {

	public static void main(String[] args) {
//		char[][] grid = { 
//				{ '1', '1', '0', '0', '0' }, 
//				{ '1', '1', '0', '0', '0' }, 
//				{ '0', '0', '1', '0', '0' },
//				{ '0', '0', '0', '1', '1' } 
//				};
//		char[][] grid = {
//				{'1'},
//				{'1'},
//				{'0'},
//				{'1'},
//				{'0'},
//				{'1'}
//				};
//		char[][] grid = {{ '1', '1', '0', '0', '1' }};
		char[][] grid = {{ '1' }};
		System.out.println(new IslandsNumberGraph().numIslands(grid));
	}

	public int numIslands(char[][] grid) {
		if(grid.length == 1 && grid[0].length == 1) {
			return (grid[0][0] == '0') ? 1 : 0;
		}
		Map<Cell, List<Cell>> graph = getCellGraph(grid);
		return numIslands(graph);
	}

	private Map<Cell, List<Cell>> getCellGraph(char[][] grid) {
		Map<Cell, List<Cell>> graph = new HashMap<>();
		if(grid.length > 1 && grid[0].length > 1) {			
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[i][j] == '1') {
						int val = getCellValue(i, j, grid);
						Cell cell = new Cell(val, i, j);
						List<Cell> neighbours = new ArrayList<>();
						markNoLineGridCellNeighbours(cell, neighbours, grid, i, j);
						graph.put(cell, neighbours);
					}
				}
			}
		} else if(grid.length == 1 && grid[0].length > 1) {
			for(int j = 0; j < grid[0].length; j++) {
				if (grid[0][j] == '1') {
					int val = getCellValue(0, j, grid);
					Cell cell = new Cell(val, 0, j);
					List<Cell> neighbours = new ArrayList<>();
					markHorizontalLineGridCellNeighbours(cell, neighbours, grid, 0, j);
					graph.put(cell, neighbours);
				}
			}
		} else if(grid.length > 1 && grid[0].length == 1) {
			for(int i = 0; i < grid.length; i++) {
				if (grid[i][0] == '1') {
					int val = getCellValue(i, 0, grid);
					Cell cell = new Cell(val, i, 0);
					List<Cell> neighbours = new ArrayList<>();
					markVerticalLineGridCellNeighbours(cell, neighbours, grid, i, 0);
					graph.put(cell, neighbours);
				}
			}
		}
		return graph;
	}

	private void markNoLineGridCellNeighbours(Cell cell, List<Cell> neighbours, char[][] grid, int i, int j) {
		if (i == 0) {
			if (j == 0) {
				if (grid[i][j + 1] == '1') {
					neighbours.add(new Cell(getCellValue(i, j + 1, grid), i, j + 1));
				}
				if (grid[i + 1][j] == '1') {
					neighbours.add(new Cell(getCellValue(i + 1, j, grid), i + 1, j));
				}
			} else if (j == grid[i].length - 1) {
				if (grid[i][j - 1] == '1') {
					neighbours.add(new Cell(getCellValue(i, j - 1, grid), i, j - 1));
				}
				if (grid[i + 1][j] == '1') {
					neighbours.add(new Cell(getCellValue(i + 1, j, grid), i + 1, j));
				}
			} else {
				if (grid[i + 1][j] == '1') {
					neighbours.add(new Cell(getCellValue(i + 1, j, grid), i + 1, j));
				}
				if (grid[i][j - 1] == '1') {
					neighbours.add(new Cell(getCellValue(i, j - 1, grid), i, j - 1));
				}
				if (grid[i][j + 1] == '1') {
					neighbours.add(new Cell(getCellValue(i, j + 1, grid), i, j + 1));
				}
			}
		} else if (i == grid.length - 1) {
			if (j == 0) {
				if (grid[i][j + 1] == '1') {
					neighbours.add(new Cell(getCellValue(i, j + 1, grid), i, j + 1));
				}
				if (grid[i - 1][j] == '1') {
					neighbours.add(new Cell(getCellValue(i - 1, j, grid), i - 1, j));
				}
			} else if (j == grid[i].length - 1) {
				if (grid[i][j - 1] == '1') {
					neighbours.add(new Cell(getCellValue(i, j - 1, grid), i, j - 1));
				}
				if (grid[i - 1][j] == '1') {
					neighbours.add(new Cell(getCellValue(i - 1, j, grid), i - 1, j));
				}
			} else {
				if (grid[i - 1][j] == '1') {
					neighbours.add(new Cell(getCellValue(i - 1, j, grid), i - 1, j));
				}
				if (grid[i][j - 1] == '1') {
					neighbours.add(new Cell(getCellValue(i, j - 1, grid), i, j - 1));
				}
				if (grid[i][j + 1] == '1') {
					neighbours.add(new Cell(getCellValue(i, j + 1, grid), i, j + 1));
				}
			}
		} else {
			if (j == 0) {
				if (grid[i][j + 1] == '1') {
					neighbours.add(new Cell(getCellValue(i, j + 1, grid), i, j + 1));
				}
				if (grid[i - 1][j] == '1') {
					neighbours.add(new Cell(getCellValue(i - 1, j, grid), i - 1, j));
				}
				if (grid[i + 1][j] == '1') {
					neighbours.add(new Cell(getCellValue(i + 1, j, grid), i + 1, j));
				}
			} else if (j == grid[i].length - 1) {
				if (grid[i][j - 1] == '1') {
					neighbours.add(new Cell(getCellValue(i, j - 1, grid), i, j - 1));
				}
				if (grid[i - 1][j] == '1') {
					neighbours.add(new Cell(getCellValue(i - 1, j, grid), i - 1, j));
				}
				if (grid[i + 1][j] == '1') {
					neighbours.add(new Cell(getCellValue(i + 1, j, grid), i + 1, j));
				}
			} else {
				if (grid[i][j - 1] == '1') {
					neighbours.add(new Cell(getCellValue(i, j - 1, grid), i, j - 1));
				}
				if (grid[i][j + 1] == '1') {
					neighbours.add(new Cell(getCellValue(i, j + 1, grid), i, j + 1));
				}
				if (grid[i - 1][j] == '1') {
					neighbours.add(new Cell(getCellValue(i - 1, j, grid), i - 1, j));
				}
				if (grid[i + 1][j] == '1') {
					neighbours.add(new Cell(getCellValue(i + 1, j, grid), i + 1, j));
				}
			}
		}
	}
	
	private void markHorizontalLineGridCellNeighbours(Cell cell, List<Cell> neighbours, char[][] grid, int i, int j) {
		if (j == 0) {
			if (grid[i][j + 1] == '1') {
				neighbours.add(new Cell(getCellValue(i, j + 1, grid), i, j + 1));
			}
		} else if (j == grid[i].length - 1) {
			if (grid[i][j - 1] == '1') {
				neighbours.add(new Cell(getCellValue(i, j - 1, grid), i, j - 1));
			}
		} else {
			if (grid[i][j - 1] == '1') {
				neighbours.add(new Cell(getCellValue(i, j - 1, grid), i, j - 1));
			}
			if (grid[i][j + 1] == '1') {
				neighbours.add(new Cell(getCellValue(i, j + 1, grid), i, j + 1));
			}
		}
	}
	
	private void markVerticalLineGridCellNeighbours(Cell cell, List<Cell> neighbours, char[][] grid, int i, int j) {
		if (i == 0) {
			if (grid[i + 1][j] == '1') {
				neighbours.add(new Cell(getCellValue(i + 1, j, grid), i + 1, j));
			}
		} else if (i == grid.length - 1) {
			if (grid[i - 1][j] == '1') {
				neighbours.add(new Cell(getCellValue(i - 1, j, grid), i - 1, j));
			}
		} else {
			if (grid[i - 1][j] == '1') {
				neighbours.add(new Cell(getCellValue(i - 1, j, grid), i - 1, j));
			}
			if (grid[i + 1][j] == '1') {
				neighbours.add(new Cell(getCellValue(i + 1, j, grid), i + 1, j));
			}
		}
	}

	private int getCellValue(int i, int j, char[][] grid) {
		return (i * grid[i].length) + j + 1;
	}

	private int numIslands(Map<Cell, List<Cell>> graph) {
		int islndsNumber = 0;
		Set<Cell> visitedCells = new HashSet<>();
		Queue<Cell> noVisitedCells = new ArrayDeque<>();
		for (Map.Entry<Cell, List<Cell>> entry : graph.entrySet()) {
			Cell cell = entry.getKey();
			if (!visitedCells.contains(cell)) {
				noVisitedCells.offer(cell);
				while (!noVisitedCells.isEmpty()) {
					cell = noVisitedCells.poll();
					if (!visitedCells.contains(cell)) {
						List<Cell> neighbours = graph.get(cell);
						for (Cell neighbour : neighbours) {
							noVisitedCells.add(neighbour);
						}
						visitedCells.add(cell);
					}
				}
				islndsNumber++;
			}
		}
		return islndsNumber;
	}

	private class Cell {

		private int val;
		private int i;
		private int j;

		Cell(int val, int i, int j) {
			super();
			this.val = val;
			this.i = i;
			this.j = j;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || obj.getClass() != this.getClass()) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			Cell other = (Cell) obj;
			return other.val == this.val && other.i == this.i && other.j == this.j;
		}

		@Override
		public int hashCode() {
			return Objects.hash(val, i, j);
		}

		@Override
		public String toString() {
			return "" + val;
		}
	}
}
