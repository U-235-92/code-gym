package aq.gym.contests.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class NumberOfProvinces {

//	https://leetcode.com/problems/number-of-provinces/
	public static void main(String[] args) {
//		int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
//		int[][] isConnected = {{1,0,0},{0,1,0},{0,0,1}};
//		int[][] isConnected = {{1,1,1},{1,1,1},{1,1,1}};
//		int[][] isConnected = {{0,0,0},{0,0,0},{0,0,0}};
		int[][] isConnected = {{0,0,0},{0,1,0},{0,0,0}};
		System.out.println(new NumberOfProvinces().findCircleNum(isConnected));
	}
	
    public int findCircleNum(int[][] isConnected) {
    	int provinceCount = 0;
        List<int[]> graph = getGraph(isConnected);
        Set<Integer> visitedNodes = new HashSet<>();
        Queue<Integer> currProvinceNodesQueue = new ArrayDeque<>();
        for(int graphNode = 0; graphNode < graph.size(); graphNode++) {
        	if(!visitedNodes.contains(graphNode)) {
        		currProvinceNodesQueue.offer(graphNode);
        		while(!currProvinceNodesQueue.isEmpty()) {
        			int provinceNode = currProvinceNodesQueue.poll();
        			visitedNodes.add(provinceNode);
        			int[] neighbours = graph.get(provinceNode);
        			for(int neighbour : neighbours) {
        				if(!visitedNodes.contains(neighbour)) {        					
        					currProvinceNodesQueue.offer(neighbour);
        					visitedNodes.add(neighbour);
        				}
        			}
        			if(currProvinceNodesQueue.isEmpty()) {
        				provinceCount++;
        			}
        		}
        	}
        }
        return provinceCount;
    }
    
    private List<int[]> getGraph(int[][] grid) {
    	List<int[]> graph = new ArrayList<>();
    	for(int i = 0; i < grid.length; i++) {
    		List<Integer> neighbours = new ArrayList<>();
    		int noZeroNodeCount = 0; // It is used to check if there haven't any [1] nodes on the line 
    		for(int j = 0; j < grid[i].length; j++) {
    			if(grid[i][j] != 0) {
    				noZeroNodeCount++;
    				if(i != j) {    					
    					neighbours.add(j);
    				}
    			}
    		}
    		if(noZeroNodeCount > 0) {     			
    			graph.add(neighbours.stream().mapToInt(Integer::intValue).toArray());
    		}
    	}
    	return graph;
    }
}
