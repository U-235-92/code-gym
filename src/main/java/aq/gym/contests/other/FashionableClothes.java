package aq.gym.contests.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FashionableClothes {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			long nShirts = Long.valueOf(br.readLine());
			long[] shirts = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			long mTrousers = Long.valueOf(br.readLine());
			long[] trousers = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			long minDelta = Long.MAX_VALUE;
			long minShirt = 0, minTrousers = 0;
			for(int i = 0, j = 0; i < nShirts && j < mTrousers; ) {
				long curShirt = shirts[i];
				long curTrousers = trousers[j];
				if(Math.abs(curShirt - curTrousers) < minDelta) {
					minDelta = Math.abs(curShirt - curTrousers);
					minShirt = curShirt;
					minTrousers = curTrousers;
				} 
				if(curShirt < curTrousers) {
					i++;
				} else {
					j++;
				}
			}
			printResult(minShirt, minTrousers);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void printResult(long a, long b) {
		System.out.println(a + " " + b);
	}
}
