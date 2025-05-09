package aq.gym.contests.other;

import java.util.Scanner;

public class BetterGrade {

	public static void main(String[] args) {
		long max = (long) Math.pow(10, 15);
		long min = 0;
		Scanner scanner = new Scanner(System.in);
		long a = scanner.nextLong();
		long b = scanner.nextLong();
		long c = scanner.nextLong();
		if(a < min || b < min || c < min || a > max || b > max || c > max) {
			scanner.close();
			throw new IllegalArgumentException();
		}
		if(a + b + c < 1) {
			scanner.close();
			throw new IllegalArgumentException();
		}
		scanner.close();
		long gradeNumber = getFiveGradeNumberMathWay(a, b, c);
		System.out.println(gradeNumber);
	}

	private static long getFiveGradeNumberMathWay(long a, long b, long c) {
		double sumOfA = a * 2;
		double sumOfB = b * 3;
		double sumOfC = c * 4;
		double totalNumberGrades = a + b + c;
		double gradeNumber = Math.round((sumOfA + sumOfB + sumOfC) / totalNumberGrades); 
		if(gradeNumber >= 4.0) {
			return 0;
		} else {
			long d = (long) Math.ceil((1.5*a + 0.5*b -0.5*c) / 1.5);
			return d;	
		}
	}
	
	@SuppressWarnings("unused")
	private static long getFiveGradeNumberBrootForceWay(long a, long b, long c) {
		double sumOfA = a * 2;
		double sumOfB = b * 3;
		double sumOfC = c * 4;
		double totalNumberGrades = a + b + c;
		double gradeNumber = Math.round((sumOfA + sumOfB + sumOfC) / totalNumberGrades); 
		if(gradeNumber >= 4.0) {
			return 0;
		} else {
			long d = 1;
			while(true) {
				long sumOfD = d * 5;
				totalNumberGrades = a + b + c + d;
				gradeNumber = Math.round((sumOfA + sumOfB + sumOfC + sumOfD) / totalNumberGrades);
				if(gradeNumber >= 4.0) {
					break;
				}
				d++;
			}
			return d;
		}
	}
}
