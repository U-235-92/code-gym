package aq.gym.algorithms_and_structures.recursion.rectangles;

public class MaxSquareCalculator {

	public int getMaxSquareSideLength(int firstRectangleSideLength, int secondRectangleSideLength) {
		if(firstRectangleSideLength <= 0 || secondRectangleSideLength <= 0) 
			throw new IllegalArgumentException("Wrong parameters of side length(s). It can't be <= 0");
		if(firstRectangleSideLength > secondRectangleSideLength) 
			return getMax(firstRectangleSideLength, secondRectangleSideLength);
		else if(firstRectangleSideLength < secondRectangleSideLength) 
			return getMax(secondRectangleSideLength, firstRectangleSideLength);
		else
			return firstRectangleSideLength;
	}
	
	private int getMax(int greaterRectngleSideLength, int lesserRectangleSideLength) {
		int squareDetector = greaterRectngleSideLength % lesserRectangleSideLength;
		if(squareDetector == 0) 
			return lesserRectangleSideLength;
		return getMax(lesserRectangleSideLength, squareDetector);
	}
}
