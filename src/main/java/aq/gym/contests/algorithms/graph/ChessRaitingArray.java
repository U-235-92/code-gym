package aq.gym.contests.algorithms.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class ChessRaitingArray {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int n = data[0];
			int m = data[1];
			@SuppressWarnings("unchecked")
			List<Integer>[] graph = new ArrayList[n];
			for(int i = 0; i < graph.length; i++) {
				graph[i] = new ArrayList<>();
			}
			int[] incommingArrows = new int[n];
			for(int i = 0; i < m; i++) {
				data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
				int firstPlayer = data[0] - 1;
				int secondPlayer = data[1] - 1;
				int winner = data[2];
				if(winner == 1) {
					graph[firstPlayer].add(secondPlayer);
					incommingArrows[secondPlayer] += 1;
				} else {
					graph[secondPlayer].add(firstPlayer);
					incommingArrows[firstPlayer] += 1;
				}
			}
			if(isAbleToDefinePlayersLevel(graph, incommingArrows))
				System.out.println("YES");
			else
				System.out.println("NO");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean isAbleToDefinePlayersLevel(List<Integer>[] graph, int[] incommingArrows) {
		if(isCyclicGraph(graph))
			return false;
		if(hasGraphAbiguousPlayers(graph, incommingArrows))
			return false;
		return true;
	}
	
	private static boolean isCyclicGraph(List<Integer>[] graph) {
		int[] colors = new int[graph.length];
		Deque<Integer> stack = new ArrayDeque<>();
		for(int i = 0; i < graph.length; i++) {
			if(colors[i] == 0) {
				stack.push(i);
				while(!stack.isEmpty()) {
					int node = stack.peek();
					if(colors[node] == 0) {						
						colors[node] = 1;
						List<Integer> neighbours = graph[node];
						for(int neighbour : neighbours) {
							if(colors[neighbour] == 1) {
								return true;
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
		return false;
	}
	
	private static boolean hasGraphAbiguousPlayers(List<Integer>[] graph, int[] incommingArrows) {
		List<Integer> topologicalSortedGraph = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i = 0; i < incommingArrows.length; i++) {
			if(incommingArrows[i] == 0) {
				queue.add(i);
			}
		}
		while(!queue.isEmpty()) {
			if(queue.size() > 1) {
				return true;
			}
			int node = queue.remove();
			topologicalSortedGraph.add(node);
			for(int v : graph[node]) {
				incommingArrows[v] -= 1;
				if(incommingArrows[v] == 0) {
					queue.add(v);
				}
			}
		}
		return topologicalSortedGraph.size() != graph.length;
	}
}
