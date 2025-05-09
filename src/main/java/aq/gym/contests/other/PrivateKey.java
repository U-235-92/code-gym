package aq.gym.contests.other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PrivateKey {

	public static void main(String[] args) {
		mathWay();
	}
	
	private static void mathWay() {
		Scanner scanner = new Scanner(System.in);
		long[] nums = Arrays.stream(scanner.nextLine().split("\\s")).mapToLong(Long::valueOf).toArray();
		scanner.close();
		long gcd = nums[0];
		long lcm = nums[1];
		int keyPairNumber = 0;
		if(lcm % gcd == 0) {
			if(lcm / gcd == 1) {
				keyPairNumber = 1;
				System.out.println(keyPairNumber);
			} else {
				Set<Long> factors = new HashSet<Long>();
				long multiplicator = lcm / gcd;
				for(long factor = 2; factor <= multiplicator / factor; factor++) {
					while(multiplicator % factor == 0) {
						multiplicator = multiplicator / factor;
						factors.add(factor);
					}
				}
				if(multiplicator != 1) {					
					factors.add(multiplicator);
				}
				keyPairNumber = (int) Math.pow(2, factors.size());
				System.out.println(keyPairNumber);
			}
		} else {
			System.out.println(keyPairNumber);
		}
	}
	
	@SuppressWarnings("unused")
	private static void brootForceWay() {
		Scanner scanner = new Scanner(System.in);
		long[] nums = Arrays.stream(scanner.nextLine().split("\\s")).mapToLong(Long::valueOf).toArray();
		scanner.close();
		long gcd = nums[0];
		long lcm = nums[1];
		long gcd_lcm = gcd * lcm;
		Set<long[]> keys = new HashSet<>();
		for(long a = 1; a <= gcd_lcm; a++) {
			for(long b = 1; b <= gcd_lcm; b++) {
				if(a * b == gcd_lcm) {
					keys.add(new long[] {a, b});
					keys.add(new long[] {b, a});
				}
				if(a * b > gcd_lcm) {
					break;
				}
			}
		}
		int privateKeyCount = 0;
		for(long[] key : keys) {
			long a = key[0];
			long b = key[1];
			long calculateGCD = gcd(a, b);
			if(isValidGCD(gcd, calculateGCD)) {
				privateKeyCount++;
			} 
		}
		System.out.println(privateKeyCount);
	}
	
	private static long gcd(long a, long b) {
		long divisor = 0;
		if(a > b) {
			if(a % b == 0) {
				divisor = b;
			} else {
				while(a % b != 0) {
					divisor = a % b;
					a = b;
					b = divisor;
				}
			}
		} else {
			if(b % a == 0) {
				divisor = a;
			} else {
				while(b % a != 0) {
					divisor = b % a;
					b = a;
					a = divisor;
				}
			}
		}
		return divisor;
	}
	
	@SuppressWarnings("unused")
	private static long lcm(long a, long b, long gcd) {
		long calculateLCM = (a * b) / gcd;
		return calculateLCM;
	}
	
	private static boolean isValidGCD(long chekGCD, long calculateGCD) {
		return calculateGCD == chekGCD;
	}
	
	@SuppressWarnings("unused")
	private static boolean isValidLCM(long checkLCM, long calculateLCM) {
		return calculateLCM == checkLCM;
	}
}
