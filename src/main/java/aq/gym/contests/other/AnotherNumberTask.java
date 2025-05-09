package aq.gym.contests.other;

import java.util.Arrays;
import java.util.Scanner;

public class AnotherNumberTask {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long[] nums = Arrays.stream(scanner.nextLine().split("\\s")).mapToLong(Long::valueOf).toArray();
		long a = nums[0], b = nums[1];
		long greatestDiv = euclideCommonDivisor(a, b);
		long smallestMul = leastCommonMultiple(a, b);
		System.out.println(greatestDiv + " " + smallestMul);
		scanner.close();
	}
	
//	761457 614573 
	private static long leastCommonMultiple(long a, long b) {
		long multiple = Math.abs((a * b) / euclideCommonDivisor(a, b));
		return multiple;
	}
	
	private static long euclideCommonDivisor(long a, long b) {
		long divisor = 0;
		if(a > b) {
			if(a % b == 0) {
				divisor = b;
			} else {				
				while((a % b) != 0) {
					divisor = a % b;
					a = b;
					b = divisor;
				}
			}
		} else {
			if(b % a == 0) {
				divisor = a;
			} else {				
				while((b % a) != 0) {
					divisor = b % a;
					b = a;
					a = divisor;
				}
			}
		}
		return divisor;
	}
	
	@SuppressWarnings("unused")
	private static class BrootForceWay {
		
		private static long greatestCommonDivisor(long a, long b) {
			int divisor = 0;
			if(a > b) {
				for(int i = 1; i <= b; i++) {
					if(a % i == 0 && b % i == 0) {
						divisor = i;
					}
				}
			} else {
				for(int i = 1; i <= a; i++) {
					if(a % i == 0 && b % i == 0) {
						divisor = i;
					}
				}
			}
			return divisor;
		}
		
		private static long smallestCommonMultiple(long a, long b) {
			long commonMultiple = 0, tmpMultiplicator = 1;
			while(true) {
				long m1 = a * tmpMultiplicator;
				long m2 = b * tmpMultiplicator;
				if(m1 > m2) {
					if(m1 % a == 0 && m1 % b == 0) {
						commonMultiple = m1;
						return commonMultiple;
					}
				} else {
					if(m2 % a == 0 && m2 % b == 0) {
						commonMultiple = m2;
						return commonMultiple;
					}
				}
				tmpMultiplicator++;
			}
		}
	}
}
