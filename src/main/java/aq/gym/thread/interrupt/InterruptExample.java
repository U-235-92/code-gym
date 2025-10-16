package aq.gym.thread.interrupt;

public class InterruptExample {

	public static void main(String[] args) {
		Runnable r = () -> {
			System.out.println("Start run!");
			try {
				for(int i = 0; i < 5; i++) {
					System.out.printf("Step: %d%n", i + 1);
					Thread.sleep(1000);
				}
			} catch(InterruptedException exc) {
				System.out.println("Thread interrupted");
			} finally {
				System.out.println("Finish run!");
			}
		};
		Thread interruptThread = new Thread(r);
		interruptThread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Main crash");
		}
		interruptThread.interrupt();
	}
}
