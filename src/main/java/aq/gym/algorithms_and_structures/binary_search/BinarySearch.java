package aq.gym.algorithms_and_structures.binary_search;

import java.util.NoSuchElementException;

public class BinarySearch {

	public static void main(String[] args) {
		int[] numbers = {1, 2, 3, 4, 5};
		int pos = search(numbers, 3);
		System.out.println(pos);
	}

	private static int search(int[] numbers, int search) {
		int left = 0, right = numbers.length - 1;
		while(left <= right) {
			int mid = (left + right) / 2;
			int guess = numbers[mid]; 
			if(guess == search) 
				return mid;
			else if(guess > search) 
				right = mid - 1;
			else 
				left = mid + 1;
		}
		throw new NoSuchElementException(search + " not found");
	}
}
