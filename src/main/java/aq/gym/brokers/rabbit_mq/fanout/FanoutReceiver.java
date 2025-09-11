package aq.gym.brokers.rabbit_mq.fanout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FanoutReceiver {

	private static final String FANOUT_EXCHANGE_NAME = "my_fanout";
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		try {
			Connection connection = connectionFactory.newConnection();
			Channel channel = connection.createChannel();
			channel.exchangeDeclare(FANOUT_EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, FANOUT_EXCHANGE_NAME, "");
			boolean autoAck = true;
			channel.basicConsume(
					queueName, 
					autoAck,
					(consumerTag, message) -> {
						String stringMessage = new String(message.getBody());
						System.out.println("Received: " + stringMessage);
						if(stringMessage.contains("[end]")) {
							connection.close();
						}
						try {
							TimeUnit.MILLISECONDS.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}, 
					consumerTag -> {});
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} 
	}
}
