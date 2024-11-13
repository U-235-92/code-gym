package aq.gym.thread.library;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		Library library = new Library();
		Thread bobThread = new Thread(() -> {
			sleep();
			Person bob = new Person("Bob");
			bob.takeBook("A", library);
			bob.takeBook("C", library);
		});
		Thread aliceThread = new Thread(() -> {
			sleep();
			Person alice = new Person("Alice");
			alice.takeBook("A", library);
			alice.takeBook("D", library);
		});
		bobThread.start();
		aliceThread.start();
		try {
			aliceThread.join();
			bobThread.join();
			System.out.println("End");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void sleep() {
		int sleep = generate();
		try {
			TimeUnit.MILLISECONDS.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static int generate() {
		int min = 1;
		int max = 5;
		return (min + (int) (Math.random() * (max - min) + 1)) * 100;
	}

}
