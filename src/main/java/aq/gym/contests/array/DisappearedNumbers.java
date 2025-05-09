package aq.gym.contests.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DisappearedNumbers {

	public static void main(String[] args) {
//		int[] nums = {1,1};
//		List<Integer> result = new DisappearedNumbers().findDisappearedNumbersByInPlace(nums);
//		System.out.println(result);
		new DisappearedNumbers().test();
	}

	private void test() {
		while(true) {
			int n = 8;
			int[] nums = IntStream.generate(() -> 1 + (int) (Math.random() * n)).limit(n).toArray();
			List<Integer> bySet = findDisappearedNumbersBySet(nums);
			List<Integer> byInPlace = findDisappearedNumbersByInPlace(nums);
			if(bySet.equals(byInPlace)) {
				System.out.println("OK");
			} else {
				System.out.println("W/A");
				return;
			}
		}
	}
	
	public List<Integer> findDisappearedNumbersBySet(int[] nums) {
		List<Integer> result = new ArrayList<>();
		Set<Integer> fullNumbersSet = Stream
				.iterate(1, num -> Integer.sum(num, 1))
				.limit(nums.length)
				.collect(Collectors.toCollection(HashSet::new));
		Set<Integer> origNumbersSet = Arrays
				.stream(nums)
				.boxed()
				.collect(Collectors.toCollection(HashSet::new));
		fullNumbersSet.removeAll(origNumbersSet);
		result.addAll(fullNumbersSet);
		return result;
	}
	
	public List<Integer> findDisappearedNumbersBySetAdvenced(int[] nums) {
		List<Integer> result = new ArrayList<>();
		Set<Integer> fullNumbersSet = Stream
				.iterate(1, num -> Integer.sum(num, 1))
				.limit(nums.length)
				.collect(Collectors.toCollection(HashSet::new));
		for(int num : nums) {
			if(fullNumbersSet.contains(num))
				fullNumbersSet.remove(num);
		}
		result.addAll(fullNumbersSet);
		return result;
	}
	
	public List<Integer> findDisappearedNumbersByInPlace(int[] nums) {
		List<Integer> result = new ArrayList<>();
		for(int i = 0; i < nums.length; i++) {
			int idx = nums[i];
			if(idx >= 0) {
				idx = nums[i] - 1;
				if(nums[idx] >= 0) {					
					nums[idx] = -nums[idx];
				}
			} else {
				idx = (-1 * idx) - 1;
				if(nums[idx] >= 0) {					
					nums[idx] = -nums[idx];
				}
			}
		}
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] > 0) {
				result.add(i + 1);
			}
		}
		return result;
	}
}
