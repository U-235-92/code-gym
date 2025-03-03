package aq.gym.contests.easy.greate_neighbour;

import java.util.Arrays;
import java.util.Scanner;

public class GreatNeighbour {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		int count = 0;
		if(nums.length >= 3) {
			for(int i = 1; i < nums.length - 1; i++) {
				int prev = nums[i - 1];
				int crnt = nums[i];
				int next = nums[i + 1];
				if(crnt > prev && crnt > next) {
					count++;
				}
			}
		}
		System.out.println(count);
		scanner.close();
	}

}
