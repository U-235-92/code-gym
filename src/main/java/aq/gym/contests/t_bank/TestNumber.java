package aq.gym.contests.t_bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TestNumber {

//	https://edu.tbank.ru/selection/76378fbd-1998-48fa-944e-eb736d321f11/practice/244/task/5
//	WARNING! Partial solution. It works slow after < 10^9 complexity.
	public static void main(String[] args) {
//		long left = 1L;
//		long right = (long) Math.pow(10, 10);
//		System.out.println(getTestNumber(left, right));
		Scanner sc = new Scanner(System.in);
		long[] data = Arrays.stream(sc.nextLine().split("\\s+")).mapToLong(Long::valueOf).toArray();
		long left = data[0], right = data[1];
		long result = getTestNumber(left, right);
		System.out.println(result);
		sc.close();
	}

	private static int getTestNumber(long left, long right) {
		int testNumber = 0;
		Set<Long> availableNumbers = getSetAvailableNumbers(left, right);
		for(long i = left; i <= right; i = i + 1L) {
			if(availableNumbers.contains(i)) {
				testNumber++;
			}
		}
		return testNumber;
	}
	
	private static Set<Long> getSetAvailableNumbers(long left, long right) {
		Set<Long> availableNumbers = new HashSet<Long>(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L));
		List<List<Long>> table = new ArrayList<>();
		table.add(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L));
		long generated = 1L;
		int prevLineIdx = 0;
		while(generated < right) {
			List<Long> prevLine = table.get(prevLineIdx);
			List<Long> currLine = new ArrayList<Long>();
			for(int i = 1; i < 10; i++) {
				generated = i + (10L * prevLine.get(i - 1));
				if(generated > right) break;
				availableNumbers.add(generated);
				currLine.add(generated);
			}
			if(!currLine.isEmpty()) {				
				table.add(currLine);
				prevLineIdx++;
			}
		}
		return availableNumbers;
	}
}
