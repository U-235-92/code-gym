package aq.gym.contests.algorithms.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SimpleGraphOperations {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Integer>[] graph = new ArrayList[5];
		graph[0] = new ArrayList<>(List.of(3, 1, 2));
		graph[1] = new ArrayList<>(List.of(2));
		graph[2] = new ArrayList<>(List.of(4));
		graph[3] = new ArrayList<>(List.of(1, 4));
		graph[4] = new ArrayList<>(List.of());
		dfs(graph);
		bfs(graph);
		topologicalSort(graph);
	}

	private static void dfs(List<Integer>[] graph) {
		Deque<Integer> stack = new ArrayDeque<>();
		List<Integer> dfs = new LinkedList<>();
		int[] visited = new int[graph.length];
		for(int i = 0; i < graph.length; i++) {
			if(visited[i] != 1) {
				visited[i] = 1;
				stack.push(i);
				while(!stack.isEmpty()) {
					int node = stack.pop();
					dfs.add(node);
					List<Integer> neighbours = graph[node];
					for(int neighbour : neighbours) {
						if(visited[neighbour] != 1) {
							visited[neighbour] = 1;
							stack.push(neighbour);
						}
					}
				}
			}
		}
		System.out.println("DFS: " + dfs);
	}
	
	private static void bfs(List<Integer>[] graph) {
		List<Integer> bfs = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();
		int[] visited = new int[graph.length];
		for(int i = 0; i < graph.length; i++) {
			if(visited[i] != 1) {
				visited[i] = 1;
				queue.add(i);
				while(!queue.isEmpty()) {
					int node = queue.poll();
					bfs.add(node);
					List<Integer> neighbours = graph[node];
					for(int neighbour : neighbours) {
						if(visited[neighbour] != 1) {
							visited[neighbour] = 1;
							queue.add(neighbour);
						}
					}
				}
			}
		}
		System.out.println("BFS: " + bfs);
	}
	
	
	private static void topologicalSort(List<Integer>[] graph) {
		List<Integer> cycle = detectCycleInGraph(graph);
		if(cycle == null) {
			List<Integer> topologicalSortedNodes = new ArrayList<>();
			System.out.println("Topological sort: " + topologicalSortedNodes);
		} else {
			System.out.println("The graph has got a cycle: " + cycle  + ". The topological sort denied.");
		}		
	}
	
	private static List<Integer> detectCycleInGraph(List<Integer>[] graph) {
		return null;
	}
}
