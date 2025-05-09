package aq.gym.contests.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MoneyExchange {

	public static void main(String[] args) {
		greedyExchange();
	}
	
	@SuppressWarnings("unused")
	private static void greedyExchange() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] coins = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int money = Integer.valueOf(br.readLine());
			Map<Integer, Integer> result = new HashMap<>();
			for(int i = 0; i < coins.length; i++) {
				int coinCount = (int) Math.floor(money / coins[i]);
				money = money - (coinCount * coins[i]);
				if(coinCount > 0) {					
					result.put(coins[i], coinCount);
				}
			}
			result.entrySet().forEach(e -> System.out.println(e.getValue() + " x " + e.getKey()));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
