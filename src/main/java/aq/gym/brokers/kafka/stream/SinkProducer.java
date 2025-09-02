package aq.gym.brokers.kafka.stream;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import static aq.gym.brokers.kafka.stream.Topics.*;

public class SinkProducer {

	private final Properties producerProperties = new Properties();
	
	public SinkProducer() {
		producerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
		producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);		
	}
	
	public void pushMessage(long key, String value) {
		try(Producer<Long, String> producer = new KafkaProducer<Long, String>(producerProperties)) {				
			ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(SOURCE_TOPIC, key, value);
			producer.send(record);
		}
	}
	
	public void pushMessages() {
		for(long i = 0; i < 100; i++) {
			try(Producer<Long, String> producer = new KafkaProducer<Long, String>(producerProperties)) {				
				String value = "message #" + (i + (long) (Math.random() * (100000 - i) + 1));
				ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(SOURCE_TOPIC, i, value);
				producer.send(record);
			}
		}
	}
}
