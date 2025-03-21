package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FibonacciNumber {

	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			long fibonacci = iteration(n);
			System.out.println(fibonacci);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static long recursion(int n) {
		if(n == 1) {
			return 1;
		}
		if(n == 0) {
			return 0;
		}
		return recursion(n - 1) + recursion(n - 2);
	}
	
	@SuppressWarnings("unused")
	private static long memoization(int n) {
		long[] fibonacci = new long[n + 1];
		Arrays.fill(fibonacci, -1);
		fibonacci[0] = 0; fibonacci[1] = 1;
		return memoization(fibonacci, n + 1);
	}
	
	private static long memoization(long[] mem, int n) {
		if(mem[n - 1] == -1) {
			if(n == 0) {
				return mem[0];
			}
			if(n == 1) {
				return mem[1];
			} 
			mem[n - 1] = memoization(mem, n - 1) + memoization(mem, n - 2);
		} 
		return mem[n - 1];
	}
	
	@SuppressWarnings("unused")
	private static long iteration(int n) {
		if(n == 0) {
			return 0;
		} else if(n == 1) {
			return 1;
		} else {
			long previous = 0, current = 1, next = 0;
			for(int i = 2; i <= n; i++) {
				next = previous + current;
				previous = current;
				current = next;
			}
			return next;
		}
	}
	
	@SuppressWarnings("unused")
	private static long array(int n) {
		long[] fibonacci = new long[n + 1];
		fibonacci[0] = 0; fibonacci[1] = 1;
		for(int i = 2; i < fibonacci.length; i++) {
			fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
		}
		return fibonacci[n];
	}
}
