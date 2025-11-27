package aq.gym.contests.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {

//	https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
	public static void main(String[] args) {
//		int n = 6;
//		int[][] connections = {{4,5},{0,1},{1,3},{2,3},{4,0}}; // 3
//		int n = 6;
//		int[][] connections = {{0,1},{1,3},{2,3},{4,0},{4,5}}; // 3
//		int n = 2;
//		int[][] connections = {{0,1}}; // 1
//		int n = 2;
//		int[][] connections = {{1,0}}; // 0
		int n = 3;
		int[][] connections = {{1,2},{2,0}}; // 0
		System.out.println(new ReorderRoutesToMakeAllPathsLeadToTheCityZero().minReorder(n, connections));
	}
	
	public int minReorder(int n, int[][] connections) {
        List<List<int[]>> graph = new ArrayList<>();
        intializeDataStructures(n, connections, graph);
        return getReorderedRoadNumber(graph);
    }
    
    private void intializeDataStructures(int n, int[][] connections, List<List<int[]>> graph) {
    	for(int i = 0; i < n; i++) {
    		graph.add(new ArrayList<>());
    	}
		for(int i = 0; i < connections.length; i++) {
			int[] connection = connections[i];
			int from = connection[0], to = connection[1];
			graph.get(from).add(new int[] {to, 1});
			graph.get(to).add(new int[] {from, -1});
		}
    }
    
	private int getReorderedRoadNumber(List<List<int[]>> graph) {
		int reorderedRoadsNumber = 0;
		Set<Integer> visited = new HashSet<>();
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(0);
		visited.add(0);
		while(!stack.isEmpty()) {
			int from = stack.pop();
			List<int[]> neighbours = graph.get(from);
			for(int[] neighbour : neighbours) {
				int to = neighbour[0];
				int direction = neighbour[1];
				if(direction != 0 && !visited.contains(to)) {
					if(direction == 1) {								
						reorderedRoadsNumber++;
					}
					stack.push(to);
					visited.add(to);
				}
			}
		}
		return reorderedRoadsNumber;
	}
}
