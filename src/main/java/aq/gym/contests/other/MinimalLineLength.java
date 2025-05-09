package aq.gym.contests.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class MinimalLineLength {

	public static void main(String[] args) {
		getMinimalLineLength();
//		stressTest();
//		binaryTest(); 
	}
	
	private static void getMinimalLineLength() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int[] data = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
			int k = data[1];
			int[] points = Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::valueOf).sorted().toArray();
			int minimalLength = 0;
			if(k >= points.length) {
				System.out.println(minimalLength);
			} else if(k == 1) {
				minimalLength = points[points.length - 1] - points[0];
				System.out.println(minimalLength);
			} else {
				minimalLength = getMinimalLengthBinary(k, points);
				System.out.println(minimalLength);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void binaryTest() {
		int[] points = {2, 3, 11, 12, 12};
		int k = 3;
		int binary = getMinimalLengthBinary(k, points);
		System.out.println(binary);
	}
	
	private static void stressTest() {
		while(true) {
			int n = 5;
			int k = 3; // 1 <= k <= 10^5
			int min = 1, max = 20; // 1 <= xi <= 10^9
			int[] points = IntStream.generate(() -> (int) (Math.random() * (max - min) + 1)).limit(n).sorted().toArray();
			int naive = getMinimalLengthNaive(k, points);
			int binary = getMinimalLengthBinary(k, points);
			if(naive == binary) {
				System.out.println("OK");
			} else {
				System.out.println("W/A");
				System.out.println("Naive: " + naive + ", Binary: " + binary);
				System.out.println("Points: " + Arrays.toString(points));
				System.out.println("K: " + k);
				return;
			}
		}
	}
	
	private static int getMinimalLengthBinary(int k, int[] points) {
		int lineCount = 1;
		int left = 0, right = points[points.length - 1] - points[0];
		int resultMinimalLength = Integer.MAX_VALUE;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			int border = points[0] + mid;
			for(int i = 1; i < points.length; i++) {
				if(points[i] > border && points[i] != points[i - 1]) {
					lineCount++;
					border = points[i] + mid;
				}
			}
			if(lineCount > k) {
				left = mid + 1;
			} else {
				resultMinimalLength = mid;
				right = mid - 1;
			}
			lineCount = 1;
		}
		return resultMinimalLength;
	}
	
	private static int getMinimalLengthNaive(int k, int[] points) {
		int minimalLength = 0;
		int lineCount = 1;
		Arrays.sort(points);
		Set<Integer> lengthes = new TreeSet<>();
		for(int i = 0; i < points.length; i++) {
			for(int j = i; j < points.length; j++) {
				lengthes.add(points[j] - points[i]);
			}
		}
		for(Integer length : lengthes) {
			int border = points[0] + length;
			for(int i = 1; i < points.length; i++) {
				if(points[i] > border) {
					lineCount++;
					border = points[i] + length;
				}
			}
			if(lineCount == k) {
				minimalLength = length;
				break;
			} else {
				lineCount = 1;
			}
		}
		return minimalLength;
	}
}
