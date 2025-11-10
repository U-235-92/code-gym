package aq.gym.contests.game_theory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ValidPlayerNumbersPermutation {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		List<int[]> arrays = new ArrayList<>();
		while(t-- > 0) {
			@SuppressWarnings("unused")
			int n = Integer.valueOf(scanner.nextLine());
			int[] a = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
			arrays.add(a);
		}
		scanner.close();
		for(int[] a : arrays) {
			int n = a.length;
			Arrays.sort(a);
			long sum = 0L;
			for(int i = 0; i < n; i++) {
				int delta = (i + 1) - a[i];
				sum = sum + delta;
			}
			if (sum % 2 == 1) {
                System.out.println("First");
            } else {
                System.out.println("Second");
            }
		}
    }
}
