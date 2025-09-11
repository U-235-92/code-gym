package aq.gym.brokers.rabbit_mq.direct;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class DirectReceiver {

	private static final String DIRECT_EXCHANGE_NAME = "my_direct";
	private static final String ORANGE_ROUTING_KEY = "orange";
	private static final String GREEN_ROUTING_KEY = "green";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		String queueName = channel.queueDeclare().getQueue();
		channel.exchangeDeclare(DIRECT_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
		channel.queueBind(queueName, DIRECT_EXCHANGE_NAME, ORANGE_ROUTING_KEY);
		channel.basicConsume(
				queueName, 
				true, 
				(consumerTag, message) -> {
					String incommingMsg = new String(message.getBody());
					System.out.println("Received: " + incommingMsg);
					if(incommingMsg.contains("[end]")) {
						connection.close();
					}
				}, 
				consumerTag -> {});
	}
}
