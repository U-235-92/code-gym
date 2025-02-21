package aq.gym.algorithms_and_structures.min_cost;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinCost {

	public static void main(String[] args) {
		int[] costs = {5, 4, 2, 8};
		int minCost = calculateMinCost(costs);
		System.out.println(minCost);
	}
	
	public static int calculateMinCost(int[] costs) {
		int minCost = 0;
		PriorityQueue<Integer> costQueue = Arrays.stream(costs)
				.collect(PriorityQueue::new, PriorityQueue::add, PriorityQueue::addAll);
		while(costQueue.size() > 1) {
			int firstCost = costQueue.remove();
			int secondCost = costQueue.remove();
			int totalCost = firstCost + secondCost;
			costQueue.add(totalCost);
			minCost += totalCost;
		}
		return minCost;
	}
}
