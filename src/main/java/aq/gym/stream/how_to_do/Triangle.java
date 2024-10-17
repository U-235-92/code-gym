package aq.gym.stream.how_to_do;

import java.util.stream.IntStream;

public class Triangle {

	public static void main(String[] args) {
		printAllowedTriangleSides(3, 4, 5);
	}

	private static void printAllowedTriangleSides(int a, int b, int c) {
		int ab = IntStream.of(a, b).sum();
		int bc = IntStream.of(b, c).sum();
		int ac = IntStream.of(a, c).sum();
		if(ab > c && bc > a && ac > b) {
			System.out.println("You can build tringle [" + String.join(", ", a + "", b + "", c + "") + "]");
		} else {
			System.out.println("You can't build tringle [" + String.join(", ", a + "", b + "", c + "") + "]");
		}
	}
}
