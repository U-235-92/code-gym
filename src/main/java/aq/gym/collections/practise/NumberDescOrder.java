package aq.gym.collections.practise;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class NumberDescOrder {

	public static void main(String[] args) {
		printNumberHasNumbersDescOrder(getNumberNumbersStack(54321));
	}

	private static Deque<Integer> getNumberNumbersStack(int number) {		
		Deque<Integer> numbers = new LinkedList<>();
		while(number > 0) {
			int n = number % 10;
			number = number / 10;
			numbers.push(n);
		}
		return numbers;
	}
	
	private static void printNumberHasNumbersDescOrder(Deque<Integer> numbers) {
		if(isNumbersInDescOrderCheckByDeque(numbers))
			numbers.forEach(System.out::print);
		else
			System.out.println("The numbers order isn't desc!");
	}
	
	private static boolean isNumbersInDescOrderCheckByDeque(Deque<Integer> numbers) {
		Deque<Integer> copy = new LinkedList<>(numbers);
		int first = copy.pop();
		while(copy.size() != 0) {
			int popped = copy.pop();
			if(first < popped) 
				return false;
		}
		return true;
	}
	
	@SuppressWarnings("unused")
	private static boolean isNumberInDescOrderCheckByArrayList(Deque<Integer> numbers) {
		List<Integer> copy = new ArrayList<>(numbers);
		for(int i = copy.size() - 1; i >= 1; i--) {
			int current = copy.get(i);
			int next = copy.get(i - 1);
			if(current > next)
				return false;
		}
		return true;
	}
}
