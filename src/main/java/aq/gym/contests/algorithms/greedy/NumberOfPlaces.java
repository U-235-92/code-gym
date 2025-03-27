package aq.gym.contests.algorithms.greedy;

import java.util.Scanner;

public class NumberOfPlaces {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long n = scanner.nextLong();
		scanner.close();
		long kNext = 1, kPrev = 0, limit = 1;;
		while(kNext <= n) {
			kNext = limit * (limit + 1) / 2;
			if(kNext > n) {
				break;
			} else {
				kPrev = limit;
				limit++;
			}
		}
		System.out.println(kPrev);
	}

}
