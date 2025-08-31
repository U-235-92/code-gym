package aq.gym.brokers.kafka.interceptors;

import java.util.Map;
import java.util.UUID;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class MessageProducerInterceptor implements ProducerInterceptor<String, String> {

	@Override
	public void configure(Map<String, ?> configs) {}

	@Override
	public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
		String key = "uuid";
		byte[] value = UUID.randomUUID().toString().getBytes();
		record.headers().add(key, value);
		return record;
	}

	@Override
	public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
		if(exception != null)
			System.err.println(exception.getMessage());
	}

	@Override
	public void close() {}
}
