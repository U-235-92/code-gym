package aq.gym.contests.easy;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AscNumberOrderDetector {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		List<Integer> ints = Arrays.stream(line.split(" ")).map(Integer::valueOf).toList();
		for(int i = 0; i < ints.size() - 1; i++) {
			int current = ints.get(i);
			int next = ints.get(i + 1);
			if(next <= current) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
		sc.close();
	}

}
