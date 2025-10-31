package aq.gym.contests.t_bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

public class NumbersOnPaper {

//	https://edu.tbank.ru/selection/76378fbd-1998-48fa-944e-eb736d321f11/practice/244/task/4
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int k = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::valueOf).toArray()[1];
			long[] numbers = Arrays.stream(br.readLine().split("\\s+")).mapToLong(Long::valueOf).toArray();
			long maxDelta = getMaxDelta(numbers, k);
			System.out.println(maxDelta);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void test() {
//		REGULAR TEST CASES
//		long a = (int) Math.pow(10, 9), b = (int) Math.pow(10, 9) - 1;
//		long x = 1000000000L + 1L, y = 9000000000L + 9L;
//		int k = 2;
//		long[] nums = {8L, 5L};
//		long delta = getMaxDelta(nums, k);
//		System.out.println(delta);
//		
//		MIN EDGES TEST CASE
//		long[] nums = {899};
//		int k = 1;
//		long delta = getMaxDelta(nums, k);
//		System.out.println(delta);
//		
//		MAX EDGES TEST CASE
		long min = 1, max = (long) Math.pow(10, 9);
		int limit = (int) Math.pow(10, 3), k = (int) Math.pow(10, 4);
		long[] nums = LongStream.generate(() -> min + (long) (Math.random() * (max - min) + 1)).limit(limit).toArray();
		long delta = getMaxDelta(nums, k);
		System.out.println(delta);
	}
	
	private static long getMaxDelta(long[] numbers, int k) {
		if(k > numbers.length) {
			k = numbers.length;
		}
		Arrays.sort(numbers);
		Map<Long, Long> cache = new HashMap<>();
		final long sumOrigNumbers = Arrays.stream(numbers).sum();
		long maxSumUpdtNumbers = sumOrigNumbers, maxDelta = 0;
		for(int i = 0; i < k; i++) {
			long updtNumber = getUpdateNumberOrCalculate(numbers[i], cache);
			maxSumUpdtNumbers = maxSumUpdtNumbers - numbers[i] + updtNumber;
		}
		maxDelta = Math.max(maxDelta, maxSumUpdtNumbers - sumOrigNumbers);		
		for(int i = 1; i < numbers.length - k + 1; i++) {
			long currSumUpdtNumbers = sumOrigNumbers;
			for(int j = i; j < i + k; j++) {
				long currUpdtNumber = getUpdateNumberOrCalculate(numbers[j], cache);
				currSumUpdtNumbers = currSumUpdtNumbers - numbers[j] + currUpdtNumber;
			}
			if(currSumUpdtNumbers > maxSumUpdtNumbers) {
				maxSumUpdtNumbers = currSumUpdtNumbers;
				maxDelta = Math.max(maxDelta, maxSumUpdtNumbers - sumOrigNumbers);
			} else {
				break;
			}
		}
		return maxDelta;
	}
	
	private static long getUpdateNumberOrCalculate(long number, Map<Long, Long> cache) {
		long updtNumber = 0L;
		if(cache.get(number) == null) {
			updtNumber = calculateUpdatedNumber(number);
			cache.put(number, updtNumber);
		} else {
			updtNumber = cache.get(number);
		}
		return updtNumber;
	}
	
	private static long calculateUpdatedNumber(long number) {
		char[] digits = (number + "").toCharArray();
		for(int d = 0; d < digits.length; d++) {
			int digit = Character.digit(digits[d], 10);
			if(digit != 9) {
				digits[d] = '9';
				break;
			}
		}
		return Long.valueOf(new String(digits));
	}
}
