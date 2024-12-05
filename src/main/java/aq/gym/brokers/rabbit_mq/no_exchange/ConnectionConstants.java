package aq.gym.brokers.rabbit_mq.no_exchange;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConnectionConstants {

	HOST("localhost"), QUEUE("hello_queue");
	
	private String value;
}
