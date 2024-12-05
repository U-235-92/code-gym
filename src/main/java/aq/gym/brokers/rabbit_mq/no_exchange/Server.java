package aq.gym.brokers.rabbit_mq.no_exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Server {
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Server server = new Server();
		server.recieveMessages(connectionFactory);
	}
	
	public void recieveMessages(ConnectionFactory connectionFactory) {
		try {
			Connection connection = connectionFactory.newConnection();
			Channel channel = connection.createChannel();
			queueDeclare(channel);
			boolean autoAck = true;
			DeliverCallback callback = ((consumerTag, message) -> {
				String body = new String(message.getBody());
				System.out.println("Recieved: " + body);
			});
			channel.basicConsume(ConnectionConstants.QUEUE.getValue(), autoAck, callback, consumerTag -> {});
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
