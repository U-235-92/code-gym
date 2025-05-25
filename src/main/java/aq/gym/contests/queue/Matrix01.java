package aq.gym.contests.queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Matrix01 {

	public static void main(String[] args) {
		int[][] mat = new int[][] {
			{0,0,0},
			{0,1,0},
			{1,1,1}
		};
		print(new Matrix01().updateMatrix(mat));
	}

	public int[][] updateMatrix(int[][] mat) {
		final int VISITED = 1, NO_VISITED = 0;
        int[][] visitedCells = new int[mat.length][mat[0].length];
        int[][] directions = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
        int rows = visitedCells.length, columns = visitedCells[0].length;
        Queue<int[]> cellsQueue = new ArrayDeque<>();
        for(int i = 0; i < rows; i++) {
        	for(int j = 0; j < columns; j++) {
        		if(mat[i][j] == 0) {
        			cellsQueue.offer(new int[] {i, j});
        			visitedCells[i][j] = VISITED;
        		}
        	}
        }
        while(!cellsQueue.isEmpty()) {
        	int[] cell = cellsQueue.poll();
        	int cI = cell[0], cJ = cell[1];
        	for(int i = 0; i < directions.length; i++) {
        		int rI = cI + directions[i][0], rJ = cJ + directions[i][1];
        		if(rI >= 0 && rI < rows && rJ >= 0 && rJ < columns && visitedCells[rI][rJ] == NO_VISITED) {
                    mat[rI][rJ] = mat[cI][cJ] + 1;
                    visitedCells[rI][rJ] = VISITED;
                    cellsQueue.offer(new int[] {rI, rJ});
                }
        	}
        }
        return mat;
    }
	
	private static void print(int[][] mat) {
		for(int[] arr : mat) {
			System.out.println(Arrays.toString(arr));
		}
	}
}
