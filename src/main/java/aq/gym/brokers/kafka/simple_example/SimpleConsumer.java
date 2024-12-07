package aq.gym.brokers.kafka.simple_example;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class SimpleConsumer {

	private boolean isContinueToConsume = true;
	
	private void consume(Properties properties) {
		try(KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties)) {
			consumer.subscribe(List.of("helloworld"));
			while(isContinueToConsume) {				
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(200));
				records.forEach(record -> System.out.println(record.value()));
			}
		}
	}
	
	private void shutdown() {
		isContinueToConsume = false;
	}
	
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "simple_java_consumer");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		SimpleConsumer consumer = new SimpleConsumer();
		consumer.consume(properties);
		Runtime.getRuntime().addShutdownHook(new Thread(consumer::shutdown));
	}

}
