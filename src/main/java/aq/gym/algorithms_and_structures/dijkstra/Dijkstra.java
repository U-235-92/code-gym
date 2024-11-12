package aq.gym.algorithms_and_structures.dijkstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Dijkstra {
	
	private Map<Node, Map<Node, Integer>> graph;
	private Map<Node, Integer> costs;
	private Node startNode;
	private Node endNode;
	
	public Dijkstra(Node startNode, Node endNode, Map<Node, Map<Node, Integer>> graph) {
		this.startNode = startNode;
		this.endNode = endNode;
		this.graph = graph;
		costs = new HashMap<Node, Integer>();
		fillCostsMap(costs);
	}
	
	private void fillCostsMap(Map<Node, Integer> costs) {
		for(Map.Entry<Node, Map<Node, Integer>> entry : graph.entrySet()) {			
			List<Node> neighbours = entry.getKey().getNeighbours();
			for(Node node : neighbours) {
				if(startNode.getNeighbours().contains(node)) {
					costs.putIfAbsent(node, entry.getValue().get(node));
				} else {
					costs.putIfAbsent(node, Integer.MAX_VALUE);
				}
			}
		}
	}
	
	public void calculatePath() {
		int pathCost = 0;
		Queue<Node> pathQueue = new LinkedList<Node>();
		Node minCostNode = getMinCostNode(costs);
		while(minCostNode != null) {
			int currentMinCostNode = costs.get(minCostNode);
			for(Node node : minCostNode.getNeighbours()) {
				int cost = graph.get(minCostNode).get(node);
				int totalCost = cost + currentMinCostNode;
				if(totalCost < costs.get(node)) {
					costs.put(node, totalCost);
					node.setParent(minCostNode);
					node.setMinCost(totalCost);
					if(node == endNode) {						
						endNode.setParent(minCostNode);
						endNode.setMinCost(totalCost);
					}
				}
			}
			minCostNode.setVisited(true);
			minCostNode = getMinCostNode(costs);
		}
		while(endNode != null) {
			pathQueue.offer(endNode);
			endNode = endNode.getParent();
		}
		pathQueue.offer(startNode);
		System.out.print("Path: ");
		while(pathQueue.size() != 0) {
			Node node = pathQueue.poll();
			pathCost += node.getMinCost();
			if(pathQueue.size() == 0) {
				System.out.print(node.getName());
			} else {				
				System.out.print(node.getName() + "->");
			}
		}
		System.out.println(", path cost = " + pathCost);
	}

	private Node getMinCostNode(Map<Node, Integer> costs) {
		return costs.entrySet()
				.stream()
				.sorted((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()))
				.map(Map.Entry::getKey)
				.filter(node -> !node.isVisited())
				.findFirst()
				.orElse(null);
	}
}
