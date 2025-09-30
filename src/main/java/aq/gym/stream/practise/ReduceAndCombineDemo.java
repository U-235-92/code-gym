package aq.gym.stream.practise;

import java.util.List;
import java.util.stream.Stream;

public class ReduceAndCombineDemo {

	public static void main(String[] args) {
		parallelStreamReduceExample();
		regularStreamReduceExample();
	}

	private static void parallelStreamReduceExample() {
		System.out.println("Parallel stream reduce work example:");
		List<Integer> parallelStreamReduceWorkExample = Stream.iterate(1, n -> n + 1).limit(3).toList(); 
		int parallelStreamResult = parallelStreamReduceWorkExample.parallelStream().reduce(5, 
				(a, b) -> {
					System.out.println(String.format("Accumulator call: %d + %d", a, b));
					return a + b;
		}, 
				(a, b) -> {
					System.out.println(String.format("Combiner call: %d * %d", a, b));
					return a * b;
		});
		System.out.println(parallelStreamResult);
	}
	
	private static void regularStreamReduceExample() {
		System.out.println("Regular stream reduce work example:");
		List<Integer> regularStreamReduceWorkExample = Stream.iterate(1, n -> n + 1).limit(3).toList(); 
		int regularStreamResult = regularStreamReduceWorkExample.stream().reduce(5, 
				(a, b) -> {
					System.out.println(String.format("Accumulator call: %d + %d", a, b));
					return a + b;
		}, 
				(a, b) -> {
					System.out.println(String.format("Combiner call: %d * %d", a, b));
					return a * b;
		});
		System.out.println(regularStreamResult);
	}
}
