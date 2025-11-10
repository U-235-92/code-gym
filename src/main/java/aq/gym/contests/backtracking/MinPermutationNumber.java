package aq.gym.contests.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MinPermutationNumber {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		System.out.println(permutation(number));
		scanner.close();
	}

	private static int permutation(int number) {
		PriorityQueue<Integer> permutations = new PriorityQueue<>();
		int[] numbers = Arrays.stream((number + "").split("")).mapToInt(Integer::valueOf).toArray();
		boolean[] usedNumbers = new boolean[numbers.length];
		backtrack(4, 0, usedNumbers, numbers, permutations, new ArrayList<Integer>());
		return permutations.poll();
	}
	
	private static void backtrack(final int limit, int start, boolean[] usedNumbers, int[] numbers, PriorityQueue<Integer> permutations, List<Integer> combination) {
		if(combination.size() == limit) {
			if(combination.get(0) != 0) {
				int number = getPermutationCombinationNumber(combination);
				permutations.offer(number);
			}
		}
		for(int i = start; i < numbers.length; i++) {
			if(usedNumbers[i]) {
				continue;
			}
			combination.add(numbers[i]);
			usedNumbers[i] = true;
			backtrack(limit, start, usedNumbers, numbers, permutations, combination);
			combination.remove(combination.size() - 1);
			usedNumbers[i] = false;
		}
	}
	
	private static int getPermutationCombinationNumber(List<Integer> combination) {
		return (combination.get(0) * 1000) + (combination.get(1) * 100) + (combination.get(2) * 10) + combination.get(3);
	}
}
