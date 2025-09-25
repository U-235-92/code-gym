package aq.gym.thread.dead_lock;

public class DeadLockTestDrive {

	public static void main(String[] args) throws InterruptedException {
		Account alice = new Account("Alice", 500);
		Account bob = new Account("Bob", 800);
		Thread aliceThread = new Thread(() -> alice.transfer(bob, 2600));
		Thread bobThread = new Thread(() -> bob.transfer(alice, 6200));
		aliceThread.start();
		Thread.sleep(4000);
		bobThread.start();
	}

}
