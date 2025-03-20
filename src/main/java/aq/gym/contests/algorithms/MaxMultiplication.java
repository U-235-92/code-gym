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
		app();
	}
	
//	
	private static void app() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			int allowCompairsNumber = (2 * n) - 3;
			if(allowCompairsNumber > (1.5 * n)) {				
				calculateCompairs(n);
			} else {
				System.out.println("No");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void calculateCompairs(int n) {
		int arrayLength = n; //2 <= n <= 200_000
		int numberLimit = 200_000; //0 <= a <= 200_000
		while(true) {
			long[] numbers = LongStream
					.generate(() -> (long) (Math.random() * numberLimit))
					.limit(arrayLength)
					.toArray();
			long comparesNumber = countCompares(numbers) * 2;
			if(comparesNumber > 1.5 * n) { 
				System.out.println("Yes");
				Arrays.stream(numbers).forEach(num -> System.out.print(num + " "));
				return;
			} 
		}
	}
	
	private static long countCompares(long[] numbers) {
		long firstMax = numbers[0], secondMax = numbers[1], compareCount = 0;
		if(numbers[1] > numbers[0]) {
			long tmp = numbers[0];
			numbers[0] = numbers[1];
			numbers[1] = tmp;
			firstMax = numbers[0];
			secondMax = numbers[1];
		}
        compareCount++;
		for(int i = 2; i < numbers.length; i++) {
			if(numbers[i] > firstMax) {
				secondMax = firstMax;
				firstMax = numbers[i];
			} else {
				if(numbers[i] > secondMax) {
					compareCount++;
					secondMax = numbers[i];
				}
			}
			compareCount++;
		}
		return compareCount;
	}	

	@SuppressWarnings("unused")
	private static void productOfFour() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			long[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			Arrays.sort(numbers);
			long a = numbers[numbers.length - 1];
			long b = numbers[numbers.length - 2];
			long c = numbers[numbers.length - 3];
			long d = numbers[numbers.length - 4];
			long x = numbers[0];
			long y = numbers[1];
			long z = numbers[2];
			long h = numbers[3];
			long resultA = a * b * c * d;
			long resultB = x * y * z * h;
			long resultC = a * b * x * y;
			long result = Long.max(Long.max(resultA, resultB), resultC);
			System.out.println(result);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void productOfThree() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			long[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			Arrays.sort(numbers);
			long a = numbers[numbers.length - 1];
			long b = numbers[numbers.length - 2];
			long c = numbers[numbers.length - 3];
			long x = numbers[0];
			long y = numbers[1];
			long resultA = a * b * c;
			long resultB = x * y * a;
			long result = Long.max(resultA, resultB);
			System.out.println(result);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void productOfTwo() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			long n = Long.valueOf(br.readLine());
			long[] numbers = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			long result = N2Product.productOfTwo(numbers);
			System.out.println(result);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static class NProduct {
		
		private static long productOfTwo(long[] numbers) {
			long firstMax = 0, secondMax = 0;
			if(numbers[1] > numbers[0]) {
				long tmp = numbers[0];
				numbers[0] = numbers[1];
				numbers[1] = tmp;
				firstMax = numbers[0];
				secondMax = numbers[1];
			}
			for(int i = 2; i < numbers.length; i++) {
				if(numbers[i] > firstMax) {
					secondMax = firstMax;
					firstMax = numbers[i];
				} else {
					if(numbers[i] > secondMax) {
						secondMax = numbers[i];
					}
				}
			}
			return firstMax * secondMax;
		}
	}
	
	@SuppressWarnings("unused")
	private static class N2Product {
		
		private static long productOfTwo(long[] numbers) {
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
	}
	
	@SuppressWarnings("unused")
	private static class N1_5Product {
		
		private static long productOfTwo(long[] numbers) {
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
	}
	
	@SuppressWarnings("unused")
	private static class NaiveProduct {
		
		private static long productOfTwo(long[] numbers) {
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
		
		private static long productOfThree(long[] numbers) {
			long maxMultiplication = Long.MIN_VALUE;
			for(int i = 0; i < numbers.length; i++) {
				for(int j = i + 1; j < numbers.length; j++) {
					for(int k = j + 1; k < numbers.length; k++) {
						long a = numbers[i];
						long b = numbers[j];
						long c = numbers[k];
						long d = a * b * c;
						if(d > maxMultiplication) {
							maxMultiplication = d;
						}
					}
				}
			}
			return maxMultiplication;
		}
	}
	
	private static class SortProduct {
		
		private static long productOfThree(long[] numbers) {
			Arrays.sort(numbers);
			long a = numbers[numbers.length - 1];
			long b = numbers[numbers.length - 2];
			long c = numbers[numbers.length - 3];
			long x = numbers[0];
			long y = numbers[1];
			long resultA = a * b * c;
			long resultB = x * y * a;
			return Long.max(resultA, resultB);
		}
	}
	
	@SuppressWarnings("unused")
	private static class Test {
		@SuppressWarnings("unused")
		private static void staticN2Test(long[] numbers) {
			long result = N2Product.productOfTwo(numbers);
			System.out.println(result);
		}
		
		private static void staticN1_5Test() {
			long[] numbers = new long[] {4, 4, 7, 8, 0, 2};
			long result = N1_5Product.productOfTwo(numbers);
			System.out.println(result);
		}
		
		@SuppressWarnings("unused")
		private static void n2TimeTest() {
			int arrayLength = 10000;
			int numberLimit = 2 * 100000;
			long[] numbers = LongStream.generate(() -> (long) (Math.random() * numberLimit)).limit(arrayLength).toArray();
			long start = System.currentTimeMillis();
			long result = N2Product.productOfTwo(numbers);
			long end = System.currentTimeMillis();
			System.out.println(result);
			System.out.println("Work time: " + Duration.between(Instant.ofEpochMilli(start), Instant.ofEpochMilli(end)).toMillis() + " ms.");
		}
		
		@SuppressWarnings("unused")
		private static void stressTestProductOfTwo() {
			int arrayLength = 20000;
			int numberLimit = 2 * 100000;
			long okCount = 1;
			while(true) {
				long[] numbers = LongStream.generate(() -> (long) (Math.random() * numberLimit)).limit(arrayLength).toArray();
				long nPow2naiveResult = NaiveProduct.productOfTwo(numbers);
				long n1_5Result = N1_5Product.productOfTwo(numbers);
				if(nPow2naiveResult == n1_5Result) {
					System.out.println("OK " + (okCount++));
				} else {
					System.out.println("W/A: naive result: " + nPow2naiveResult + ", n1_5 result: " + n1_5Result);
					System.out.println("Input array data: " + Arrays.toString(numbers));
					return;
				}
			}
		}
		
		@SuppressWarnings("unused")
		private static void stressTestProductOfThree() {
			int arrayLength = 2000;
			int numberLimitUp = 100;
			int numberLimitDown = -100;
			long okCount = 1;
			while(true) {
				long[] numbers = LongStream
						.generate(() -> numberLimitDown + (long) (Math.random() * (numberLimitUp - numberLimitDown) + 1))
						.limit(arrayLength)
						.toArray();
//				long[] numbers = {-68, -44, -10, 56, 71};
				long naiveResult = NaiveProduct.productOfThree(numbers);
				long alternativeResult = SortProduct.productOfThree(numbers); 
				if(naiveResult == alternativeResult) {
					System.out.println("OK " + (okCount++));
//					System.out.println("Input array data: " + Arrays.toString(numbers));
				} else {
					System.out.println("W/A: naive result: " + naiveResult + ", Alternative result: " + alternativeResult);
					System.out.println("Input array data: " + Arrays.toString(numbers));
					return;
				}
			}
		}
	}
}
