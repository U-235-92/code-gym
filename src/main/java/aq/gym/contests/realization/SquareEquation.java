package aq.gym.contests.realization;

import java.util.Arrays;
import java.util.Scanner;

public class SquareEquation {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double nums[] = Arrays.stream(scanner.nextLine().split("\\s")).mapToDouble(Double::valueOf).toArray();
		double a = nums[0], b = nums[1], c = nums[2];
		if(a != 0) {
			double d = b*b - 4*a*c;
			if(d == 0) {
				double x = -b / (2*a);
				System.out.println(1);
				System.out.printf("%.6f", x);
			} else if(d > 0) {
				double x1 = (-b - Math.sqrt(d)) / (2*a);
				double x2 = (-b + Math.sqrt(d)) / (2*a);
				System.out.println(2);
				if(x1 < x2) 
					System.out.printf("%.6f %.6f", x1, x2);
				else
					System.out.printf("%.6f %.6f", x2, x1);
			} else {
				System.out.println("0");
			}
		}
		scanner.close();
	}
}
