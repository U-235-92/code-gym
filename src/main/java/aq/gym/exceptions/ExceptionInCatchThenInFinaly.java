package aq.gym.exceptions;

import java.io.IOException;

public class ExceptionInCatchThenInFinaly {

	public static void main(String[] args) {
		int num = getNumber("42");
		System.out.println(num);
		System.out.println("Before exception");
		try {
			throwCheckUncheckManual();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("After exception");
	}

	private static int getNumber(String number) {
		int num;
		try {
			num = Integer.parseInt(number);
		} catch(NumberFormatException e) {
			throw e;
		} finally {
//			return 42; // If it was thrown [NumberFormatException] OR method wasn't thrown any exception the method will return 42 
//			throw new NullPointerException("Uh oh..."); // If was thrown [NumberFormatException] this instruction will override [NumberFormatException] on [NullPointerException] 
			
		}
		return num; // If uncomment code above this line will be unreachable and this code won't compile
	}
	
	protected static void throwCheckUncheckManual() throws IOException {
		throw new IOException(); // You have to add throws declaration if you throw checked exception
//		throw new RuntimeException(); // You don't have to add throws declaration if you throw unchecked exception
	}
	
	protected static void doUnchecked() {
		throw new RuntimeException();
	}
}
