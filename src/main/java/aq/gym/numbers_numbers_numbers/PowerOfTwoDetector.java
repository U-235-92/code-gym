package aq.gym.numbers_numbers_numbers;

public class PowerOfTwoDetector {

	public static void main(String[] args) {
		System.out.println(isPower(8));
	}

	public static boolean isPower(int num) {
		return (num & (num - 1)) == 0;
	}
}
