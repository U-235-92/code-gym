package aq.gym.brokers.kafka.interaptable_consumer;

import static aq.gym.brokers.kafka.interaptable_consumer.ConnectionConfig.BOOTSTRAP_SERVERS;
import static aq.gym.brokers.kafka.interaptable_consumer.ConnectionConfig.TOPIC;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class MadProducer {

	private static Logger log = Logger.getLogger(MadProducer.class.getName());
	
	private Properties properties;
	private ExecutorService executor;
	private int threadNumber = 2;
	
	public MadProducer() {
		super();
		properties = new Properties();
		properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		properties.put(ProducerConfig.ACKS_CONFIG, "0");
		properties.put(ProducerConfig.RETRIES_CONFIG, "1");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		executor = Executors.newFixedThreadPool(threadNumber);
		log.setLevel(Level.INFO);
	}
	
	public void produce() {
		for(int i = 0; i < threadNumber; i++) {
			executor.execute(() -> produce0());
		}
		executor.shutdown();
	}
	
	private void produce0() {
		log.info("Started thread: " + Thread.currentThread().getName());
		try(Producer<String, String> producer = new KafkaProducer<String, String>(properties)) {
			for(int i = 0; i < 100; i++) {
				int rnd = (int) (1 + (Math.random() * ((5 - 1) + 1))); //1...5
				String key = Integer.toString(rnd);
				String value = UUID.randomUUID().toString();
				ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, key, value);
				producer.send(record);
				Thread.sleep(100 + (int) (Math.random() * (500 - 100) + 1));
			}
		} catch(Exception exc) {
			exc.printStackTrace();
		} finally {
			log.info(String.format("Thread %s finished produce messages", Thread.currentThread().getName()));
		}
	}
}
