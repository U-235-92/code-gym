package aq.gym.brokers.rabbit_mq.no_exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Client {
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(ConnectionConstants.HOST.getValue());
		Client client = new Client();
		client.sendHello(connectionFactory, "Bob");
	}
	
	private void sendHello(ConnectionFactory connectionFactory, String name) {
		try(Connection connection = connectionFactory.newConnection();
				Channel channel = connection.createChannel()) {
			queueDeclare(channel);
			byte[] hello = ("Hello, " + name + "!").getBytes();
			channel.basicPublish("", ConnectionConstants.QUEUE.getValue(), null, hello);
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	private void queueDeclare(Channel channel) throws IOException {
		boolean durable = false;
		boolean exclusive = false;
		boolean autoDelete = true;
		channel.queueDeclare(ConnectionConstants.QUEUE.getValue(), durable, exclusive, autoDelete, null);
	}
}
