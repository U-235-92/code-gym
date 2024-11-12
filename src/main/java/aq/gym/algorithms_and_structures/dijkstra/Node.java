package aq.gym.algorithms_and_structures.dijkstra;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private String name;
	private boolean isVisited;
	private Node parent;
	private List<Node> neighbours;
	private int minCost;

	public Node(String name) {
		this.name = name;
		isVisited = false;
		parent = null;
		neighbours = new ArrayList<Node>();
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public List<Node> getNeighbours() {
		return List.copyOf(neighbours);
	}

	public void setNeighbours(List<Node> neighbours) {
		this.neighbours.addAll(neighbours);
	}

	public int getMinCost() {
		return minCost;
	}

	public void setMinCost(int minCost) {
		this.minCost = minCost;
	}
}
