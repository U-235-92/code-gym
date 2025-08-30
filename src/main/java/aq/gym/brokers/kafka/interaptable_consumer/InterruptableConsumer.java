package aq.gym.brokers.kafka.interaptable_consumer;

import static aq.gym.brokers.kafka.interaptable_consumer.ConnectionConfig.BOOTSTRAP_SERVERS;
import static aq.gym.brokers.kafka.interaptable_consumer.ConnectionConfig.TOPIC;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class InterruptableConsumer {

	private static Logger devLog = Logger.getLogger(InterruptableConsumer.class.getName() + "-DEV");
	private static Logger warnLog = Logger.getLogger(InterruptableConsumer.class.getName() + "-WARN");
	
	private Properties properties;
	private volatile boolean isConsuming = true;
	
	public InterruptableConsumer() {
		super();
		properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "interaptable_consumers");
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		setUpDevelopmentHandler();
		setUpWarningHandler();
	}
	
	private void setUpDevelopmentHandler() {
		Handler infoHandler = new StreamHandler(System.out, new SimpleFormatter());
		devLog.setUseParentHandlers(false);
		devLog.setLevel(Level.INFO);
		devLog.addHandler(infoHandler);
	}
	
	private void setUpWarningHandler() {
		Handler warnHandler = new StreamHandler(System.err, new SimpleFormatter());
		warnLog.setUseParentHandlers(false);
		warnLog.setLevel(Level.WARNING);
		warnLog.addHandler(warnHandler);
	}
	
	public InterruptableConsumer(Properties properties) {
		this.properties = properties;
	}
	
	public CompletableFuture<Void> consume() {
		return CompletableFuture.supplyAsync(() -> consume0())
			.thenAccept(val -> devLog.info(String.format("Messages processed: %d", val)));
	}
	
	private int consume0() {
		devLog.fine("Started thread: " + Thread.currentThread().getName());
		int processedMessagesNumber = 0;
		try (Consumer<String, String> consumer = new KafkaConsumer<String, String>(properties)) {			
			consumer.subscribe(List.of(TOPIC));
			while(isConsuming) {
				Iterator<ConsumerRecord<String, String>> iterator = consumer.poll(Duration.ofMillis(250)).iterator();
				while(iterator.hasNext()) {
					ConsumerRecord<String, String> record = iterator.next();
					String key = record.key();
					String value = record.value();
					long offset = record.offset();
					int partition = record.partition();
					devLog.fine(String.format("Key: %s, Value: %s, Offset: %d, Partition: %d", key, value, offset, partition));
					processedMessagesNumber++;
				}
			}
		} catch(Exception exc) {
			warnLog.warning(exc.getMessage());
		} 
		return processedMessagesNumber;
	}
	
	public synchronized void interrupt() {
		isConsuming = false;
		warnLog.warning("Consumer was interrupted");
	}
}
