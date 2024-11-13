package aq.gym.algorithms_and_structures.recursion.rectangles;

public class Main {

	public static void main(String[] args) {
		MaxSquareCalculator calculator = new MaxSquareCalculator();
		int length = calculator.getMaxSquareSideLength(50, 25);
		System.out.println(length);
	}

}
