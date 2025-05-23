package aq.gym.contests.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CloneGraph {

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		n1.neighbors.addAll(List.of(n2, n4));
		n2.neighbors.addAll(List.of(n1, n3));
		n3.neighbors.addAll(List.of(n2, n4));
		n4.neighbors.addAll(List.of(n1, n3));
		CloneGraph cloneGraph = new CloneGraph();
		System.out.println(cloneGraph.cloneGraph(new Node(11)));
		System.out.println(cloneGraph.cloneGraph(null));
		System.out.println(cloneGraph.getAdjacencyList(n1));
		System.out.println(cloneGraph.getAdjacencyList(cloneGraph.cloneGraph(n1)));
	}

	public Node cloneGraph(Node node) {
		if(node == null) {
			return null;
		} else if(node.neighbors.isEmpty()) {
			return new Node(node.val);
		} else {
			Map<Integer, Node> map = getNodeMap(node);
			Node clone = cloneGraph(node, map);
			return clone;
		}
	}
	
	private Map<Integer, Node> getNodeMap(Node node) {
		Map<Integer, Node> map = new HashMap<>();
		Deque<Node> stack = new ArrayDeque<>();
		Set<Integer> visited = new HashSet<>();
		stack.push(node);
		while(!stack.isEmpty()) {
			Node curr = stack.pop();
			if(!visited.contains(curr.val)) {
				map.put(curr.val, new Node(curr.val));
				stack.addAll(curr.neighbors);
				visited.add(curr.val);
			}
		}
		return map;
	}
	
	private Node cloneGraph(Node node, Map<Integer, Node> map) {
		Set<Integer> visited = new HashSet<>();
		Deque<Node> stack = new ArrayDeque<>();
		stack.push(node);
		while(!stack.isEmpty()) {
			Node curr = stack.pop();
			Node clone = map.get(curr.val);
			if(!visited.contains(curr.val)) {
				for(Node neighbour : curr.neighbors) {
					clone.neighbors.add(map.get(neighbour.val));
					stack.push(neighbour);
				}
				visited.add(curr.val);
			}
		}
		return map.get(node.val);
	}
	
	private List<List<Node>> getAdjacencyList(Node orig) {
		if(orig == null) 
			return null;
		List<List<Node>> adjacency = new ArrayList<>();
		Deque<Node> stack = new ArrayDeque<>();
		List<Node> visited = new ArrayList<>();
		stack.push(orig);
		while(!stack.isEmpty()) {
			Node node = stack.pop();
			if(!visited.contains(node)) {				
				List<Node> neighbours = new ArrayList<>();
				for(Node neighbour : node.neighbors) {
					neighbours.add(new Node(neighbour.val));
					if(!visited.contains(neighbour)) {
						stack.push(neighbour);
					}
				}
				adjacency.add(neighbours);
				visited.add(node);
			}
		}
		return adjacency;
	}
	
	@SuppressWarnings("unused")
	private void cloneGraph(Node node, Map<Integer, Node> map, Set<Integer> visited) {
		if(visited.contains(node.val)) {
			return;
		}
		visited.add(node.val);
		Node clone = map.get(node.val);
		for(Node n : node.neighbors) {
			clone.neighbors.add(map.get(n.val));
			cloneGraph(n, map, visited);
		}
	}
	
	@SuppressWarnings("unused")
	private static class Node {
		
		public int val;
		public List<Node> neighbors;

		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
		
		@Override
		public String toString() {
			return val + "";
		}
	}
}
