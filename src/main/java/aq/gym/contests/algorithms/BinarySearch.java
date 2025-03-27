package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BinarySearch {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			@SuppressWarnings("unused")
			long n = Long.valueOf(br.readLine());
			long[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			long q = Long.valueOf(br.readLine());
			int idx = binarySearch(0, numbers.length - 1, numbers, q);
			System.out.println(idx);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static int binarySearch(int left, int right, long[] numbers, long q) {
		while(left <= right) {			
			int mid = (left + right) / 2;
			if(numbers[mid] == q) {
				return mid;
			} else if(numbers[mid] > q) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}
}
