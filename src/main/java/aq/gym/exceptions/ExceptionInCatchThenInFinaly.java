package aq.gym.exceptions;

public class ExceptionInCatchThenInFinaly {

	public static void main(String[] args) {
		int num = getNumber("forty_two");
		System.out.println(num);
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
}
