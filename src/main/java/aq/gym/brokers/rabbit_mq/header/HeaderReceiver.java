package aq.gym.brokers.rabbit_mq.header;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class HeaderReceiver {

	private static final String HEADER_EXCHANGE = "my_header";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		String queue = channel.queueDeclare().getQueue();
		channel.exchangeDeclare(HEADER_EXCHANGE, BuiltinExchangeType.HEADERS);
		channel.queueBind(queue, HEADER_EXCHANGE, "");
		DeliverCallback deliverCallback = (consumerTag, message) -> {
			Map<String, Object> headers = message.getProperties().getHeaders();
			if(headers.containsKey("color")) {
				Object val = headers.get("color");
				if(val.toString().equals("green")) {
					System.out.println(new String(message.getBody()));
				}
			}
		};
		channel.basicConsume(queue, true, deliverCallback, consumerTag -> {});
	}
}
