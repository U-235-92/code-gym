package aq.gym.contests.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Souvenir {

	public static void main(String[] args) {	
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int n = data[0];
			int budget = data[1];
			List<Integer> costs = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				int souvenirCost = Integer.valueOf(br.readLine()); 
				costs.add(souvenirCost);
			}
			Collections.sort(costs);
			int numberOfSouvenirs = getMaxCountSouvenirs(budget, costs);
			System.out.println(numberOfSouvenirs);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static int getMaxCountSouvenirs(int budget, List<Integer> costs) {
		int numberOfSouvenirs = 0;
		while(budget > 0 && costs.size() > 0) {
			int leastExpensiveSouvenirIdx = 0;
			if(budget >= costs.get(leastExpensiveSouvenirIdx)) {
				int amount = Integer.min(budget, costs.get(leastExpensiveSouvenirIdx));
				budget -= amount;
				numberOfSouvenirs++;
			}
			costs.remove(leastExpensiveSouvenirIdx);
		}
		return numberOfSouvenirs;
	}
}
