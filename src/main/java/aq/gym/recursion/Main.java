package aq.gym.recursion;

public class Main {

	public static void main(String[] args) {
		System.out.printf("Reverse of %s: " + reverseString("ABCDEF") + "%n", "ABCDEF");
		System.out.printf("Fctorial of %d: " + factorial(4) + "%n", 4); 
		System.out.printf("Fibonacci to limit %d: " + fibonacci(4), 4); 
	}
	
	private static String reverseString(String string) {
		return recursiveReverseString(0, string);
	}
	
	private static String recursiveReverseString(int index, String string) {
		String reverse = "";
		if(index != string.length() - 1) {
			reverse = recursiveReverseString(index + 1, string);
		} 
		reverse = reverse + string.charAt(index);
		return reverse;
	}

	private static int factorial(int number) {
		int factorial = 1;
		if(number != 1) {
			factorial = factorial(number - 1);
		}
		return factorial *= number; 
	}
	
	private static int fibonacci(int limit) {
		if(limit <= 1) {
			return limit;
		}
		return fibonacci(limit - 2) + fibonacci(limit - 1);
	}
}
