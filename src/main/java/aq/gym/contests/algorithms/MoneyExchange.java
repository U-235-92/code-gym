package aq.gym.contests.algorithms;

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
		minimalCoinCountExchangetBruteforce();
//		greedyExchange();
	}

	@SuppressWarnings("unused")
	private static void minimalCoinCountExchangetBruteforce() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] coins = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int money = Integer.valueOf(br.readLine());
			int minimalNumberOfCoins = Integer.MAX_VALUE;
			int totalNumberOfCoins = 0;
			Set<Map<Integer, Integer>> coinCombinations = new HashSet<>();
			for(int i = 0; i < coins.length; i++) {
				Map<Integer, Integer> minimalCoinsMap = new HashMap<>();
				int numberOfCoins = (int) Math.floor(money / coins[i]);
				int remainOfMoney = money - (numberOfCoins * coins[i]);
				totalNumberOfCoins += numberOfCoins;
				minimalCoinsMap.put(coins[i], numberOfCoins);
				if(remainOfMoney == 0) {
					if(totalNumberOfCoins < minimalNumberOfCoins) {
						minimalNumberOfCoins = totalNumberOfCoins;
					}
				} else {
					for(int j = 0; j < coins.length; j++) {
						if(remainOfMoney / coins[j] > 0) {
							numberOfCoins = (int) Math.floor(remainOfMoney / coins[j]);
							totalNumberOfCoins += numberOfCoins;
							remainOfMoney -= (numberOfCoins * coins[j]);
							minimalCoinsMap.put(coins[j], numberOfCoins);
							if(remainOfMoney == 0) {
								if(totalNumberOfCoins < minimalNumberOfCoins) {
									minimalNumberOfCoins = totalNumberOfCoins;
								}
							}
						}
					}
				}
				totalNumberOfCoins = 0;
				coinCombinations.add(minimalCoinsMap);
			}
			coinCombinations.stream()
				.sorted((map1, map2) -> {
					int sumOfValueMap1 = map1.values().stream().mapToInt(Integer::valueOf).sum();
					int sumOfValueMap2 = map2.values().stream().mapToInt(Integer::valueOf).sum();
					return sumOfValueMap1 - sumOfValueMap2;
				})
				.findFirst()
				.stream()
				.forEach(map -> map.forEach((k, v) -> System.out.println(v + " x " + k)));
		} catch(IOException e) {
			e.printStackTrace();
		}
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
