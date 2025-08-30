package aq.gym.brokers.kafka.interaptable_consumer;

public class MainProducer {
	
	public static void main(String[] args) throws InterruptedException {
		MadProducer producer = new MadProducer();
		producer.produce();
	}
}
