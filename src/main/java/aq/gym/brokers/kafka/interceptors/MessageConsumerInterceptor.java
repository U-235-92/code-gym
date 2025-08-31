package aq.gym.brokers.kafka.interceptors;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

public class MessageConsumerInterceptor implements ConsumerInterceptor<String, String> {

	@Override
	public void configure(Map<String, ?> configs) {}

	@Override
	public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
		records.forEach(record -> record.headers()
				.headers("uuid")
				.forEach(header -> System.out.println("[Interceptor] Header " + header.key() + ": " + new String(header.value()))));
		return records;
	}

	@Override
	public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {}

	@Override
	public void close() {}
}
