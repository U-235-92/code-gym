package aq.gym.numbers_numbers_numbers;

public class Binary {

	public static void main(String[] args) {
		int number = 19;
		String binary = convertToBinary(number);
		System.out.println(number + " in binary format: " + binary);
	}

	private static String convertToBinary(int number) {
		int power = 1;
		String result = "";
		while(power <= number / 2) {
			power *= 2;
		}
		while(power > 0) {
			if(number - power >= 0) {
				result += "1";
				number -= power;
			} else {
				result += "0";
			}
			power /= 2;
		}
		return result;
	}
}
