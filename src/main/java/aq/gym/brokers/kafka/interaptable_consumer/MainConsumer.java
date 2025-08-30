package aq.gym.brokers.kafka.interaptable_consumer;

import java.util.concurrent.CompletableFuture;

public class MainConsumer {

	public static void main(String[] args) {
		InterruptableConsumer consumer = new InterruptableConsumer();
		CompletableFuture<Void> future = consumer.consume();
		sleep();
		consumer.interrupt();
		future.join();
	}
	
	private static void sleep() {		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
