package aq.gym.contests.matrix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class FloodFill {

//	https://leetcode.com/problems/flood-fill/
	public static void main(String[] args) {
		int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
		int sr = 1, sc = 1, color = 2;
		new FloodFill().floodFill(image, sr, sc, color);
		for(int[] row : image) {
			System.out.println(Arrays.toString(row));
		}
	}
	
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    	int[] iDirs = {1,-1,0,0};
    	int[] jDirs = {0,0,1,-1};
    	Queue<int[]> cells = new ArrayDeque<>();
    	cells.offer(new int[] {sr, sc});
    	while(!cells.isEmpty()) {
    		int[] cell = cells.poll();
    		int iCell = cell[0], jCell = cell[1]; 
    		for(int d = 0; d < 4; d++) {
    			if(isAbleToFill(image, color, iCell, jCell, iDirs, jDirs, d)) {
    				cells.offer(new int[] { iCell + iDirs[d], jCell + jDirs[d] });
    			}
    		}
    		image[iCell][jCell] = color;
    	}
        return image;
    }
    
    private boolean isAbleToFill(int[][] image, int color, int iCell, int jCell, int[] iDirs, int[] jDirs, int d) {
    	return isValidIndexes(image, iCell, jCell, iDirs[d], jDirs[d]) &&
    			hasAdjacentCellEqualColor(image, iCell, jCell, iDirs[d], jDirs[d]) &&
    			isCurrentCellNotFilled(image, color, iCell, jCell);
    }
    
    private boolean isValidIndexes(int[][] image, int iCell, int jCell, int dI, int dJ) {
    	return iCell + dI >= 0 && iCell + dI < image.length && jCell + dJ >= 0 && jCell + dJ < image[0].length;
    }
    
    private boolean hasAdjacentCellEqualColor(int[][] image, int iCell, int jCell, int dI, int dJ) {
    	return image[iCell][jCell] == image[iCell + dI][jCell + dJ];
    }
    
    private boolean isCurrentCellNotFilled(int[][] image, int color, int iCell, int jCell) {
    	return image[iCell][jCell] != color;
    }
}
