package aq.gym.contests.hash_table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Scoreboard {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine()); //1...50
			Map<String, Integer> playerScoreMap = new HashMap<>();
			for(int i = 0; i < n; i++) {
				playerScoreMap.put(br.readLine(), Integer.valueOf(0));
			}
			int m = Integer.valueOf(br.readLine()); //1...500
			int prevScore1 = 0, prevScore2 = 0;
			for(int i = 0; i < m; i++) {
				String line = br.readLine();
				String player = line.split("\\s")[1];
				int[] currentGameState = Arrays.stream(line.split("\\s")[0].split(":")).mapToInt(Integer::valueOf).toArray();
				int curScore1 = currentGameState[0], curScore2 = currentGameState[1];
				int playerScore = playerScoreMap.get(player);
				playerScore = playerScore + (curScore1 - prevScore1) + (curScore2 - prevScore2);
				playerScoreMap.put(player, playerScore);
				prevScore1 = curScore1;
				prevScore2 = curScore2;
			}
			String result = playerScoreMap.entrySet()
			 .stream()
				.sorted((o1, o2) -> {
					if(o2.getValue().compareTo(o1.getValue()) == 0) {
						return o2.getKey().compareTo(o1.getKey());
					} else {
						return o2.getValue().compareTo(o1.getValue());
					}
				})
				.findFirst()
				.map(entry -> entry.getKey() + " " + entry.getValue())
				.get();
			System.out.println(result);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
