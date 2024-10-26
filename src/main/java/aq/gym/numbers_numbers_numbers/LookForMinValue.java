package aq.gym.numbers_numbers_numbers;

import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LookForMinValue {

	public static void main(String[] args) {
		printMaxByIntStreamWay();
		printMaxByReduceOperationWay();
		printMaxByCollectorOperationWay();
	}

	private static void printMaxByIntStreamWay() {
		List<Integer> numbers = Stream.iterate(1, val -> val + 1).limit(100).unordered().collect(Collectors.toList());
		Collections.shuffle(numbers);
		numbers.stream().mapToInt(Integer::intValue).max().ifPresent(System.out::println);
	}
	
	private static void printMaxByReduceOperationWay() {
		List<Integer> numbers = Stream.iterate(1, val -> val + 1).limit(100).unordered().collect(Collectors.toList());
		Collections.shuffle(numbers);
		numbers.stream().reduce(Integer::max).ifPresent(System.out::println);
	}
	
	private static void printMaxByCollectorOperationWay() {
		List<Integer> numbers = Stream.iterate(1, val -> val + 1).limit(100).unordered().collect(Collectors.toList());
		Collections.shuffle(numbers);
		IntSummaryStatistics stat = numbers.stream().collect(Collectors.summarizingInt(Integer::intValue));
		System.out.println(stat.getMax());
	}
}
