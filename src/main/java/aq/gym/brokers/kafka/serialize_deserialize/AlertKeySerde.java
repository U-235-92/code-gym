package aq.gym.brokers.kafka.serialize_deserialize;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

public class AlertKeySerde implements Serializer<Alert>, Deserializer<String> {

	@Override
	public String deserialize(String topic, byte[] data) {
		if(data == null)
			return null;
		return new String(data);
	}

	@Override
	public byte[] serialize(String topic, Alert data) {
		if(data == null)
			return null;
		return data.getStageId().getBytes();
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		Deserializer.super.configure(configs, isKey);
	}

	@Override
	public void close() {
		Serializer.super.close();
	}
}
