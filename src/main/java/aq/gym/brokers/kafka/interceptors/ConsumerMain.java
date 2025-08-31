package aq.gym.brokers.kafka.interceptors;

public class ConsumerMain {

	public static void main(String[] args) {
		MessageConsumer messageConsumer = new MessageConsumer();
		messageConsumer.consume();
	}
}
