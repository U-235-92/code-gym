package aq.gym.brokers.kafka.timestamp_consumer;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Main {

	public static void main(String[] args) {
		LocalDateTime moment = LocalDateTime.of(2025, 8, 30, 15, 0);
		long mills = moment.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		TimestampConsumer timestampConsumer = new TimestampConsumer();
		timestampConsumer.consume(mills);
	}

}
