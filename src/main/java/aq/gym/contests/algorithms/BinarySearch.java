package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class BinarySearch {

	public static void main(String[] args) {
		countAppearNumbers();
	}
	
	@SuppressWarnings("unused")
	private static void countAppearNumbers() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			long n = Long.valueOf(br.readLine());
			long[] kNumbers = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			long m = Long.valueOf(br.readLine());
			long[] qNumbers = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			String statistic = countAppearNumbersBinary(kNumbers, qNumbers);
			System.out.println(statistic);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void stresTest() {
		while(true) {
			long n = 100000; //1 <= n <= 10^5
			int kMax = 1000000000, kMin = 1; //1 <= k <= 10^9
			long[] kNumbers = LongStream.generate(() -> (long) (Math.random() * (kMax - kMin) + 1)).limit(n).sorted().toArray();
			long m = 100000; //1 <= m <= 10^5
			int qMax = 1000000000, qMin = 1; //1 <= q <= 10^9
			long[] qNumbers = LongStream.generate(() -> (long) (Math.random() * (qMax - qMin) + 1)).limit(m).toArray();
			String naive = countAppearNumbersNaive(kNumbers, qNumbers);
			String binary = countAppearNumbersBinary(kNumbers, qNumbers);
			if(naive.equals(binary)) {
				System.out.println("OK");
			} else {
				System.out.println("W/A");
				System.out.println("Na: " + naive);
				System.out.println("Bi: " + binary);
				System.out.println("K: " + Arrays.toString(kNumbers));
				System.out.println("Q: " + Arrays.toString(qNumbers));
				return;
			}
		}
	}
	
	private static String countAppearNumbersNaive(long[] kNumbers, long[] qNumbers) {
		int[] counts = new int[qNumbers.length];
		for(int i = 0; i < qNumbers.length; i++) {
			int idx = getFirstElementIndexWithDuplicates(kNumbers, qNumbers[i]);
			if(idx != -1) {
				int countOfNumber = 0, j = idx;
				while(j < kNumbers.length && kNumbers[j] == qNumbers[i]) {
					countOfNumber++;
					j++;
				}
				counts[i] = countOfNumber;
			} else {
				counts[i] = 0;
			}
		}
		return Arrays.stream(counts).boxed().map(String::valueOf).collect(Collectors.joining(" "));
	}
	
	private static String countAppearNumbersBinary(long[] kNumbers, long[] qNumbers) {
		int[] counts = new int[qNumbers.length];
		for(int i = 0; i < qNumbers.length; i++) {
			int firstAppearElementIndex = getFirstElementIndexWithDuplicates(kNumbers, qNumbers[i]);
			int lastAppearElementIndex = getLastElementIndexWithDuplicates(kNumbers, qNumbers[i]);
			if(firstAppearElementIndex == -1) {
				counts[i] = 0;
			} else {
				int count = (lastAppearElementIndex - firstAppearElementIndex) + 1;
				counts[i] = count;
			}
		}
		return Arrays.stream(counts).boxed().map(String::valueOf).collect(Collectors.joining(" "));
	}
	
	private static int getFirstElementIndexWithDuplicates(long[] numbers, long q) {
		int left = 0, right = numbers.length - 1;
		int idx = -1;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			if(numbers[mid] == q) {
				idx = mid;
				right = mid - 1;
			} else if(numbers[mid] > q) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return idx;
	}
	
	private static int getLastElementIndexWithDuplicates(long[] numbers, long q) {
		int left = 0, right = numbers.length - 1;
		int idx = -1;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			if(numbers[mid] == q) {
				idx = mid;
				left = mid + 1;
			} else if(numbers[mid] > q) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return idx;
	}
	
	@SuppressWarnings("unused")
	private static void binarySearch() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			@SuppressWarnings("unused")
			long n = Long.valueOf(br.readLine());
			long[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			long q = Long.valueOf(br.readLine());
			int idx = getElementIndex(numbers, q);
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
			int idx = getElementIndex(sort, noSort[i]);
			System.out.println(idx);
		}
	}
	
	private static int getElementIndex(long[] numbers, long q) {
		int left = 0, right = numbers.length - 1;
		return getElementIndex(left, right, numbers, q);
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
