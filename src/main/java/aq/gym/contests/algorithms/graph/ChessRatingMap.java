package aq.gym.contests.algorithms.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class ChessRatingMap {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			Map<Node, List<Node>> graph = new HashMap<>();
			Map<Node, Integer> incommingArrowsMap = new HashMap<>();
			int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int n = data[0];
			int m = data[1];
			for(int i = 0; i < m; i++) {
				data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
				int firstPlayer = data[0];
				int secondPlayer = data[1];
				int winner = data[2];
				if(winner == 1) {
					Node key = new Node(firstPlayer);
					graph.computeIfAbsent(key, k -> new ArrayList<Node>()).add(new Node(secondPlayer));
					incommingArrowsMap.computeIfAbsent(key, k -> 0);
					incommingArrowsMap.compute(new Node(secondPlayer), (k, v) -> (v == null) ? 1 : v + 1);
				} else {
					Node key = new Node(secondPlayer);
					graph.computeIfAbsent(key, k -> new ArrayList<Node>()).add(new Node(firstPlayer));
					incommingArrowsMap.computeIfAbsent(key, k -> 0);
					incommingArrowsMap.compute(new Node(firstPlayer), (k, v) -> (v == null) ? 1 : v + 1);
				}
			}
			boolean isAbleToDefinePlayersLevel = isAbleToDefinePlayersLevel(n, graph, incommingArrowsMap);
			if(isAbleToDefinePlayersLevel) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean isAbleToDefinePlayersLevel(int n, Map<Node, List<Node>> graph, Map<Node, Integer> incommingArrowsMap) {
		if(hasCycleNoRecursiveWay(graph)) {			
			return false;
		}
		if(hasAmbiguousPlayerLevel(n, graph, incommingArrowsMap)) {			
			return false;
		}
		return true;
	}

	private static boolean hasCycleNoRecursiveWay(Map<Node, List<Node>> graph) {
//		0 - белый (не посещён), 1 - серый (в обработке), 2 - чёрный (обработан)
		Map<Node, Integer> colorMap = new HashMap<>();
		Deque<Node> stack = new ArrayDeque<>();
		for(Node node : graph.keySet()) {
			colorMap.put(node, 0);
		}
		for(Node node : graph.keySet()) {
			if(colorMap.get(node) == 0) {
				stack.push(node);
				while(stack.size() > 0) {
					Node peek = stack.peek();
					if(colorMap.get(peek) == 0) {						
						colorMap.put(peek, 1);
						List<Node> peekNeighbours = graph.getOrDefault(peek, Collections.emptyList());
						for(Node neighbour : peekNeighbours) {
							colorMap.putIfAbsent(neighbour, 0);
							if(colorMap.get(neighbour) == 1) {
								return true;
							}
							if(colorMap.get(neighbour) == 0) {
								stack.push(neighbour);
							}
						}
					} else {						
						colorMap.put(peek, 2);
						stack.pop();
					}
				}
			}
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	private static boolean hasCycleRecursiveWay(Map<Node, List<Node>> graph) {
//		0 - белый (не посещён), 1 - серый (в обработке), 2 - чёрный (обработан)
		Map<Node, Integer> colorMap = new HashMap<>();
		for(Node node : graph.keySet()) {
			colorMap.put(node, 0);
		}
		for(Node node : graph.keySet()) {
			if(hasCycleRecursiveWay(graph, colorMap, node)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean hasCycleRecursiveWay(Map<Node, List<Node>> graph, Map<Node, Integer> colorMap, Node node) {
		colorMap.put(node, 1);
		List<Node> neghbours = graph.getOrDefault(node, Collections.emptyList());
		for(Node neighbour : neghbours) {
			colorMap.putIfAbsent(neighbour, 0);
			if(colorMap.get(neighbour) == 1) {
				return true;
			}
			if(colorMap.get(neighbour) == 2) {
				continue;
			}
			hasCycleRecursiveWay(graph, colorMap, neighbour);
		}
		colorMap.put(node, 2);
		return false;
	}
	
	private static boolean hasAmbiguousPlayerLevel(int n, Map<Node, List<Node>> graph, Map<Node, Integer> incommingArrowsMap) {
		List<Node> topologicalSortedNodesList = new LinkedList<>();
		Queue<Node> levelRaitingQueue = incommingArrowsMap.entrySet()
				.stream()
				.filter(entry -> entry.getValue() == 0)
				.map(Map.Entry::getKey)
				.collect(Collectors.toCollection(LinkedList::new));
		while(incommingArrowsMap.size() > 0) {
			List<Node> ambiguousPlayersDetectorList = new LinkedList<>(levelRaitingQueue);
			levelRaitingQueue.clear();
			if(ambiguousPlayersDetectorList.size() > 1) {
				return true;
			}
			Node leader = ambiguousPlayersDetectorList.remove(0);
			topologicalSortedNodesList.add(leader);
			List<Node> leaderNeighbours = graph.getOrDefault(leader, Collections.emptyList());
			for(Node neighbour : leaderNeighbours) {
				incommingArrowsMap.compute(neighbour, (k, v) -> {
					if(v - 1 == 0) {
						levelRaitingQueue.add(k);
					}
					return v - 1;
				});
			}
			incommingArrowsMap.remove(leader);
		}
		return topologicalSortedNodesList.size() != n;
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
