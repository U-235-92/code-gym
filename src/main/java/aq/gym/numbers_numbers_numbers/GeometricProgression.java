package aq.gym.numbers_numbers_numbers;

public class GeometricProgression {

	public static void main(String[] args) {
		int p = 2;
		int q = 2;
		for(p = 2; p <= 1024; p *= q) {
			System.out.print(p + " ");
		}
	}

}
