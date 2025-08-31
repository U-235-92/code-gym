package aq.gym.brokers.kafka.interceptors;

public class ProducerMain {

	public static void main(String[] args) {
		MessageProducer messageProducer = new MessageProducer();
		messageProducer.produce();
	}
}
