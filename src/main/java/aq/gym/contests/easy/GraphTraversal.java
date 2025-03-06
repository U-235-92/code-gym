package aq.gym.contests.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
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
			Map<Node, List<Node>> graph = new HashMap<>();
			int[] graphMetaData = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int n = graphMetaData[0];
			int m = graphMetaData[1];
			Node start = null;
			while(m-- > 0) {
				int[] nodeData = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
				Node node1 = new Node(nodeData[0]);
				Node node2 = null;
				if(start == null) {
					start = node1;
				}
				if(nodeData[1] == nodeData[0]) {
					graph.compute(node1, (k, v) -> {
						if(v == null) {
							List<Node> neighbours = new ArrayList<>();
							neighbours.add(node1);
							return neighbours;
						} else {
							v.add(node1);
							return v;
						}
					});
				} else {
					node2 = new Node(nodeData[1]);
					if(graph.get(node1) == null) {
						List<Node> neighbours = new ArrayList<>();
						neighbours.add(node2);
						graph.put(node1, neighbours);
					} else {
						graph.get(node1).add(node2);
					}
					if(graph.get(node2) == null) {
						List<Node> neighbours = new ArrayList<>();
						neighbours.add(node1);
						graph.put(node2, neighbours);
					} else {
						graph.get(node2).add(node1);
					}
				}
			}
			travel(graph, start);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void travel(Map<Node, List<Node>> graph, Node start) {
		List<Node> neighbours = graph.get(start);
		Queue<Node> notVisitedNodes = new LinkedList<>();
		notVisitedNodes.addAll(neighbours);
		List<Node> visitedNodes = new ArrayList<>();
		start.setVisited(true);
		visitedNodes.add(start);
		while(notVisitedNodes.size() > 0) {
			Node node = notVisitedNodes.remove();
			if(!node.isVisited()) {
				node.setVisited(true);
				visitedNodes.add(node);
				neighbours = graph.get(node);
				notVisitedNodes.addAll(neighbours);
			}
		}
		visitedNodes.sort(Comparator.comparing(Node::getValue));
		Set<Node> v = new LinkedHashSet<>(visitedNodes); 
		System.out.println(v.size());
		v.forEach(node -> System.out.print(node.getValue() + " "));
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
