package aq.gym.brokers.kafka.stream;

public class MainProducer {

	public static void main(String[] args) {
		SinkProducer producer = new SinkProducer();
//		pushBatchMessages(producer);
		pushSingleMessage(8L, "bla-bla", producer);
	}
	
	@SuppressWarnings("unused")
	private static void pushSingleMessage(long key, String value, SinkProducer producer) {
		producer.pushMessage(key, value);
		System.out.println(String.format("%d: %s", key, value));
	}
	
	@SuppressWarnings("unused")
	private static void pushBatchMessages(SinkProducer producer) {
		producer.pushMessages();
	}
}
