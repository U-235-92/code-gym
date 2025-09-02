package aq.gym.brokers.kafka.compact_topic;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class Producer {
	
	private Properties properties;
	
	public Producer() {
		properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:5050");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	}
	
	public void produce(long key, String value) {
		try(org.apache.kafka.clients.producer.Producer<Long, String> producer = new KafkaProducer<Long, String>(properties)) {
			ProducerRecord<Long, String> record = new ProducerRecord<Long, String>("compact-statuses", key, value);
			producer.send(record);
			System.out.println(String.format("Message [ %d: %s ] was written", key, value));
		}
	}
}
