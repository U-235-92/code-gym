package aq.gym.brokers.kafka.interceptors;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class MessageProducer {

	private final Properties properties = new Properties();
	
	public MessageProducer() {
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:5050,localhost:5051,localhost:5052");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, MessageProducerInterceptor.class.getName());
	}
	
	public void produce() {
		try(Producer<String, String> producer = new KafkaProducer<String, String>(properties)) {
			for(int i = 0; i < 50; i++) {				
				String payload = "I'm message #" + i;
				ProducerRecord<String, String> record = new ProducerRecord<String, String>("string-messages", payload);
				producer.send(record);
			}
		}
	}
}
