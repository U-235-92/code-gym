package aq.gym.brokers.kafka.interceptors;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class MessageConsumer {

	private final Properties properties = new Properties();
	
	public MessageConsumer() {		
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:5050,localhost:5051,localhost:5052");
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "message_consumers");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, MessageConsumerInterceptor.class.getName());
	}
	
	public void consume() {
		try(KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties)) {
			consumer.subscribe(List.of("string-messages"));
			while(true) {				
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(200));
				records.forEach(record -> System.out.println("[Consumer] Record value: " + record.value()));
			}
		}
	}
}
