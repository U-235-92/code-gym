package aq.gym.brokers.kafka.simple_avro_example;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class SimpleAvroProducer {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
		properties.put("schema.registry.url", "http://localhost:8081");
		try(Producer<Long, Alert> producer = new KafkaProducer<Long, Alert>(properties)) {
			Alert alert = new Alert(12345L, System.currentTimeMillis(), AlertStatus.Critical);
			ProducerRecord<Long, Alert> record = new ProducerRecord<>("gym-alert-avro", alert.getSensorId(), alert);
			producer.send(record);
		}
	}

}
