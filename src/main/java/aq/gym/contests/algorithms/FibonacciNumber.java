package aq.gym.contests.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

import org.hibernate.id.IdentifierGeneratorHelper.BigIntegerHolder;
import org.hibernate.type.descriptor.java.BigIntegerJavaType;

import com.google.common.math.BigIntegerMath;

public class FibonacciNumber {

	public static void main(String[] args) {
		getLastNumberOfPartitialSumOfFibonacciNumbers();
	}
	
	@SuppressWarnings("unused")
	private static void getLastNumberOfPartitialSumOfFibonacciNumbers() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			long mod = 10;
			long[] data = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			long n = data[0] + 2 - 1; 
			long m = data[1] + 2;
			BigInteger modN = getMod(n);
			BigInteger modM = getMod(m);
			System.out.println(modM.subtract(modN).mod(BigInteger.valueOf(mod)));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static BigInteger getMod(long n) {
		long m = 10;
        long period = getPisanoPeriod(m);
        BigInteger mod = getModFibonacciNumber(n, m, period).subtract(BigInteger.valueOf(1)); // Second part of function S(n)
        return mod.mod(BigInteger.valueOf(m));
	}
	
	private static BigInteger getSumFibonacciNumbers(long n) {
		BigInteger sum = BigInteger.valueOf(0);
		sum = bigFibonacci(n);
		return sum;
	}

	@SuppressWarnings("unused")
	private static void getLastNumberOfSumFibonacciNumbers() {
//		S(n) = F(n + 2) - 1
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            long n = Long.parseLong(br.readLine()) + 2; // First part of function S(n)
            long m = 10;
            long period = getPisanoPeriod(m);
            BigInteger mod = getModFibonacciNumber(n, m, period).subtract(BigInteger.valueOf(1)); // Second part of function S(n)
            System.out.println(mod.mod(BigInteger.valueOf(m)).toString()); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@SuppressWarnings("unused")
	private static void getModFibonacciNumber() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			long[] data = Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::valueOf).toArray();
			long n = data[0]; 
			long m = data[1];
			long period = getPisanoPeriod(m);
			BigInteger mod = getModFibonacciNumber(n, m, period);
			System.out.println(mod.toString());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static long getPisanoPeriod(long m) {
		long current = 0, next = 1, period = 0;
		while(true) {
			long oldNext = next;
			next = (current + next) % m;
			current = oldNext;
			period = period + 1;
			if(current == 0 && next == 1) {
				return period;
			}
		}
	}
	
	private static BigInteger getModFibonacciNumber(long n, long m, long period) {
        long fibonacciPeriodN = n % period;
        BigInteger fibonacci = bigFibonacci(fibonacciPeriodN);
        return fibonacci.mod(BigInteger.valueOf(m));
    }
	
	private static BigInteger bigFibonacci(long n) {
        if (n == 0) {
            return BigInteger.valueOf(0);
        } else if (n == 1) {
            return BigInteger.valueOf(1);
        } else {
            BigInteger prev = BigInteger.valueOf(0);
            BigInteger curr = BigInteger.valueOf(1);
            BigInteger next = BigInteger.valueOf(0);
            for (long i = 2; i <= n; i++) {
                next = prev.add(curr);
                prev = curr;
                curr = next;
            }
            return next;
        }
    }
	
	@SuppressWarnings("unused")
	private static void getLastNumberOfFibonacciNumber() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			long lastNumber = lastNumber(n);
			System.out.println(lastNumber);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static long lastNumber(int n) {
		if(n == 0) {
			return 0;
		} else if(n == 1) {
			return 1;
		} else {
			long previous = 0, current = 1, next = 0;
			if(n <= 6) {
				for(int i = 2; i <= n; i++) {
					next = previous + current;
					previous = current;
					current = next;
				}
			} else {				
				for(int i = 2; i <= n; i++) {
					next = (previous + current) % 10;
					previous = current % 10;
					current = next;
				}
			}
			return next;
		}
	}
	
	@SuppressWarnings("unused")
	private static void getFibonacciNumber() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.valueOf(br.readLine());
			long fibonacci = iterationFibonacciNumber(n);
			System.out.println(fibonacci);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static long recursionFibonacciNumber(int n) {
		if(n == 1) {
			return 1;
		}
		if(n == 0) {
			return 0;
		}
		return recursionFibonacciNumber(n - 1) + recursionFibonacciNumber(n - 2);
	}
	
	@SuppressWarnings("unused")
	private static long memoizationFibonacciNumber(int n) {
		long[] fibonacci = new long[n + 1];
		Arrays.fill(fibonacci, -1);
		fibonacci[0] = 0; fibonacci[1] = 1;
		return memoizationFibonacciNumber(fibonacci, n + 1);
	}
	
	private static long memoizationFibonacciNumber(long[] mem, int n) {
		if(mem[n - 1] == -1) {
			if(n == 0) {
				return mem[0];
			}
			if(n == 1) {
				return mem[1];
			} 
			mem[n - 1] = memoizationFibonacciNumber(mem, n - 1) + memoizationFibonacciNumber(mem, n - 2);
		} 
		return mem[n - 1];
	}
	
	@SuppressWarnings("unused")
	private static long iterationFibonacciNumber(long n) {
		if(n == 0) {
			return 0;
		} else if(n == 1) {
			return 1;
		} else {
			long previous = 0, current = 1, next = 0;
			for(long i = 2; i <= n; i++) {
				next = previous + current;
				previous = current;
				current = next;
			}
			return next;
		}
	}
	
	@SuppressWarnings("unused")
	private static long arrayFibonacciNumber(int n) {
		long[] fibonacci = new long[n + 1];
		fibonacci[0] = 0; fibonacci[1] = 1;
		for(int i = 2; i < fibonacci.length; i++) {
			fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
		}
		return fibonacci[n];
	}
}
