package aq.gym.brokers.kafka.stream;

import static aq.gym.brokers.kafka.stream.Topics.BOOTSTRAP_SERVERS;
import static aq.gym.brokers.kafka.stream.Topics.SINK_TOPIC;
import static aq.gym.brokers.kafka.stream.Topics.SOURCE_TOPIC;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology.AutoOffsetReset;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

public class Streamer {
	
	private Properties properties = new Properties();
	
	public Streamer() {
		properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "simple-kafka-streamer");
	}
	
	public void doToUpperCaseMessagesStream() {
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		
		streamsBuilder.stream(SOURCE_TOPIC, Consumed.with(Serdes.Long(), Serdes.String(), null, AutoOffsetReset.LATEST))
				.mapValues(value -> value.toUpperCase())
				.peek((k, v) -> System.out.println(k + ": " + v))
				.to(SINK_TOPIC, Produced.with(Serdes.Long(), Serdes.String()));
		
		@SuppressWarnings("resource")
		KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties);
		kafkaStreams.start();
	}
	
	public void showKTableContent() {
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		
		KTable<Long, String> sinkTable = streamsBuilder.stream(SOURCE_TOPIC, Consumed.with(Serdes.Long(), Serdes.String(), null, AutoOffsetReset.EARLIEST))
			.toTable();
		
		streamsBuilder.stream(SOURCE_TOPIC, Consumed.with(Serdes.Long(), Serdes.String(), null, AutoOffsetReset.EARLIEST))
			.join(sinkTable, (value1, value2) -> String.format("[ V_1: %s, V_2: %s ]", value1, value2))
			.peek((k, v) -> System.out.println(k + ": " + v))
			.to("string-messages", Produced.with(Serdes.Long(), Serdes.String()));
		
		KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties);
		kafkaStreams.start();
		kafkaStreams.close();
	}
}
