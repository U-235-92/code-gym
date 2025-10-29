package aq.gym.contests.t_bank;

import java.util.Scanner;

public class FairlyCake {

//	https://edu.tbank.ru/selection/76378fbd-1998-48fa-944e-eb736d321f11/practice/244/task/2
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] accumulator = {0};
//		int n = sc.nextInt();
		int n = 2 * (int) Math.pow(10, 9);
		getCutCountByRecursion(n, accumulator);
		System.out.println(accumulator[0]);
		sc.close();
	}

	private static void getCutCountByRecursion(int n, int[] accumulator) {
		if(n == 1) return;
		int left = n / 2;
		int right = n - left;
		accumulator[0]++;
		getCutCountByRecursion(Math.max(left, right), accumulator);
	}
}
