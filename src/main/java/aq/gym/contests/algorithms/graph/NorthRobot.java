package aq.gym.contests.algorithms.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class NorthRobot {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			Map<Address, List<Address>> graph = new HashMap<>();
			int n = Integer.valueOf(br.readLine());
			for(int i = 0; i < n; i++) {
				String line = br.readLine();
				String[] lineParts = line.split("\\s");
				String fromStreet = lineParts[0];
				int fromNumber = Integer.valueOf(lineParts[1]);
				String toStreet = lineParts[2];
				int toNumber = Integer.valueOf(lineParts[3]);
				Address from = new Address(fromNumber, fromStreet);
				Address to = new Address(toNumber, toStreet);
				graph.computeIfAbsent(from, k -> new ArrayList<Address>());
				graph.computeIfAbsent(to, k -> new ArrayList<Address>());
				graph.computeIfPresent(from, (k, v) -> {
					v.add(to);
					return v;
				});
			}
			String path = getStartAndEndPath(graph);
			System.out.println(path);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static String getStartAndEndPath(Map<Address, List<Address>> graph) {
		if(hasGraphAnyCycle(graph))
			return "-1";
		return getPath(graph);
	}
	

	private static boolean hasGraphAnyCycle(Map<Address, List<Address>> graph) {
//		0 - white, 1 - gray, 3 - black
		Map<Address, Integer> colorMap = new HashMap<>();
		Deque<Address> stack = new ArrayDeque<>();
		for(Address address : graph.keySet()) {
			colorMap.put(address, 0);
		}
		for(Address address : graph.keySet()) {
			if(colorMap.get(address) == 0) {
				stack.push(address);
				while(!stack.isEmpty()) {
					Address current = stack.peek();
					if(colorMap.get(current) == 0) {
						colorMap.put(current, 1);
						List<Address> neghbours = graph.get(current);
						for(Address neighbour : neghbours) {
							if(colorMap.get(neighbour) == 1) {
								return true;
							}
							if(colorMap.get(neighbour) == 0) {
								stack.push(neighbour);
							}
						}
					} else {
						colorMap.put(current, 2);
						stack.pop();
					}
				}
			}
		}
		return false;
	}
	
	private static String getPath(Map<Address, List<Address>> graph) {
		Map<Address, Integer> incommingArrows = new HashMap<>();
		for(Address address : graph.keySet()) {
			incommingArrows.computeIfAbsent(address, k -> 0);
			List<Address> neighbours = graph.get(address);
			for(Address neighbour : neighbours) {
				incommingArrows.computeIfAbsent(neighbour, k -> 0);
				incommingArrows.computeIfPresent(neighbour, (k, v) -> v + 1);
			}
		}
		if(hasMoreThanOnePath(graph, incommingArrows)) {
			return "-1";
		}
		Deque<Address> topologicalSortedAddresses = new ArrayDeque<>();
		Queue<Address> noIncommingArrowsQueue = incommingArrows.entrySet()
				.stream()
				.filter(entry -> entry.getValue() == 0)
				.map(Map.Entry::getKey)
				.collect(Collectors.toCollection(LinkedList::new));
		while(incommingArrows.size() > 0) {
			List<Address> ambiguousIncommingArrowsDetectorList = new LinkedList<>(noIncommingArrowsQueue);
			noIncommingArrowsQueue.clear();
			if(ambiguousIncommingArrowsDetectorList.size() > 1) {
				return "-1";
			}
			Address noIncommingArrowsAddress = ambiguousIncommingArrowsDetectorList.remove(0);
			topologicalSortedAddresses.add(noIncommingArrowsAddress);
			List<Address> noIncommingArrowsAddressNeighbours = graph.get(noIncommingArrowsAddress);
			for(Address neighbour : noIncommingArrowsAddressNeighbours) {
				incommingArrows.compute(neighbour, (k, v) -> {
					if(v - 1 == 0) {
						noIncommingArrowsQueue.add(k);
					}
					return v - 1;
				});
			}
			incommingArrows.remove(noIncommingArrowsAddress);
		}
		return topologicalSortedAddresses.getFirst() + " " + topologicalSortedAddresses.getLast();
	}
	
	private static boolean hasMoreThanOnePath(Map<Address, List<Address>> graph, Map<Address, Integer> incommingArrows) {
		Set<Address> visited = new HashSet<>();
		Queue<Address> noVisited = new LinkedList<>();
		Address start = incommingArrows
				.entrySet()
				.stream()
				.filter(entry -> entry.getValue() == 0)
				.map(Map.Entry::getKey)
				.findFirst()
				.get();
		noVisited.add(start);
		while(noVisited.size() > 0) {
			Address current = noVisited.remove();
			if(!visited.contains(current)) {				
				List<Address> neighbours = graph.getOrDefault(current, Collections.emptyList());
				noVisited.addAll(neighbours);
				visited.add(current);
			}
		}
		return visited.size() != graph.size();
	}
	
	private static class Address {
		
		private int number;
		private String street;
		
		public Address(int number, String street) {
			super();
			this.number = number;
			this.street = street;
		}

		@Override
		public int hashCode() {
			return Objects.hash(number, street);
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null || obj.getClass() != this.getClass()) {
				return false;
			}
			if(obj == this) {
				return true;
			}
			Address node = (Address) obj;
			return node.number == number && street.equals(street);
		}
		
		@Override
		public String toString() {
			return street + " " + number;
		}
	}
}
