package aq.gym.brokers.kafka.timestamp_consumer;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class TimestampConsumer {
	
	private Properties properties;
	
	public TimestampConsumer() {
		properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:5050");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
	}
	
	@SuppressWarnings("unused")
	public void consume(long timestamp) {
		try(Consumer<String, String> consumer = new KafkaConsumer<String, String>(properties)) {
			String topic = "string-messages";
//			THE FASHION FOR ALL PARTITIONS READ 
			List<TopicPartition> partitions = consumer.partitionsFor(topic)
					.stream()
					.map(info -> new TopicPartition(topic, info.partition()))
					.toList();
			Map<TopicPartition, Long> timestamps = new HashMap<TopicPartition, Long>();
			partitions.forEach(partition -> timestamps.put(partition, timestamp));
			Map<TopicPartition, OffsetAndTimestamp> offsetsForTimes = consumer.offsetsForTimes(timestamps);
			consumer.assign(partitions);
			offsetsForTimes.forEach((partition, offsetAndTimestamp) -> {
				if(offsetAndTimestamp != null) 
					consumer.seek(partition, offsetAndTimestamp.offset());
			});
			
//			THE FASHION FOR ONLY ONE PARTITION READ 
//			TopicPartition partition0 = new TopicPartition(topic, 0);
//			long beginninOffset = consumer.offsetsForTimes(Collections.singletonMap(partition0, timestamp))
//					.get(partition0).offset();
//			consumer.assign(List.of(partition0));
//			consumer.seek(partition0, beginninOffset);
			while(true) {
				ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(250));
				consumerRecords.forEach(record -> {
					String recordTimestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(record.timestamp()), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
					String key = record.key();
					String value = record.value();
					long offset = record.offset();
					int partition = record.partition();
					System.out.println(String.format("Partition: %d, Offset: %d, Timestamp: [ %s ], Key: %s, Value: %s", partition, offset, recordTimestamp, key, value));
					OffsetAndMetadata offsetAndMetadata = new OffsetAndMetadata(offset);
					TopicPartition topicPartition = new TopicPartition(topic, partition);
					Map<TopicPartition, OffsetAndMetadata> map = new HashMap<>();
					map.put(topicPartition, offsetAndMetadata);
					consumer.commitAsync(map, (offsets, exception) -> {
						if(exception != null) 
							System.err.println(exception.getMessage());
					});
				});
			}
		}
	}
}
