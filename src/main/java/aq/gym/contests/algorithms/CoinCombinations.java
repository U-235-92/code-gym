package aq.gym.contests.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CoinCombinations {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		Set<List<Integer>> combinations = new TreeSet<>(Comparator.comparing(List::size));
		int[] coins = {1, 5, 10};
		recursiveFindCombination(coins, combinations, new ArrayList<Integer>(), n);
		System.out.println(combinations.size());
		for(List<Integer> combination : combinations) {
			System.out.print(combination.size() + " ");
			String line = combination.stream().map(String::valueOf).collect(Collectors.joining(" "));
			System.out.println(line);
		}
	}

	private static void recursiveFindCombination(int[] coins, Set<List<Integer>> combinations, List<Integer> combination, int money) {
		if(money == 0) {
			combinations.add(new ArrayList<>(combination));
			return;
		}
		for(int i = 0; i < coins.length; i++) {
			if(coins[i] <= money) {				
				combination.add(coins[i]);
				recursiveFindCombination(coins, combinations, combination, money - coins[i]);
				combination.remove(combination.size() - 1);
			}
		}
	}
}
