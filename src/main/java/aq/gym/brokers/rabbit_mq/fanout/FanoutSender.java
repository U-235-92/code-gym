package aq.gym.brokers.rabbit_mq.fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FanoutSender {

	private static final String FANOUT_EXCHANGE_NAME = "my_fanout";
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		try(Connection connection = connectionFactory.newConnection();
				Channel channel = connection.createChannel()) {
			channel.exchangeDeclare(FANOUT_EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
			int tmp = 5;
			while(tmp > 0) {
				String msg = "Hello, fanout exchange! " + tmp;
				channel.basicPublish(FANOUT_EXCHANGE_NAME, "", null, msg.getBytes());
				--tmp;
			}
			channel.basicPublish(FANOUT_EXCHANGE_NAME, "", null, "[end]".getBytes());
			System.out.println("The messages were send");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
