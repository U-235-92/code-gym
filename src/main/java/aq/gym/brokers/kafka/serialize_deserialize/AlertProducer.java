package aq.gym.brokers.kafka.serialize_deserialize;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlertProducer {
	
	private static final Logger LOGGER = Logger.getLogger(AlertProducer.class.getName());
	
	private Properties properties;
	
	public AlertProducer() {
		super();
		properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:5050,localhost:5051,localhost:5052");
		properties.put(ProducerConfig.ACKS_CONFIG, "-1");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, AlertKeySerde.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	}
	
	public void produce(Alert alert) {
		try(Producer<String, String> producer = new KafkaProducer<String, String>(properties)) {
			String topic = "alert-messages";
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, alert.toString());
			Future<RecordMetadata> future = producer.send(record);
			RecordMetadata meatadata = future.get();
			long offset = meatadata.offset();
			topic = meatadata.topic();
			String timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(meatadata.timestamp()), ZoneId.systemDefault())
					.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
			LOGGER.info(String.format("Topic: %s, Offset: %d, Timestamp: %s", topic, offset, timestamp));
		} catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}
