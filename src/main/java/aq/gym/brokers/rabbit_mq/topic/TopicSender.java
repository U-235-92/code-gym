package aq.gym.brokers.rabbit_mq.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TopicSender {

	private static final String TOPIC_EXCHANGE_NAME = "my_topic";
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try(Connection connection = connectionFactory.newConnection();
				Channel channel = connection.createChannel()) {
			channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
			channel.basicPublish(TOPIC_EXCHANGE_NAME, "car.audi", null, "Audi".getBytes());
			channel.basicPublish(TOPIC_EXCHANGE_NAME, "car.honda", null, "Honda".getBytes());
			channel.basicPublish(TOPIC_EXCHANGE_NAME, "car.green.bmw", null, "BMW".getBytes());
			channel.basicPublish(TOPIC_EXCHANGE_NAME, "car.yellow.lamborghini", null, "Lamborghini".getBytes());
			channel.basicPublish(TOPIC_EXCHANGE_NAME, "fruit.banana", null, "Banana".getBytes());
			channel.basicPublish(TOPIC_EXCHANGE_NAME, "car.red.porshe", null, "Porshe".getBytes());
			System.out.println("Published");
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}
