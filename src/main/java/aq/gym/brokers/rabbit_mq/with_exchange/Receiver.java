package aq.gym.brokers.rabbit_mq.with_exchange;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Receiver {

	public static void main(String[] args) {
		try {
			Receiver server = new Receiver();
			File source = new File("src/main/java/aq/gym/brokers/rabbit_mq/with_exchange/connection.json");
			String host = server.getHost(source);
			ConnectionFactory connectionFactory = new ConnectionFactory();
			connectionFactory.setHost(host);
			server.receive(connectionFactory, source);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getHost(File source) throws IOException {
		ObjectMapper jsonMapper = new ObjectMapper();
		JsonNode jsonTree = jsonMapper.readTree(source);
		String host = jsonTree.asText("host");
		return host;
	}
	
	public void receive(ConnectionFactory connectionFactory, File source) {
		try {
			Connection connection = connectionFactory.newConnection();
			Channel channel = connection.createChannel();
			int index = 1;
			boolean autoAck = false;
			String queueName = getQueueNames(source).get(index);
			String routingKey = getRoutingKeys(source).get(index);
			String exchangeName = getExchangeName(source);
			channel.basicQos(1);
			channel.queueDeclare(queueName, false, false, true, null);
			channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);
			channel.queueBind(queueName, exchangeName, routingKey);
			channel.basicConsume(queueName, autoAck, (consumerTag, message) -> {
				String bodyStr = new String(message.getBody());
				String messageStr = "Received " + bodyStr + " to " + queueName;
				System.out.println(messageStr);
				channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
			}, consumerTag -> {});
			System.out.println(queueName + " receiver created!");
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	private List<String> getQueueNames(File source) throws IOException {
		ObjectMapper jsonMapper = new ObjectMapper();
		JsonNode jsonTree = jsonMapper.readTree(source);
		JsonNode queuesArrayNode = jsonTree.findValue("queues");
		List<String> queueNames = new ArrayList<String>();
		for(JsonNode node : queuesArrayNode) {
			String queueName = node.asText();
			queueNames.add(queueName);
		}
		return queueNames;
	}
	
	private List<String> getRoutingKeys(File source) throws IOException {
		ObjectMapper jsonMapper = new ObjectMapper();
		JsonNode jsonTree = jsonMapper.readTree(source);
		JsonNode queuesArrayNode = jsonTree.findValue("routing_keys");
		List<String> routingKeys = new ArrayList<String>();
		for(JsonNode node : queuesArrayNode) {
			String routingKey = node.asText();
			routingKeys.add(routingKey);
		}
		return routingKeys;
	}
	
	private String getExchangeName(File source) throws IOException {
		ObjectMapper jsonMapper = new ObjectMapper();
		JsonNode jsonTree = jsonMapper.readTree(source);
		JsonNode exchangeNameNode = jsonTree.findValue("exchange_name");
		String exchangeName = exchangeNameNode.asText();
		return exchangeName;
	}
}
