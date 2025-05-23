package aq.gym.numbers_numbers_numbers;

public class MinMaxNumber {

	public static void main(String[] args) {
		Integer[] numbers = new Integer[]{1234, 222, 45, 987};
		printMin(numbers);
		printMax(numbers);
	}
	
	public static void printMin(Integer[] numbers) {
		int min = Integer.MAX_VALUE;
		for(int number : numbers) {
			if(number < min) {
				min = number;
			}
		}
		System.out.println("Min number: " + min);
	}
	
	public static void printMax(Integer[] numbers) {
		int max = Integer.MIN_VALUE;
		for(int number : numbers) {
			if(number > max) {
				max = number;
			}
		}
		System.out.println("Max number: " + max);
	}
}
