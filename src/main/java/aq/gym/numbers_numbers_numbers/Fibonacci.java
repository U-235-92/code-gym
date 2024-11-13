package aq.gym.numbers_numbers_numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Fibonacci {

	public static void main(String[] args) {
		System.out.println(cycleWay(10));
		System.out.println(streamWay(10)); 
		System.out.println(recursionWay(9));
	}
	
	private static List<Integer> cycleWay(int limit) {
		List<Integer> result = new ArrayList<Integer>();
		int a = 0, b = 1;
		result.add(a);
		result.add(b);
		for(int i = 0; i < limit - 2; i++) {
			int num = a + b;
			a = b;
			b = num;
			result.add(num);
		}
		return result;
	}
	
	private static List<Integer> streamWay(int limit) {
		return Stream
				.iterate(new int[] {0, 1}, arr -> new int[] { arr[1], arr[0] + arr[1] })
				.limit(limit)
				.map(arr -> arr[0])
				.collect(Collectors.toList());
	}
	
	private static int recursionWay(int limit) {
		if(limit <= 1) {
			return limit;
		}
		return recursionWay(limit - 2) + recursionWay(limit - 1);
	}
}
