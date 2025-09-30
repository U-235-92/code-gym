package aq.gym.stream.practise;

import java.util.List;

public class StatelessAndStatefullOperationsWorkExample {

	public static void main(String[] args) {
		statefullExample();
		statelessExample();
	}

	private static void statefullExample() {
		System.out.println("Statefull example:");
		List.of(5, 2, 8).stream()
			.filter(num ->{
				System.out.println("filter: " + num);
				return num % 2 == 0;
			})
			.map(num -> {
				System.out.println("map: " + num);
				return num * 1;
			})
			.sorted()
			.peek(num -> System.out.println("peek: " + num))
			.toList();
	}
	
	private static void statelessExample() {
		System.out.println("Stateless example:");
		List.of(5, 2, 8).stream()
			.filter(num ->{
				System.out.println("filter: " + num);
				return num % 2 == 0;
			})
			.map(num -> {
				System.out.println("map: " + num);
				return num * 1;
			})
			.peek(num -> System.out.println("peek: " + num))
			.toList();
	}
}
