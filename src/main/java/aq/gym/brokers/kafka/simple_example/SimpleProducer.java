package aq.gym.brokers.kafka.simple_example;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class SimpleProducer {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		try(Producer<String, String> producer = new KafkaProducer<String, String>(properties)) {
			String payload = "I'm message from simple producer";
			ProducerRecord<String, String> record = new ProducerRecord<String, String>("helloworld", payload);
			producer.send(record);
		}
	}

}
