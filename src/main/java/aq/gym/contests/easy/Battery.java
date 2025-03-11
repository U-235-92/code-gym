package aq.gym.contests.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Battery {

	public static void main(String[] args) {
		battery();
	}
	
	private static void battery() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			final int minAppCount = 1, maxAppCount = (int) Math.pow(10, 5);
			final int minAppPercent = 1, maxAppPercent = (int) Math.pow(10, 2);
			int runner = 0;
			int appsCount = Integer.valueOf(br.readLine());
			if(appsCount < minAppCount || appsCount > maxAppCount) {
				throw new IllegalArgumentException();
			}
			String appsConsuptionLine = br.readLine();
			int[] appsConsumption = new int[appsCount];
			for(String str : appsConsuptionLine.split("\\s")) {
				int consumption = Integer.valueOf(str);
				if(consumption < minAppPercent || consumption > maxAppPercent) {
					throw new IllegalArgumentException();
				}
				appsConsumption[runner++] = consumption;
			}
			int totalConsumption = Arrays.stream(appsConsumption).sum();
			int timeWork = 100 / totalConsumption;
			System.out.println(timeWork);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
