package aq.gym.contests.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DynamicRocksEasy {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String line = br.readLine();
			int[] rocks = Arrays.stream(line.split("\\s")).mapToInt(Integer::valueOf).toArray();
			char[][] game = new char[rocks[0] + 1][rocks[1] + 1];
			
			game[0][0] = 'L';
			for(int i = 1; i < game[0].length; i++) {
				if(game[0][i - 1] == 'L') {
					game[0][i] = 'W';
				} else {
					game[0][i] = 'L';
				}
			}
			for(int i = 1; i < game.length; i++) {
				if(game[i - 1][0] == 'L') {
					game[i][0] = 'W';
				} else {
					game[i][0] = 'L';
				}
			}
			for(int i = 1; i < game.length; i++) {
				for(int j = 1; j < game[i].length; j++) {
					if(game[i - 1][j] == 'W' && game[i - 1][j - 1] == 'W' && game[i][j - 1] == 'W') {
						game[i][j] = 'L';
					} else {
						game[i][j] = 'W';
					}
				}
			}
//			Arrays.stream(game).forEach(arr -> System.out.println(Arrays.toString(arr)));
			if(game[game.length - 1][game[0].length - 1] == 'W') {
				System.out.println("Win");
			} else {
				System.out.println("Lose");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
