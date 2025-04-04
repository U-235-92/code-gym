package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DynamicBackpack {

	public static void main(String[] args) {
		app();
	}
	
	private static void app() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int[] weights = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int backpackVolume = data[0];
			int numberOfGoods = data[1];
			if(numberOfGoods == 1) {
				if(weights[0] > backpackVolume) {
					System.out.println(0);
				} else {
					System.out.println(weights[0]);
				}
			} else {				
				int maxLootValue = getMaximalLoot(backpackVolume, weights);
				System.out.println(maxLootValue);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static int getMaximalLoot(int backpackVolume, int[] weights) {
		int maxLootValue = 0;
		int[][] lootTable = new int[weights.length][backpackVolume + 1];
		for(int i = 0, w = 0; i < lootTable.length; i++, w++) {
			int currentWeight = weights[w];
			for(int j = 0; j < lootTable[i].length; j++) {
				if(j - currentWeight >= 0) {					
					if(i > 0) {						
						int previousMaxWeight = lootTable[i - 1][j];
						int dynamicMaxWeight = currentWeight + lootTable[i - 1][j - currentWeight];
						lootTable[i][j] = Integer.max(previousMaxWeight, dynamicMaxWeight);
					} else {
						lootTable[i][j] = currentWeight;
					}
				} else {
					if(i > 0) {						
						lootTable[i][j] = lootTable[i - 1][j];
					} 
				}
			}
		}
		maxLootValue = lootTable[lootTable.length - 1][lootTable[0].length - 1];
		return maxLootValue;
	}
}
