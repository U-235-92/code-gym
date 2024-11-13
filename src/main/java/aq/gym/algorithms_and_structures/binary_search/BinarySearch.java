package aq.gym.algorithms_and_structures.binary_search;

import java.util.NoSuchElementException;

public class BinarySearch {

	public static void main(String[] args) {
		int[] numbers = {1, 2, 3, 4, 5};
		int posRegular = searchRegularWay(numbers, 3);
		System.out.println("Regular way: " + posRegular);
		int posRecursive = searchRecursiveWay(numbers, 3, 0, numbers.length - 1);
		System.out.println("Recursive way: " + posRecursive);
	}

	private static int searchRegularWay(int[] numbers, int search) {
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
	
	private static int searchRecursiveWay(int[] numbers, int search, int left, int right) {
		int mid = (left + right) / 2;
		int guess = numbers[mid];
		if(left <= right) {			
			if(guess == search) 
				return mid;
			else if(guess > search)
				return searchRecursiveWay(numbers, search, left, mid - 1);
			else
				return searchRecursiveWay(numbers, search, mid + 1, right);
		} 
		throw new NoSuchElementException(search + " not found");
		
	}
}
