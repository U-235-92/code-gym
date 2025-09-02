package aq.gym.brokers.kafka.compact_topic;

public class MainProducer {

	public static void main(String[] args) {
		Producer producer = new Producer();
		producer.produce(1, "NEW");
		producer.produce(2, "RUN");
		producer.produce(1, "RUN");
		producer.produce(2, "FINISH");
		producer.produce(3, "FINISH");
	}
}
