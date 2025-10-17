package aq.gym.contests.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EffectiveLoaders {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			@SuppressWarnings("unused")
			int n = Integer.valueOf(br.readLine());
			int[] routeZones = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int maxPath = calculate(routeZones);
			System.out.println(maxPath);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static int calculate(int[] routeZones) {
		int maxPath = 0;
		for(int i = 0; i < routeZones.length; i++) {
			int left = i, right = i;
			while(left >= 0 && right < routeZones.length && routeZones[left] == routeZones[right]) {
				maxPath = Math.max(maxPath, right - left + 1);
				left--; right++;
			}
			left = i; right = i + 1;
			while(left >= 0 && right < routeZones.length && routeZones[left] == routeZones[right]) {
				maxPath = Math.max(maxPath, right - left + 1);
				left--; right++;
			}
		}
		if(maxPath >= 2) {
			return maxPath;
		} else {
			return 0;
		}
	}
}
