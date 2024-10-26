package aq.gym.thread.managed_thread;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		ManagedRun mr = new ManagedRun();
		Thread th1 = new Thread(mr, "A");
		Thread th2 = new Thread(mr, "B");
		th1.start();
		th2.start();
		Thread.sleep(1000);
		mr.pause();
		Thread.sleep(5000);
		mr.resume();
		Thread.sleep(1000);
		mr.stop();
	}

}
