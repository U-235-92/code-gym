package aq.gym.contests.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class SimpleGraphOperations {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Integer>[] cycleGraph = new ArrayList[7];
		cycleGraph[0] = new ArrayList<>(List.of(1));
		cycleGraph[1] = new ArrayList<>(List.of(2, 5));
		cycleGraph[2] = new ArrayList<>(List.of(3, 4));
		cycleGraph[3] = new ArrayList<>(List.of());
		cycleGraph[4] = new ArrayList<>(List.of());
		cycleGraph[5] = new ArrayList<>(List.of(6));
		cycleGraph[6] = new ArrayList<>(List.of(0));
		List<Integer>[] simpleGraph = new ArrayList[5];
		simpleGraph[0] = new ArrayList<>(List.of(3, 1, 2));
		simpleGraph[1] = new ArrayList<>(List.of(2));
		simpleGraph[2] = new ArrayList<>(List.of(4));
		simpleGraph[3] = new ArrayList<>(List.of(1));
		simpleGraph[4] = new ArrayList<>(List.of());
		dfs(simpleGraph);
		bfs(simpleGraph);
		topologicalSort(simpleGraph);
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
		Queue<Integer> queue = new ArrayDeque<>();
		if(cycle == null) {
			List<Integer> topologicalSortedNodes = new ArrayList<>();
			int[] incommingArrows = new int[graph.length];
			for(int i = 0; i < graph.length; i++) {
				List<Integer> neighbours = graph[i];
				for(int neighbour : neighbours) {
					incommingArrows[neighbour] += 1;
				}
			}
			for(int i = 0; i < incommingArrows.length; i++) {
				if(incommingArrows[i] == 0) {
					queue.add(i);
				}
			}
			while(!queue.isEmpty()) {
				int node = queue.poll();
				topologicalSortedNodes.add(node);
				List<Integer> neighbours = graph[node];
				for(int neighbour : neighbours) {
					incommingArrows[neighbour] -= 1;
					if(incommingArrows[neighbour] == 0) {
						queue.add(neighbour);
					}
				}
			}
			System.out.println("Topological sort: " + topologicalSortedNodes);
		} else {
			String cycleStr = cycle.stream().map(String::valueOf).collect(Collectors.joining("->"));
			System.out.println("The graph has got a cycle: " + cycleStr  + ". The topological sort denied.");
		}		
	}
	
	private static List<Integer> detectCycleInGraph(List<Integer>[] graph) {
//		0 - белый, 1 - серый, 2 - черный
		List<Integer> cycle = new ArrayList<>();
		Deque<Integer> stack = new ArrayDeque<>();
		int[] colors = new int[graph.length];
		for(int i = 0; i < graph.length; i++) {
			if(colors[i] == 0) {
				stack.push(i);
				while(!stack.isEmpty()) {
					int node = stack.peek();
					if(colors[node] == 0) {
						colors[node] = 1;
						cycle.add(node);
						List<Integer> neighbours = graph[node];
						for(int neighbour : neighbours) {
							if(colors[neighbour] == 1) {
								cycle.add(neighbour);
								return cycle;
							}
							if(colors[neighbour] == 0) {
								stack.push(neighbour);
							}
						}
					} else {
						colors[node] = 2;
						stack.pop();
					}
				}
			}
		}
		return null;
	}
}
