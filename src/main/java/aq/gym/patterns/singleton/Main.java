package aq.gym.patterns.singleton;

public class Main {

	public static void main(String[] args) {
		Thread th1 = new Thread(() -> {
			Sun sun = Sun.getInstance();
			System.out.println(sun);
		});
		Thread th2 = new Thread(() -> {
			Sun sun = Sun.getInstance();
			System.out.println(sun);
		});
		th1.start();
		th2.start();
	}

}
