package aq.gym.brokers.rabbit_mq.with_exchange;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {

	
	public static void main(String[] args) {
		File source = new File("src/main/java/aq/gym/brokers/rabbit_mq/with_exchange/connection.json");
		ConnectionFactory connectionFactory = new ConnectionFactory();
		String name = "Bob";
		Sender sender = new Sender();
		sender.send(connectionFactory, source, name);
	}
	
	private void send(ConnectionFactory connectionFactory, File source, String name) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.submit(() -> {
			try(Connection connection = connectionFactory.newConnection();
					Channel channel = connection.createChannel()) {
				String exchangeName = getExchangeName(source);
				channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);
				int index = 1;
				String routingKey = getRoutingKeys(source).get(index);
				while(true) {
					channel.basicPublish(exchangeName, routingKey, null, name.getBytes());
					TimeUnit.MILLISECONDS.sleep(100);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		executor.shutdown();
	}
	
	private String getExchangeName(File source) throws IOException {
		ObjectMapper jsonMapper = new ObjectMapper();
		JsonNode jsonTree = jsonMapper.readTree(source);
		JsonNode exchangeNameNode = jsonTree.findValue("exchange_name");
		String exchangeName = exchangeNameNode.asText();
		return exchangeName;
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
}
