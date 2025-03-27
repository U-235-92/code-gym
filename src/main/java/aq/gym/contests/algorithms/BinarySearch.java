package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BinarySearch {

	public static void main(String[] args) {
		multiBinarySearch();
	}

	@SuppressWarnings("unused")
	private static void binarySearch() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			@SuppressWarnings("unused")
			long n = Long.valueOf(br.readLine());
			long[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			long q = Long.valueOf(br.readLine());
			int idx = getElementIndex(0, numbers.length - 1, numbers, q);
			System.out.println(idx);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void multiBinarySearch() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			long n = Long.valueOf(br.readLine());
			long[] sorted = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			long q = Long.valueOf(br.readLine());
			long[] noSorted = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			multiBinarySearch(sorted, noSorted);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void multiBinarySearch(long[] sort, long[] noSort) {
		for(int i = 0; i < noSort.length; i++) {
			int left = 0, right = sort.length - 1;
			int idx = getElementIndex(left, right, sort, noSort[i]);
			System.out.println(idx);
		}
	}
	
	private static int getElementIndex(int left, int right, long[] numbers, long q) {
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
