package aq.gym.contests.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class DynamicMoneyExchange {

	public static void main(String[] args) {
		app();
	}
	
	private static void app() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int money = Integer.valueOf(br.readLine());
			int minimalCoinCount = getMinimalCoinCount(money);
			System.out.println(minimalCoinCount);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static int getMinimalCoinCount(int money) {
		int minimalCoinCount = 0;
		final int[] coins = {1, 3, 4};
		Map<Integer, Integer> exchangeMap = new LinkedHashMap<>();
		exchangeMap.put(0, 0); //basic case
		for(int i = 1; i <= money; i++) {
			exchangeMap.put(i, Integer.MAX_VALUE);
		}
		for(int currentMoney = 1; currentMoney <= money; currentMoney++) {
			for(int c = 0; c < coins.length; c++) {
				int coin = coins[c];
				if(coin <= currentMoney) {
					int currentMapValue = exchangeMap.get(currentMoney);
					int dynamicMapValue = 1 + exchangeMap.get(currentMoney - coin);
					exchangeMap.put(currentMoney, Integer.min(currentMapValue, dynamicMapValue));
				}
			}
		}
		minimalCoinCount = exchangeMap.get(money);
		return minimalCoinCount;
	}
}
