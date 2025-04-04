package aq.gym.contests.algorithms.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DynamicLargestCommonSubsequence {

	public static void main(String[] args) {
		app();
	}

	@SuppressWarnings("unused")
	private static void app() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			int[] line1 = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int m = Integer.valueOf(br.readLine());
			int[] line2 = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int lcs = getLCS(line1, line2);
			System.out.println(lcs);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static int getLCS(int[] line1, int[] line2) {
		int lcs = 0;
		int[][] table = new int[line1.length][line2.length];
		for(int i = 0; i < line1.length; i++) {
			for(int j = 0; j < line2.length; j++) {
				if(line1[i] == line2[j]) {
					if(i > 0 && j > 0) {						
						table[i][j] = table[i - 1][j - 1] + 1;
					} else {
						table[i][j] = 1;
					}
				} else {
					if(i > 0 && j > 0) {
						table[i][j] = Integer.max(table[i - 1][j], table[i][j - 1]);
					} else if(i == 0 && j > 0) {
						table[i][j] = table[i][j - 1];
					} else if(i > 0 && j == 0) {
						table[i][j] = table[i - 1][j];
					}
				}
			}
		}
		lcs = table[table.length - 1][table[0].length - 1];
		return lcs;
	}
}
