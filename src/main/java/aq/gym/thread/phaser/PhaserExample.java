package aq.gym.thread.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample {

	public static void main(String[] args) {
		Phaser phaser = new Phaser(1);
		Runnable  r1 = () -> {
			phaser.register();
			try {				
				System.out.printf("Start thread %s%n", Thread.currentThread().getName());
				phaser.arriveAndAwaitAdvance();
				TimeUnit.SECONDS.sleep(1);
				System.out.printf("Start thread %s%n", Thread.currentThread().getName());
				phaser.arriveAndAwaitAdvance();
				TimeUnit.SECONDS.sleep(1);
				System.out.printf("Start thread %s%n", Thread.currentThread().getName());
				phaser.arriveAndDeregister();
				TimeUnit.SECONDS.sleep(1);
			} catch(InterruptedException  exc) {
				exc.printStackTrace();
			}
		};
		Runnable  r2 = () -> {
			phaser.register();
			try {				
				System.out.printf("Start thread %s%n", Thread.currentThread().getName());
				phaser.arriveAndAwaitAdvance();
				TimeUnit.SECONDS.sleep(3);
				System.out.printf("Start thread %s%n", Thread.currentThread().getName());
				phaser.arriveAndAwaitAdvance();
				TimeUnit.SECONDS.sleep(1);
				System.out.printf("Start thread %s%n", Thread.currentThread().getName());
				phaser.arriveAndDeregister();
				TimeUnit.SECONDS.sleep(1);
			} catch(InterruptedException  exc) {
				exc.printStackTrace();
			}
		};
		Runnable  r3 = () -> {
			phaser.register();
			try {				
				System.out.printf("Start thread %s%n", Thread.currentThread().getName());
				phaser.arriveAndAwaitAdvance();
				TimeUnit.SECONDS.sleep(1);
				System.out.printf("Start thread %s%n", Thread.currentThread().getName());
				phaser.arriveAndAwaitAdvance();
				TimeUnit.SECONDS.sleep(1);
				System.out.printf("Start thread %s%n", Thread.currentThread().getName());
				phaser.arriveAndDeregister();
				TimeUnit.SECONDS.sleep(1);
			} catch(InterruptedException  exc) {
				exc.printStackTrace();
			}
		};
		Thread th1 = new Thread(r1, "T1");
		Thread th2 = new Thread(r2, "T2");
		Thread th3 = new Thread(r3, "T3");
		th1.start();
		th2.start();
		th3.start();
		int phase = phaser.getPhase();
		phaser.arriveAndAwaitAdvance();
		System.out.printf("End of phase %d%n", phase);
		
		phase = phaser.getPhase();
		phaser.arriveAndAwaitAdvance();
		System.out.printf("End of phase %d%n", phase);
		
		phase = phaser.getPhase();
		phaser.arriveAndAwaitAdvance();
		System.out.printf("End of phase %d%n", phase);
		
		phase = phaser.getPhase();
		phaser.arriveAndAwaitAdvance();
		System.out.printf("End of phase %d%n", phase);
		
		phase = phaser.getPhase();
		System.out.printf("End of phase %d%n", phase);
		phaser.arriveAndDeregister();
	}
}
