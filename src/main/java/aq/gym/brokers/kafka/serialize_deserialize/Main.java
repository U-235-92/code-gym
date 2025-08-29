package aq.gym.brokers.kafka.serialize_deserialize;

public class Main {
	
	public static void main(String[] args) {
		Alert warn = new Alert("0", 1, "WARN", "Stage 0 was stopped");
		Alert info = new Alert("1", 2, "INFO", "Stage 1 was completed");
		AlertProducer alertProducer = new AlertProducer();
		alertProducer.produce(warn);
		alertProducer.produce(info);
	}
}
