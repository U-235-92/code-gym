package aq.gym.brokers.rabbit_mq.direct;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class DirectSender {

	private static final String DIRECT_EXCHANGE_NAME = "my_direct";
	private static final String ORANGE_ROUTING_KEY = "orange";
	private static final String GREEN_ROUTING_KEY = "green";
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try(Connection connection = connectionFactory.newConnection();
				Channel channel = connection.createChannel()) {
			channel.exchangeDeclare(DIRECT_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
			String orangeMsg = "Orange";
			String greenMsg = "Green";
			for(int i = 0; i < 5; i++) {
				channel.basicPublish(DIRECT_EXCHANGE_NAME, ORANGE_ROUTING_KEY, null, orangeMsg.getBytes());
				channel.basicPublish(DIRECT_EXCHANGE_NAME, GREEN_ROUTING_KEY, null, greenMsg.getBytes());
			}
			channel.basicPublish(DIRECT_EXCHANGE_NAME, GREEN_ROUTING_KEY, null, "[end]".getBytes());
			System.out.println("All the messages were send");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
