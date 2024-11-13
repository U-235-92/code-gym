package aq.gym.collections.practise;

import java.util.ArrayList;
import java.util.List;

public class NumberPermutator {

	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<Integer>(List.of(1, 5, 2, 8, 0, 4, 3, 5, 6, 5, 1, 9, 5, 8, 7));
		int delimiter = 5;
		System.out.println("Before: " + numbers);
		permutate(delimiter, numbers);
		System.out.println("After:  " + numbers);
	}

	//permutate numbers with no sort, no change collection and using other objects
	//main target: 
	//transfer all the numbers which > delimiter number on the right 
	//and numbers which < delimiter number on the left
	private static void permutate(int delimiter, List<Integer> numbers) {
		int left = 0, right = numbers.size() - 1;
		while (left <= right) {
			while (numbers.get(left) < delimiter)
				left++;
			while (numbers.get(right) > delimiter)
				right--;
			if (left <= right) {
				int leftNum = numbers.get(left);
				int rightNum = numbers.get(right);
				if(leftNum == delimiter && rightNum == delimiter) {
					for(int i = left; i < (numbers.size() / 2) - 1; i++) {
						int next = numbers.get(i + 1);
						if(next != delimiter) {
							int tmp = next;
							numbers.set(i + 1, leftNum);
							numbers.set(i, tmp);
						} 
					}
					for(int i = right; i > (numbers.size() / 2) - 1; i--) {
						int next = numbers.get(i - 1);
						if(next != delimiter) {
							int tmp = next;
							numbers.set(i - 1, leftNum);
							numbers.set(i, tmp);
						} 
					}
				} else if(leftNum == delimiter) {
					numbers.set(left, rightNum);
					numbers.set(right, leftNum);
					for(int i = right; i >= (numbers.size() / 2) - 1; i--) {
						int next = numbers.get(i - 1);
						if(next != delimiter) {
							int tmp = next;
							numbers.set(i - 1, leftNum);
							numbers.set(i, tmp);
						} 
					}
				} else if(rightNum == delimiter) {
					numbers.set(left, rightNum);
					numbers.set(right, leftNum);
					for(int i = left; i < (numbers.size() / 2) - 1; i++) {
						int next = numbers.get(i + 1);
						if(next != delimiter) {
							int tmp = next;
							numbers.set(i + 1, leftNum);
							numbers.set(i, tmp);
						} 
					}
				} else {
					numbers.set(left, rightNum);
					numbers.set(right, leftNum);
				}
				left++;
				right--;
			}
		}
	}
}
