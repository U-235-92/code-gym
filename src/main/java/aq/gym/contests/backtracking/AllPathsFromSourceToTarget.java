package aq.gym.contests.backtracking;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceToTarget {

//	https://leetcode.com/problems/all-paths-from-source-to-target/
	public static void main(String[] args) {
		int[][] graph = {{1,2},{3},{3},{}};
		List<List<Integer>> allPaths = new AllPathsFromSourceToTarget().allPathsSourceTarget(graph);
		allPaths.forEach(System.out::println);
 	}

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    	List<List<Integer>> allPaths = new ArrayList<>();
    	allPathsSourceTarget(graph, allPaths, new ArrayList<>(List.of(0)), 0);
    	return allPaths;
    }
    
    private void allPathsSourceTarget(int[][] graph, List<List<Integer>> allPaths, List<Integer> curPath, int node) {
    	if(node == graph.length - 1) {
    		allPaths.add(new ArrayList<>(curPath));
    		return;
    	} 
    	int[] neighbours = graph[node];
    	for(int i = 0; i < neighbours.length; i++) {
    		int neighbour = neighbours[i];
    		curPath.add(neighbour);
    		allPathsSourceTarget(graph, allPaths, curPath, neighbour);
    		curPath.removeLast();
    	}
    }
}
