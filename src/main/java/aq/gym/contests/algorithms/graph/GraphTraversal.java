package aq.gym.contests.algorithms.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraphTraversal {

	public static void main(String[] args) {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			int[] graphMetaData = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int n = graphMetaData[0];
			int m = graphMetaData[1];
			int start = 1;
			if(m > 0) {
				Set<Integer> visited = new HashSet<>();
				Map<Integer, Set<Integer>> graph = new HashMap<>(n);
				for(int i = 0; i < m; i++) {
					int[] nodeData = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
					int node1 = nodeData[0];
					int node2 = nodeData[1];
					addNodesInGraph(graph, node1, node2);
				}
				travel(graph, start, visited);
				print(visited);
			} else {
				System.out.println(1);
				System.out.println(1);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void addNodesInGraph(Map<Integer, Set<Integer>> graph, int node1, int node2) {
		graph.computeIfAbsent(node1, k -> new HashSet<Integer>()).add(node2);
		graph.computeIfAbsent(node2, k -> new HashSet<Integer>()).add(node1);
	}
	
//	Alternative way by recursion
//	private static void travel(Map<Integer, Set<Integer>> graph, int node, Set<Integer> visited) {
//		visited.add(node);
//		for(int neighbour : graph.getOrDefault(node, Collections.emptySet())) {
//			if(!visited.contains(neighbour)) {
//				travel(graph, neighbour, visited);
//			}
//		}
//	}
	
//	Alternative way by queue
	private static void travel(Map<Integer, Set<Integer>> graph, int start, Set<Integer> visited) {
		Queue<Integer> noVisited = new LinkedList<>();
		noVisited.add(start);
		while(noVisited.size() > 0) {
			int node = noVisited.remove();
			if(!visited.contains(node)) {				
				Set<Integer> neighbours = graph.getOrDefault(node, Collections.emptySet());
				noVisited.addAll(neighbours);
				visited.add(node);
			}
		}
	}
	
	private static void print(Set<Integer> visited) {
		List<Integer> result = new ArrayList<>(visited);
		result.sort(Comparator.naturalOrder());
		System.out.println(visited.size());
		System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
	}
}
