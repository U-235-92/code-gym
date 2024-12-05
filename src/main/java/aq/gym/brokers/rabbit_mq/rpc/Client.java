package aq.gym.brokers.rabbit_mq.rpc;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Client {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(() -> new Client("Alice").remoteCallDemo());
		executor.submit(() -> new Client("Bob").remoteCallDemo());
		executor.submit(() -> new Client("Carolina").remoteCallDemo());
		executor.shutdown();
	}
	
	private String name;
	private final String RPC_QUEUE_NAME = "rpc_queue";
	
	public Client(String name) {
		this.name = name;
	}
	
	private void remoteCallDemo() {
		try {
			ConnectionFactory connectionFactory = new ConnectionFactory();
			connectionFactory.setHost("localhost");
			Connection connection = connectionFactory.newConnection();
			Channel channel = connection.createChannel();
			String resultQueueName = channel.queueDeclare().getQueue();
			String correlationID = UUID.randomUUID().toString();
			channel.queueDeclare(RPC_QUEUE_NAME, false, false, true, null);
			BasicProperties properties = new BasicProperties
				.Builder()
				.replyTo(resultQueueName)
				.correlationId(correlationID)
				.build();
			channel.basicPublish("", RPC_QUEUE_NAME, properties, (name + " RPC call " + "id: " + correlationID).getBytes());
			channel.basicConsume(resultQueueName, true, (consumerTag, message) -> {
				String incommingCorrelationID = message.getProperties().getCorrelationId();
				if(incommingCorrelationID.equals(correlationID))
					System.out.println(new String(message.getBody()));
			}, consumerTag -> {});
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}

}
