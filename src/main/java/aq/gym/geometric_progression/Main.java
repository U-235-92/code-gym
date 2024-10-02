package aq.gym.geometric_progression;

public class Main {

	public static void main(String[] args) {
		int p = 2;
		int q = 2;
		for(p = 2; p <= 1024; p *= q) {
			System.out.print(p + " ");
		}
	}

}
