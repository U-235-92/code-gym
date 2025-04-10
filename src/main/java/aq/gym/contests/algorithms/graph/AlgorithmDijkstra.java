package aq.gym.contests.algorithms.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class AlgorithmDijkstra {

	public static void main(String[] args) {
//		GRAPH 1
		Node n0 = new Node(0);
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		n0.addNeighbours(List.of(n1, n2));
		n2.addNeighbours(List.of(n1, n3));
		n1.addNeighbour(n3);
		Map<Node, Map<Node, Integer>> graph = new LinkedHashMap<>();
		graph.put(n0, Map.of(n1, 6, n2, 2));
		graph.put(n1, Map.of(n3, 1));
		graph.put(n2, Map.of(n1, 3, n3, 5));
		graph.put(n3, Map.of());
		Map<Node, Integer> costMap = getCostMap(graph, n0);
		printMinimalCostReport(graph, costMap, n0, n3);
//		GRAPH 2
//		Node n0 = new Node(0);
//		Node n1 = new Node(1);
//		Node n2 = new Node(2);
//		Node n3 = new Node(3);
//		Node n4 = new Node(4);
//		Node n5 = new Node(5);
//		n0.addNeighbours(List.of(n1, n2));
//		n1.addNeighbour(n3);
//		n2.addNeighbours(List.of(n3, n4));
//		n3.addNeighbour(n5);
//		n4.addNeighbour(n5);
//		Map<Node, Map<Node, Integer>> graph = new LinkedHashMap<>();
//		graph.put(n0, Map.of(n1, 1, n2, 2));
//		graph.put(n1, Map.of(n3, 7));
//		graph.put(n2, Map.of(n3, 4, n4, 6));
//		graph.put(n3, Map.of(n5, 2));
//		graph.put(n4, Map.of(n5, 5));
//		graph.put(n5, Map.of());
//		Map<Node, Integer> costMap = getCostMap(graph, n0);
//		printMinimalCostReport(graph, costMap, n0, n5);
	}
	
	private static Map<Node, Integer> getCostMap(Map<Node, Map<Node, Integer>> graph, Node start) {
		Map<Node, Integer> costMap = new HashMap<>();
		for(Map.Entry<Node, Map<Node, Integer>> entry : graph.entrySet()) {
			List<Node> neighbours = entry.getKey().getNeighbours();
			for(Node neighbour : neighbours) {
				if(start.getNeighbours().contains(neighbour)) {
					costMap.putIfAbsent(neighbour, entry.getValue().get(neighbour));
				} else {
					costMap.putIfAbsent(neighbour, Integer.MAX_VALUE);
				}
			}
		}
		return costMap;
	}
	
	private static void printMinimalCostReport(Map<Node, Map<Node, Integer>> graph, Map<Node, Integer> costMap, Node start, Node finish) {
		Map<Node, Node> childParentMap = new LinkedHashMap<>();
		List<Node> childs = start.getNeighbours();
		for(Node n : childs) {
			childParentMap.put(n, start);
		}
		int minimalCost = 0;
		Node node = getMinimalCostNode(costMap);
		while(node != null) {
			int nodeCost = costMap.get(node);
			List<Node> neighbours = node.getNeighbours();
			for(Node neighbour : neighbours) {
				int neighbourCost = costMap.get(neighbour);
				int totalRouteCost = nodeCost + graph.get(node).get(neighbour);
				if(totalRouteCost < neighbourCost) {
					minimalCost = Integer.min(totalRouteCost, neighbourCost);
					costMap.put(neighbour, minimalCost);
					childParentMap.put(neighbour, node);
				}
			}
			node.setVisited(true);
			node = getMinimalCostNode(costMap);
		}
		StringBuilder route = new StringBuilder();
		route.append(finish.getValue() + " ");
		Node n = childParentMap.get(finish);
		while(n.getValue() != start.getValue()) {
			route.append(n.getValue() + " ");
			n = childParentMap.get(n);
		}
		route.append(start.getValue() + " ");
		System.out.println("Minimal cost of the route: " + minimalCost);
		System.out.println("The route: " + route.reverse().toString().trim());
	}
	
	private static Node getMinimalCostNode(Map<Node, Integer> costMap) {
		return costMap.entrySet()
				.stream()
				.sorted(Comparator.comparing(Map.Entry::getValue))
				.map(Map.Entry::getKey)
				.filter(Predicate.not(Node::isVisited))
				.findFirst()
				.orElse(null);
	}
	
	private static class Node {
		
		private int value;
		private boolean visited;
		private List<Node> neighbours;
		
		Node(int value) {
			this.value = value;
			this.visited = false;
			this.neighbours = new ArrayList<>();
		}

		public int getValue() {
			return value;
		}
		
		public boolean isVisited() {
			return visited;
		}
		
		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		
		public List<Node> getNeighbours() {
			return new ArrayList<Node>(neighbours);
		}
		
		private void addNeighbours(List<Node> neighbours) {
			this.neighbours.addAll(neighbours);
		}
		
		private void addNeighbour(Node node) {
			neighbours.add(node);
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}
	}
}
