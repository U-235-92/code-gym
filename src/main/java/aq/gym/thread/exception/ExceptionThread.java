package aq.gym.thread.exception;


public class ExceptionThread {

	public static void main(String[] args) {
		Runnable r = () -> {
			System.out.println(Thread.currentThread().getName() + " before interrupt!");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				System.out.println(Thread.currentThread().getName() + " was interrupted!");
				Thread.currentThread().interrupt();
			}
			System.out.println(Thread.currentThread().getName() + " interrupt status: " + Thread.currentThread().isInterrupted());
			System.out.println(Thread.currentThread().getName() + " after interrupt!");
		};
		Thread thread = new Thread(r);
		thread.start();
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " finished");
	}

}
