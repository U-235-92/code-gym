package aq.gym.algorithms_and_structures.dijkstra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Map<Node, Map<Node, Integer>> graph = new HashMap<Node, Map<Node,Integer>>();
		Node s = new Node("S");
		Node l = new Node("L");
		Node p = new Node("P");
		Node f = new Node("F");
		s.setNeighbours(List.of(l, p));
		l.setNeighbours(List.of(f));
		p.setNeighbours(List.of(l, f));
		graph.put(s, Map.of(l, 6, p, 3));
		graph.put(l, Map.of(f, 1));
		graph.put(p, Map.of(l, 2, f, 2));
		Dijkstra dijkstra = new Dijkstra(s, f, graph);
		dijkstra.calculatePath();
	}

}
