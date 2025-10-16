package aq.gym.thread.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample {

	public static void main(String[] args) {
		Phaser phaser = new Phaser(1);
		new Thread(new PhaserExample.PhaseRunner(phaser, "Runner #1")).start();
		new Thread(new PhaserExample.PhaseRunner(phaser, "Runner #2")).start();
		
		int phase = phaser.getPhase();
		phaser.arriveAndAwaitAdvance();
		System.out.printf("End of phase %d%n", phase);
		
		phase = phaser.getPhase();
		phaser.arriveAndAwaitAdvance();
		System.out.printf("End of phase %d%n", phase);
		
		phase = phaser.getPhase();
		phaser.arriveAndAwaitAdvance();
		System.out.printf("End of phase %d%n", phase);
		
		phaser.arriveAndDeregister();
		System.out.println("All the phases complete!");
	}
	
	private static class PhaseRunner implements Runnable {
		
		private Phaser phaser;
		private String name;
		
		PhaseRunner(Phaser phaser, String name) {
			this.phaser = phaser;
			this.name = name;
			phaser.register();
		}
		
		@Override
		public void run() {
			try {				
				System.out.printf("%s started phase: %d%n", name, phaser.getPhase());
				phaser.arriveAndAwaitAdvance();
				TimeUnit.MILLISECONDS.sleep(250);
				System.out.printf("%s started phase: %d%n", name, phaser.getPhase());
				phaser.arriveAndAwaitAdvance();
				TimeUnit.MILLISECONDS.sleep(250);
				System.out.printf("%s started phase: %d%n", name, phaser.getPhase());
				phaser.arriveAndDeregister();
				TimeUnit.MILLISECONDS.sleep(250);
			} catch(InterruptedException  exc) {
				exc.printStackTrace();
			}
		}
	}
}
