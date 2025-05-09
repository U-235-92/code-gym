package aq.gym.contests.other;

import java.util.Scanner;

public class PrisionerCastle {

	private static final String YES = "YES";
	private static final String NO = "NO";
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a = Integer.valueOf(scanner.nextLine());
		int b = Integer.valueOf(scanner.nextLine());
		int c = Integer.valueOf(scanner.nextLine());
		int d = Integer.valueOf(scanner.nextLine());
		int e = Integer.valueOf(scanner.nextLine());
		scanner.close();
		if(e >= a && d >= b) {
			System.out.println(YES);
		} else if(e >= b && d >= a) {
			System.out.println(YES);
		} else if(e >= a && d >= c) {
			System.out.println(YES);
		} else if(e >= c && d >= a) {
			System.out.println(YES);
		} else if(e >= b && d >= c) {
			System.out.println(YES);
		} else if(e >= c && d >= b) {
			System.out.println(YES);
		} else if(e >= c && d >= a) {
			System.out.println(YES);
		} else if(e >= a && d >= c) {
			System.out.println(YES);
		} else {
			System.out.println(NO);
		}
	}
}

//4
//2
//1
//2
//1
