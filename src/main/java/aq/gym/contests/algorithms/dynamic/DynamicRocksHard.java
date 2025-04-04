package aq.gym.contests.algorithms.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DynamicRocksHard {

	private static final char W = 'W';
	private static final char L = 'L';
	
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] rocks = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			char[][] game = new char[rocks[0] + 1][rocks[1] + 1];
			game[0][0] = L;
			game[1][1] = L;
			game[0][1] = W;
			game[1][0] = W;
			for(int i = 2; i < game.length; i++) {
				for(int j = 0; j < 2; j++) {
					if(game[i - 2][j] == L || game[i - 1][j] == L) {
						game[i][j] = W;
					} else {
						game[i][j] = L;
					}
				}
			}
			for(int i = 0; i < 2; i++) {
				for(int j = 2; j < game[i].length; j++) {
					if(game[i][j - 2] == L || game[i][j - 1] == L) {
						game[i][j] = W;
					} else {
						game[i][j] = L;
					}
				}
			}
			for(int i = 2; i < game.length; i++) {
				for(int j = 2; j < game[i].length; j++) {
					if(game[i - 1][j] == W && game[i - 2][j] == W && game[i - 2][j - 1] == W) {
						game[i][j] = L;
					} else {
						game[i][j] = W;
					}
				}
			}
//			Arrays.stream(game).forEach(arr -> System.out.println(Arrays.toString(arr)));
			if(game[game.length - 1][game[0].length - 1] == L) {
				System.out.println("Lose");
			} else {
				System.out.println("Win");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
