package aq.gym.brokers.rabbit_mq.rpc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RemoteReceiver {

	public static void main(String[] args) {
		remoteReceiverDemo();
	}
	
	private final static String RPC_QUEUE_NAME = "rpc_queue";
	
	private static void remoteReceiverDemo() {
		try {
			ConnectionFactory connectionFactory = new ConnectionFactory();
			connectionFactory.setHost("localhost");
			Connection connection = connectionFactory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(RPC_QUEUE_NAME, false, false, true, null);
			DeliverCallback callback = (consumerTag, message) -> {
				String messageStr = new String(message.getBody()); 
				System.out.println("Received " + messageStr);
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				channel.basicPublish("", message.getProperties().getReplyTo() , message.getProperties(), "Remote answer".getBytes());
			};
			channel.basicConsume(RPC_QUEUE_NAME, true, callback, consumerTag -> {});
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}
