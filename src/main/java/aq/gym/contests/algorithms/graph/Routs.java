package aq.gym.contests.algorithms.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Routs {

	public static void main(String[] args) {
		app();
	}

	private static void app() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int n = data[0];
			int m = data[1];
			int[][] routsData = new int[m][];
			for(int i = 0; i < m; i++) {
				int[] raw = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
				int[] route = Arrays.copyOfRange(raw, 1, raw.length);
				routsData[i] = route;
				
			}
			int[][] graph1 = getNeighboursStops(routsData, n);
			int[][] graph2 = getStopsWithNoChange(routsData, n);
			for(int[] row : graph1) {
				StringBuilder sb = new StringBuilder();
				for(int s : row) {
					sb.append(s);
					sb.append(" ");
				}
				System.out.println(sb.toString().trim());
			}
			for(int[] row : graph2) {
				StringBuilder sb = new StringBuilder();
				for(int s : row) {
					sb.append(s);
					sb.append(" ");
				}
				System.out.println(sb.toString().trim());
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static int[][] getNeighboursStops(int[][] routsData, int n) {
		int[][] graph = new int[n][n];
		for(int i = 0; i < routsData.length; i++) {
			for(int j = 0; j < routsData[i].length - 1; j++) {
				int l = routsData[i][j] - 1;
				int m = routsData[i][j + 1] - 1;
				graph[l][m] = 1;
				graph[m][l] = 1;
			}
		}
		return graph;
	}

	private static int[][] getStopsWithNoChange(int[][] routsData, int n) {
		int[][] graph = new int[n][n];
		for(int i = 0; i < routsData.length; i++) {
			for(int j = 0; j < routsData[i].length; j++) {
				for(int p = j + 1; p < routsData[i].length; p++) {					
					int l = routsData[i][j] - 1;
					int m = routsData[i][p] - 1;
					graph[l][m] = 1;
					graph[m][l] = 1;
				}
			}
		}
		return graph;
	}
}
