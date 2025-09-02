package aq.gym.brokers.kafka.compact_topic;

public class MainConsumer {

	public static void main(String[] args) {
		Consumer consumer = new Consumer();
		consumer.consume();
	}
}
