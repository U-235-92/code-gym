package aq.gym.contests.easy.tag_set_time;

import java.util.Scanner;

public class TagTime {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		int n = Integer.valueOf(line);
		int total = total(n) - 1;
		System.out.println(total);
		sc.close();
	}
	
	private static int total(int n) {
		if(n < 1) {
			return 1;
		} 
		return total(n - 1) + total(n - 2);
	}

}
