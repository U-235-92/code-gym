package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.LongStream;

public class QuickSort {

	public static void main(String[] args) {
		doWork();
	}
	
	private static void doWork() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			@SuppressWarnings("unused")
			int n = Integer.valueOf(br.readLine());
			long[] nums = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			sort(nums);
			Arrays.stream(nums).forEach(num -> System.out.print(num + " "));
 		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void sort(long[] nums) {
		sort0(nums, 0, nums.length - 1);
	}
	
	private static void sort0(long[] nums, int from, int to) {
		int leftIdx = from, rightIdx = to;
		long mid = nums[(to + from) / 2];
		while(leftIdx <= rightIdx) {
			while(nums[leftIdx] < mid) {
				leftIdx++;
			}
			while(nums[rightIdx] > mid) {
				rightIdx--;
			}
			if(leftIdx <= rightIdx) {
				long tmp = nums[leftIdx];
				nums[leftIdx] = nums[rightIdx];
				nums[rightIdx] = tmp;
				leftIdx++; rightIdx--;
			}
		}
		if(leftIdx < to) {
			sort0(nums, leftIdx, to);
		}
		if(rightIdx > from) {
			sort0(nums, from, rightIdx);
		}
	}
	
	@SuppressWarnings("unused")
	private static void test() {
		int arrLength = 100000;
		long numberLimit = 1000000000;
		long[] nums = LongStream.generate(() -> 1 + (long) (Math.random() * numberLimit)).limit(arrLength).toArray();
		System.out.println(Arrays.toString(nums));
		long start = System.currentTimeMillis();
		sort(nums);
		System.out.println(Arrays.toString(nums));
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
