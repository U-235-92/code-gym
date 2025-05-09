package aq.gym.contests.other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class MaxMultiplicationOfThree {

	private static final int MIN = (int) Math.pow(10, 6) * (-1);
	private static final int MAX = (int) Math.pow(10, 6);
	
	public static void main(String[] args) {
		getMaxMultipicators();
	}
	
	@SuppressWarnings("unused")
	private static void getMaxMultipicators() {
		Scanner scanner = new Scanner(System.in);
		int[] nums = Arrays
				.stream(scanner.nextLine().split("\\s"))
				.map(Integer::valueOf)
				.peek(num -> {
					if(num < MIN || num > MAX) {
						scanner.close();
						throw new IllegalArgumentException();
					}
				})
				.sorted(Comparator.comparing(num -> (int) num).reversed())
				.mapToInt(Integer::valueOf)
				.toArray();
		scanner.close();
		if(nums.length < 3 || nums.length > (int) Math.pow(10, 5)) {
			throw new IllegalArgumentException();
		}
		int[] minimals = minimals(nums);
		int[] maximals = maximals(nums);
		long max1 = (long) minimals[0] * minimals[1] * maximals[0];
		long max2 = (long) maximals[0] * maximals[1] * maximals[2];
		if(max1 == Long.max(max1, max2)) {
			Arrays.stream(minimals).forEach(num -> System.out.print(num + " "));
			System.out.print(maximals[0]);
		} else {
			Arrays.stream(maximals).forEach(num -> System.out.print(num + " "));
		}
	}
		
	@SuppressWarnings("unused")
	private static void randomTest() {
		int n = (int) Math.pow(10, 1);
		int[] nums = IntStream
				.generate(nextNumber(MIN, MAX))
				.limit(n)
				.boxed()
				.sorted(Comparator.comparing(num -> (int) num).reversed())
				.mapToInt(Integer::valueOf)
				.toArray();
		int[] minimals = minimals(nums);
		int[] maximals = maximals(nums);
		long max1 = (long) minimals[0] * minimals[1] * maximals[0];
		long max2 = (long) maximals[0] * maximals[1] * maximals[2];
		if(max1 == Long.max(max1, max2)) {
			Arrays.stream(minimals).forEach(num -> System.out.print(num + " "));
			System.out.print(maximals[0]);
		} else {
			Arrays.stream(maximals).forEach(num -> System.out.print(num + " "));
		}
	}
	
	private static IntSupplier nextNumber(int min, int max) {
		return () -> min + (int)(Math.random() * ((max - min) + 1));
	}
	
	private static int[] minimals(int[] nums) {
		int[] result = new int[2];
		for(int i = nums.length - 1, j = 0; j < result.length; i--, j++) {
			result[j] = nums[i];
		}
		return result;
	}
	
	private static int[] maximals(int[] nums) {
		int[] result = new int[3];
		for(int i = 0; i < result.length; i++) {
			result[i] = nums[i];
		}
		return result;
	}
}
