package aq.gym.contests.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MinimalCoinsCombination {

	public static void main(String[] args) {
		getMinimalCoinsCombination();
	}

	private static void getMinimalCoinsCombination() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] coins = {1, 5, 10, 20, 50};
			int money = Integer.valueOf(br.readLine());
			Map<Integer, Integer> result = new HashMap<>();
			for(int i = coins.length - 1; i >= 0; i--) {
				int currentCoin = coins[i];
				if(money / currentCoin > 0) {					
					int currentCoinNumber = (int) Math.floor(money / currentCoin);
					money = money - (currentCoinNumber * currentCoin);
					result.put(currentCoin, currentCoinNumber);
				}
			}
			System.out.println(result.values().stream().reduce(0, Integer::sum));
			result.forEach((k, v) -> {
				for(int i = 0; i < v; i++) {
					System.out.print(k + " ");
				}
			});
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
