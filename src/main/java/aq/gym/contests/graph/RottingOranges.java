package aq.gym.contests.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class RottingOranges {

//	https://leetcode.com/problems/rotting-oranges
	public static void main(String[] args) {
		int[][] grid = {{0,0,0,1,1},{0,1,1,2,1},{1,1,0,1,1},{1,1,2,2,2}}; // 3
//		int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
//		int[][] grid = {{2,1,1},{0,1,1},{1,0,1}};
//		int[][] grid = {{1,2,1},{1,1,0},{0,1,1}};
//		int[][] grid = {{2,2},{1,1},{0,0}}; // 1
//		int[][] grid = {{2,2},{1,1},{0,0},{2,0}};
//		int[][] grid = {{0,0},{0,0}};
//		int[][] grid = {{0,0},{0,1}};
//		int[][] grid = {{0,0},{2,2}};
//		int[][] grid = {{0,0},{0,2}};
//		int[][] grid = {{0,2}};
//		int[][] grid = {{0}};
//		int[][] grid = {{1}};
//		int[][] grid = {{2}};
		System.out.println("Answer: " + new RottingOranges().orangesRotting(grid));
	}

    public int orangesRotting(int[][] grid) {
    	int currTime = 0, maxTime = 0;
    	Map<Orange, List<Orange>> orangeGraph = getGraph(grid);
    	Map<Orange, Integer> timeLevelRottingOrange = new HashMap<>();
    	Queue<Orange> rottenOrangesQueue = new ArrayDeque<>();
    	Set<Orange> visitedOranges = new HashSet<>();
    	for(Orange orange : orangeGraph.keySet()) {
    		if(orange.isRotten()) {    			
    			rottenOrangesQueue.offer(orange);
    			timeLevelRottingOrange.put(orange, 0);
    			visitedOranges.add(orange);
    		}
    	}
    	if(rottenOrangesQueue.isEmpty()) { // If there aren't any rotten oranges
    		for(Orange orange : orangeGraph.keySet()) {
    			if(orange.isFresh()) {
    				return -1; // There are only fresh oranges 
    			}
    		}
    		return 0; // There aren't any oranges
    	}
    	while(!rottenOrangesQueue.isEmpty()) {
    		Orange orange = rottenOrangesQueue.poll();
    		visitedOranges.add(orange);
			currTime = timeLevelRottingOrange.get(orange);
			maxTime = Math.max(maxTime, currTime);
			List<Orange> neighbourOranges = orangeGraph.getOrDefault(orange, Collections.emptyList());
			for(Orange neigbourOrange : neighbourOranges) {
				if(!visitedOranges.contains(neigbourOrange)) {
					rottenOrangesQueue.offer(neigbourOrange);
					timeLevelRottingOrange.put(neigbourOrange, currTime + 1);
					visitedOranges.add(neigbourOrange);
				}
			}
    	}
    	for(Orange orange : orangeGraph.keySet()) {
    		if(orange.isFresh() && !timeLevelRottingOrange.containsKey(orange)) {
    			return -1;
    		}
    	}
    	return maxTime;
    }
    
    private Map<Orange, List<Orange>> getGraph(int[][] grid) {
    	Map<Orange, List<Orange>> graph = new HashMap<>();
    	for(int i = 0; i < grid.length; i++) {
    		for(int j = 0; j < grid[i].length; j++) {
    			if(grid[i][j] == 0) continue; 
    			putNodeIntoGraph(graph, grid, i, j);
    		}
    	}
    	return graph;
    }
    
    @SuppressWarnings("unused")
	private void putNodeIntoGraph(Map<Orange, List<Orange>> graph, int[][] grid, int i, int j) {
    	Orange orange = new Orange(i, j, grid[i][j]);
    	graph.put(orange, new ArrayList<Orange>());
    	int[] iDirections = {1,-1,0,0};
    	int[] jDirections = {0,0,1,-1};
    	for(int d = 0; d < 4; d++) {
    		int k = i + iDirections[d];
    		int m = j + jDirections[d];
    		if(k >= 0 && k < grid.length && m >= 0 && m < grid[0].length && grid[k][m] != 0) {
    			graph.get(orange).add(new Orange(k, m, grid[k][m]));
    		}
    	}
    }
    
    private static final class Orange {
    	
    	private final int i;
    	private final int j;
    	private int value;
    	
    	Orange(int i, int j, int value) {
    		this.i = i;
    		this.j = j;
    		this.value = value;
    	}
    	
    	public boolean isRotten() {
    		return value == 2;
    	}
    	
    	public boolean isFresh() {
    		return value == 1;
    	}
    	
    	@Override
    	public boolean equals(Object obj) {
    		if(obj == null || obj.getClass() != this.getClass()) return false;
    		if(obj == this) return true;
    		Orange node = (Orange) obj;
    		return node.i == this.i && node.j == this.j && node.value == this.value;
    	}
    	
    	@Override
    	public int hashCode() {
    		return Objects.hash(i, j, value);
    	}
    	
    	@Override
    	public String toString() {
    		return String.format("[%d, %d] = %d", i, j, value);
    	}
    }
}

