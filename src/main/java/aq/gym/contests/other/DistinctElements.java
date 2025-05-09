package aq.gym.contests.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DistinctElements {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			int[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			Map<Integer, Integer> frequencyAppearNumberMap = new HashMap<>();
			for(int num : numbers) {
				frequencyAppearNumberMap.computeIfAbsent(num, v -> 0);
				frequencyAppearNumberMap.computeIfPresent(num, (k, v) -> v + 1);
			}
			System.out.println(frequencyAppearNumberMap.values().stream().filter(freq -> freq == 1).reduce(0, Integer::sum));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
