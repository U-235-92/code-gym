package aq.gym.brokers.kafka.stream;

public interface Topics {

	String SOURCE_TOPIC = "source-messages";
	String SINK_TOPIC = "sink-messages";
	String BOOTSTRAP_SERVERS = "localhost:5050,localhost:5051,localhost:5052";
}
