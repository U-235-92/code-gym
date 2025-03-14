package aq.gym.contests.easy;

import java.util.Arrays;
import java.util.Scanner;

public class MiddleElement {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] nums = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::valueOf).toArray();
		Arrays.sort(nums);
		System.out.println(nums[1]);
		scanner.close();
	}

}
