package aq.gym.contests.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class OpenCalculator {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String calcNumsLine = scanner.nextLine();
		int[] calcNums = Arrays.stream(calcNumsLine.split("\\s")).mapToInt(Integer::valueOf).toArray();
		int x = calcNums[0];
		int y = calcNums[1];
		int z = calcNums[2];
		String enteredNumsLine = scanner.nextLine();
		Set<Integer> eneredNums = Arrays.stream(enteredNumsLine.split("")).map(Integer::valueOf).collect(Collectors.toCollection(HashSet::new));
		System.out.println(numberAdditionButtons(eneredNums, x, y, z));
		scanner.close();
	}
	
	private static int numberAdditionButtons(Set<Integer> nums, int x, int y, int z) {
		int numberAdditionButtons = 0;
		for(int num : nums) {
			if(num == x || num == y || num == z) {}
			else {
				numberAdditionButtons++;
			}
		}
		return numberAdditionButtons;
	}
}
