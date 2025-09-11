package aq.gym.brokers.rabbit_mq.header;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.AMQP.BasicProperties;

public class HeaderSender {

	private static final String HEADER_EXCHANGE = "my_header";
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		try(Connection connection = connectionFactory.newConnection();
				Channel channel = connection.createChannel()) {
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("color", "green");
			BasicProperties basicProperties = new BasicProperties.Builder().headers(headers).build();
			channel.exchangeDeclare(HEADER_EXCHANGE, BuiltinExchangeType.HEADERS);
			channel.basicPublish(HEADER_EXCHANGE, "", basicProperties, new String("Hello, green exchange!").getBytes());
			System.out.println("The message was send");
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}
