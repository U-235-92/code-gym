package aq.gym.contests.algorithms.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Backpack {

	public static void main(String[] args) {
		getMaximumLoot();
	}
	
	private static void getMaximumLoot() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//			List<Pair> weigtCostPairs = new ArrayList<>(List.of(Pair.of(60, 20), Pair.of(100, 50), Pair.of(120, 30)));
//			List<Pair> weigtCostPairs = new ArrayList<>(List.of(Pair.of(500, 30)));
			List<Pair> weigtCostPairs = new ArrayList<>();
			int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int n = data[0];
			int backpackWeight = data[1];
			for(int i = 0; i < n; i++) {
				data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
				weigtCostPairs.add(Pair.of(data[0], data[1]));
			}
			weigtCostPairs.sort(Comparator.comparing(Pair::getUnitCost).reversed());
			double maxLoot = maximumLootRecursion(backpackWeight, weigtCostPairs);
			String formatResult = String.format("%.3f", maxLoot).replace(',', '.');
			System.out.println(formatResult);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static double maximumLootRecursion(int backpackWeight, List<Pair> weigtCostPairs) {
		if(backpackWeight == 0 || weigtCostPairs.size() == 0) {
			return 0;
		}
		int mostExpensiveItemIdx = 0;
		int expItemWeight = weigtCostPairs.get(mostExpensiveItemIdx).getWeight();
		int expItemCost = weigtCostPairs.get(mostExpensiveItemIdx).getCost();
		int amount = Integer.min(backpackWeight, expItemWeight);
		double value = expItemCost * 1.0 / expItemWeight * 1.0 * amount;
		weigtCostPairs.remove(mostExpensiveItemIdx);
		return value + maximumLootRecursion(backpackWeight - amount, weigtCostPairs);
	}
	
	@SuppressWarnings("unused")
	private static double maximumLootCycle(int backpackWeight, List<Pair> weigtCostPairs) {
		double result = 0;
		while(backpackWeight > 0 && weigtCostPairs.size() > 0) {
			int mostExpensiveItemIdx = 0;
			int expItemWeight = weigtCostPairs.get(mostExpensiveItemIdx).getWeight();
			int expItemCost = weigtCostPairs.get(mostExpensiveItemIdx).getCost();
			int amount = Integer.min(backpackWeight, expItemWeight);
			double value = expItemCost * 1.0 / expItemWeight * 1.0 * amount;
			weigtCostPairs.remove(mostExpensiveItemIdx);
			backpackWeight -= amount;
			result += value;
		}
		return result;
	}
	
	private static class Pair {
		
		private int weight;
		private int cost;
		
		private Pair(int cost, int weight) {
			this.weight = weight;
			this.cost = cost;
		}
		
		public static Pair of(int cost, int weight) {
			return new Pair(cost, weight);
		}

		public int getWeight() {
			return weight;
		}

		public int getCost() {
			return cost;
		}
		
		public double getUnitCost() {
			return (double) cost / weight;
		}

		@Override
		public String toString() {
			return "Pair [weight=" + weight + ", cost=" + cost + "]";
		}
	}
}
