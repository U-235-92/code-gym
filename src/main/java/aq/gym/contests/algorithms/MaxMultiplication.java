package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.LongStream;

public class MaxMultiplication {

	public static void main(String[] args) {
		stressTest();
	}
	
	private static long n1_5Multiplication(long[] numbers) {
		int idxFirstMax = 0;
		long firstMax = Long.MIN_VALUE;
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] > firstMax) {
				firstMax = numbers[i];
				idxFirstMax = i;
			}
		}
		int mid = numbers.length / 2;
		swap(numbers, firstMax, idxFirstMax, mid);
		int left = 0, right = numbers.length - 1;
		long secondMax = Long.MIN_VALUE;
		while(left <= right) {
			long a = numbers[left];
			long b = numbers[right];
			if(a > secondMax && left != mid) {
				secondMax = a;
			}
			if(b > secondMax && right != mid) {
				secondMax = b;
			}
			left++; right--;
		}
		long result = firstMax * secondMax;
		return result;
	}
	
	private static void swap(long[] numbers, long value, int from, int to) {
		long tmp = numbers[to];
		numbers[to] = value;
		numbers[from] = tmp;
	}
	
	@SuppressWarnings("unused")
	private static void app() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			long n = Long.valueOf(br.readLine());
			long[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			long result = n2Multiplication(numbers);
			System.out.println(result);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static long n2Multiplication(long[] numbers) {
		int idxFirstMax = 0;
		long firstMax = Long.MIN_VALUE;
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] > firstMax) {
				firstMax = numbers[i];
				idxFirstMax = i;
			}
		}
		long secondMax = Long.MIN_VALUE;
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] > secondMax && i != idxFirstMax) {
				secondMax = numbers[i];
			}
		}
		long maxMultiplication = firstMax * secondMax;
		return maxMultiplication;
	}
	
	private static long naiveMultiplicationNPow2(long[] numbers) {
		long maxMultiplication = Long.MIN_VALUE;
		for(int i = 0; i < numbers.length; i++) {
			for(int j = 0; j < numbers.length; j++) {
				if(i != j) {
					long a = numbers[i];
					long b = numbers[j];
					if(a * b > maxMultiplication) {
						maxMultiplication = numbers[i] * numbers[j];
					}
				}
			}
		}
		return maxMultiplication;
	}
	
	@SuppressWarnings("unused")
	private static void staticN2Test(long[] numbers) {
		long result = n2Multiplication(numbers);
		System.out.println(result);
	}
	
	private static void staticN1_5Test() {
		long[] numbers = new long[] {4, 4, 7, 8, 0, 2};
		long result = n1_5Multiplication(numbers);
		System.out.println(result);
	}
	
	@SuppressWarnings("unused")
	private static void n2TimeTest() {
		int arrayLength = 10000;
		int numberLimit = 2 * 100000;
		long[] numbers = LongStream.generate(() -> (long) (Math.random() * numberLimit)).limit(arrayLength).toArray();
		long start = System.currentTimeMillis();
		long result = n2Multiplication(numbers);
		long end = System.currentTimeMillis();
		System.out.println(result);
		System.out.println("Work time: " + Duration.between(Instant.ofEpochMilli(start), Instant.ofEpochMilli(end)).toMillis() + " ms.");
	}
	
	@SuppressWarnings("unused")
	private static void stressTest() {
		int arrayLength = 2;
		int numberLimit = 2 * 100000;
		long okCount = 1;
		while(true) {
			long[] numbers = LongStream.generate(() -> (long) (Math.random() * numberLimit)).limit(arrayLength).toArray();
			long nPow2naiveResult = naiveMultiplicationNPow2(numbers);
			long n1_5Result = n1_5Multiplication(numbers);
			if(nPow2naiveResult == n1_5Result) {
				System.out.println("OK " + (okCount++));
			} else {
				System.out.println("W/A: naive result: " + nPow2naiveResult + ", n1_5 result: " + n1_5Result);
				System.out.println("Input array data: " + Arrays.toString(numbers));
				return;
			}
		}
	}
}
