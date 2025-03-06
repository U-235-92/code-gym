package aq.gym.contests.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class GraphTraversal {

//	4 5
//	2 2
//	3 4
//	2 3
//	1 3
//	2 4
	
//	10 11
//	1 10
//	10 9
//	2 2
//	2 9
//	3 4
//	4 10
//	3 5
//	5 8
//	7 8
//	5 6
//	7 4

	public static void main(String[] args) {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			int[] graphMetaData = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int n = graphMetaData[0];
			int m = graphMetaData[1];
			Map<Node, Set<Node>> graph = new HashMap<>(n);
			Node start = null;
			while(m-- > 0) {
				int[] nodeData = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
				Node node1 = new Node(nodeData[0]);
				Node node2 = new Node(nodeData[1]);
				addNodesInGraph(graph, node1, node2);
				if(start == null) {
					start = node1;
				}
			}
			travel(graph, start);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void addNodesInGraph(Map<Node, Set<Node>> graph, Node node1, Node node2) {
		if(node1.getValue() == node2.getValue()) {
			graph.compute(node1, (k, v) -> {
				if(v == null) {
					Set<Node> neighbours = new HashSet<>();
					neighbours.add(node1);
					return neighbours;
				} else {
					v.add(node1);
					return v;
				}
			});
		} else {
			graph.compute(node1, (k, v) -> {
				if(v == null) {
					Set<Node> neighbours = new HashSet<>();
					neighbours.add(node2);
					return neighbours;
				} else {
					v.add(node2);
					return v;
				}
			});
			graph.compute(node2, (k, v) -> {
				if(v == null) {
					Set<Node> neighbours = new HashSet<>();
					neighbours.add(node1);
					return neighbours;
				} else {
					v.add(node1);
					return v;
				}
			});
		}
	}
	
	private static void travel(Map<Node, Set<Node>> graph, Node start) {
//		graph.entrySet().forEach(System.out::println);
		List<Node> visited = new ArrayList<>();
		Queue<Node> noVisited = new LinkedList<>();
		noVisited.add(start);
		while(noVisited.size() > 0) {
			Node node = noVisited.remove();
			if(!node.isVisited() && !visited.contains(node)) {				
				node.setVisited(true);
				Set<Node> neighbours = graph.get(node);
				noVisited.addAll(neighbours);
				visited.add(node);
			}
		}
		visited.sort(Comparator.comparing(Node::getValue));
		System.out.println(visited.size());
		visited.forEach(node -> System.out.print(node.getValue() + " "));
	}
	
	private static class Node {
		
		private boolean isVisited;
		private int value;
		
		Node(int value) {
			isVisited = false;
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		
		public boolean isVisited() {
			return isVisited;
		}
		
		public void setVisited(boolean isVisited) {
			this.isVisited = isVisited;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(value);
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null)
				return false;
			if(obj == this)
				return true;
			if(obj.getClass() != getClass())
				return false;
			Node node = (Node) obj;
			return node.value == value;
		}

		@Override
		public String toString() {
			return "Node [isVisited=" + isVisited + ", value=" + value + "]";
		}
	}
}
