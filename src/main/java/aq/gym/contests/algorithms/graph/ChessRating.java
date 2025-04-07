package aq.gym.contests.algorithms.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class ChessRating {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			Map<Node, Set<Node>> graph = new HashMap<>();
			int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int n = data[0];
			int m = data[1];
			if(m >= n) {
				for(int i = 0; i < m; i++) {
					data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
					int firstPlayer = data[0];
					int secondPlayer = data[1];
					int winner = data[2];
					if(winner == 1) {
						Node key = new Node(firstPlayer);
						graph.computeIfAbsent(key, k -> new HashSet<Node>()).add(new Node(secondPlayer));
					} else {
						Node key = new Node(secondPlayer);
						graph.computeIfAbsent(key, k -> new HashSet<Node>()).add(new Node(firstPlayer));
					}
				}
//				graph.entrySet().forEach(System.out::println);
				Node start = graph.entrySet()
						.stream()
						.sorted((e1, e2) -> {
							return e2.getValue().size() - e1.getValue().size();
						})
						.findFirst()
						.get()
						.getKey();
				boolean isAbleToDefinePlayersLevel = isAbleToDefinePlayersLevel(n, graph, start);
				if(isAbleToDefinePlayersLevel) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			} else {
				System.out.println("NO");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean isAbleToDefinePlayersLevel(int n, Map<Node, Set<Node>> graph, Node start) {
		boolean isAbleToDefinePlayersLevel = false;
		Set<Node> visited = new HashSet<>();
		Queue<Node> noVisited = new LinkedList<>();
		noVisited.add(start);
		while(noVisited.size() > 0) {
			Node node = noVisited.remove();
			if(!visited.contains(node)) {
				visited.add(node);
				noVisited.addAll(graph.getOrDefault(node, Collections.emptySet()));
			}
		}
		isAbleToDefinePlayersLevel = n == visited.size();
		return isAbleToDefinePlayersLevel;
	}

	private static class Node {
		
		private int value;
		
		private Node(int value) {
			this.value = value;
		}

		@Override
		public int hashCode() {
			return Objects.hash(value);
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null || obj.getClass() != this.getClass()) {
				return false;
			}
			if(obj == this) {
				return true;
			}
			Node node = (Node) obj;
			return node.value == value;
		}
		
		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}
	}
}
