package aq.gym.brokers.rabbit_mq.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class TopicReceiver {

	private static final String TOPIC_EXCHANGE_NAME = "my_topic";
//	private static final String ALL_CARS_ROUTING_KEY = "car.#";
//	private static final String RED_CARS_ROUTING_KEY = "car.red.#";
//	private static final String LAMBORGHINI_ROUTING_KEY = "car.*.lamborghini";
//	private static final String BMW_ROUTING_KEY = "car.*.bmw";
//	private static final String ALL_CARS_QUEUE = "all_cars";
//	private static final String RED_CARS_QUEUE = "red_cars";
//	private static final String LAMBORGHINI_QUEUE = "lamborghini_cars";
//	private static final String BMW_QUEUE = "bmw_cars";
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try {
			Connection connection = connectionFactory.newConnection();
			Channel channel = connection.createChannel();
			channel.basicQos(1);
			channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
			String queue = channel.queueDeclare().getQueue();
			String routingKey = args[0];
			channel.queueBind(queue, TOPIC_EXCHANGE_NAME, routingKey);
			processMessage(connection, channel, queue);
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	private static void processMessage(Connection connection, Channel channel, String queue) throws IOException {
		DeliverCallback deliverCallback = (consumerTag, message) -> {
			System.out.println("Received: " + new String(message.getBody()) + ", Envelope: " + message.getEnvelope());
			channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
		};
		channel.basicConsume(queue, false, deliverCallback, consumerTag -> {});
	}

}
